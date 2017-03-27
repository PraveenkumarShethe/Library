package com.praveen.shethe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableAutoConfiguration
@Configuration
@ComponentScan
@Order(1)
public class StatelessAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public StatelessAuthenticationSecurityConfig() {
        super(true);
    }

    @Bean
    public StatelessLoginFilter statelessTokenBasedLoginFilter() throws AuthenticationException {
        StatelessLoginFilter statelessTokenBasedLoginFilter;

        try {
            statelessTokenBasedLoginFilter = new StatelessLoginFilter(
                    "/login/authenticate", //TODO: Take this into YAML
                    tokenAuthenticationService,
                    (UpayogakartaDetailsService) userDetailsService(),
                    authenticationManager());
        } catch (Exception e) {
            final String msg = "Exception occurred during creation of AuthenticationManager: " + e.toString();
            throw new UpayogakartaAuthenticationException(msg, e.getCause());
        }

        return statelessTokenBasedLoginFilter;
    }

    @Bean
    public StatelessAuthenticationFilter statelessTokenBasedAuthenticationFilter() {
        final StatelessAuthenticationFilter statelessTokenBasedAuthenticationFilter =
                new StatelessAuthenticationFilter(tokenAuthenticationService);
        return statelessTokenBasedAuthenticationFilter;
    }

    @Bean
    public AnonymousAuthenticationFilter anonymousFilter() {
        return new AnonymousAuthenticationFilter("anon_auth_unique_key");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(anonymousFilter(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login/authenticate").permitAll()
                .antMatchers("/books/**").authenticated()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .anyRequest().hasRole("USER").and()
                .addFilterBefore(this.statelessTokenBasedLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(this.statelessTokenBasedAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * This bean method creates an instance of the BCryptPasswordEncoder which is used to encode passwords.
     *
     * @return An instance of BCryptPasswordEncoder
     * @see org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UpayogakartaDetailsService();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
}

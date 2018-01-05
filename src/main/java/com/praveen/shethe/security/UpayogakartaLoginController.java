package com.praveen.shethe.security;

import com.praveen.shethe.model.Upayogakarta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 * Created by praveenkumar on 12/27/2016.
 */
@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class UpayogakartaLoginController {


    @Autowired
    private UpayogakartaDetailsService upayogakartaDetailsService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    /**
     * @UserDetails
     * Provides core user information.
     *
     * <p>
     * Implementations are not used directly by Spring Security for security purposes. They
     * simply store user information which is later encapsulated into {@link Authentication}
     * objects. This allows non-security related user information (such as email addresses,
     * telephone numbers etc) to be stored in a convenient location.
     * <p>
     * Concrete implementations must take particular care to ensure the non-null contract
     * detailed for each method is enforced. See
     * {@link org.springframework.security.core.userdetails.User} for a reference
     * implementation (which you might like to extend or use in your code).
     *
     * @see UserDetailsService
     * @see UserCache
     *
     */

    /**
     * @param bindingResult
     * @param httpServletResponse
     * @param user
     * 
     * General interface that represents binding results. Extends the
     * {@link Errors interface} for error registration capabilities,
     * allowing for a {@link Validator} to be applied, and adds
     * binding-specific analysis and model building.
     *
     * <p>Serves as result holder for a {@link DataBinder}, obtained via
     * the {@link DataBinder#getBindingResult()} method. BindingResult
     * implementations can also be used directly, for example to invoke
     * a {@link Validator} on it (e.g. as part of a unit test).
     *
     * @author Juergen Hoeller
     * @since 2.0
     * @see DataBinder
     * @see Errors
     * @see Validator
     * @see BeanPropertyBindingResult
     * @see DirectFieldBindingResult
     * @see MapBindingResult
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(Transactional.TxType.NEVER)
    public void authenticate(@RequestBody Upayogakarta user,
                             BindingResult bindingResult,
                             HttpServletResponse httpServletResponse) {

        UserDetails userDetails = upayogakartaDetailsService.loadUserByUsername(user.getUsername());
        Upayogakarta upayogakarta = upayogakartaDetailsService.getUpayogakarta((User) userDetails);
        if (upayogakarta == null) {
            throw new LibraryUserAuthenticationException("Upayogakarta not found");
        }
        tokenAuthenticationService.addAuthenticationTokenInHeader(httpServletResponse
                , new UpayogakartaAuthentication(upayogakarta));
    }

}

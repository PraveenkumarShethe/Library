package com.praveen.shethe.model;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Created by Praveenkumar on 3/31/2017.
 */
public class Author extends AbstractEntity {

    @NotNull
    @Column(name = "author_name")
    private String authorName;

    @NotNull
    @Column(name = "author_phone_number")
    private String authorPhoneNumber;

    @NotNull
    @Column(name = "author_email_id")
    private String authorEmailId;

    @NotNull
    @Column(name = "author_address")
    private String authorAddress;

    @NotNull
    @OneToMany
    private Book book;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorPhoneNumber() {
        return authorPhoneNumber;
    }

    public void setAuthorPhoneNumber(String authorPhoneNumber) {
        this.authorPhoneNumber = authorPhoneNumber;
    }

    public String getAuthorEmailId() {
        return authorEmailId;
    }

    public void setAuthorEmailId(String authorEmailId) {
        this.authorEmailId = authorEmailId;
    }

    public String getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(String authorAddress) {
        this.authorAddress = authorAddress;
    }
}

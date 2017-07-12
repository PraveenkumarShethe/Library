package com.praveen.shethe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Praveenkumar on 3/7/2017.
 */
@Entity
@Table(name = "book")
public class Book extends AbstractEntity {

    @Column(name = "name")
    @NotNull
    private String name;

    @NotNull
    @JoinColumn(name = "author_id")
    @ManyToOne
    private Author author;

    @NotNull
    @Column(name = "edition")
    private String edition;

    @NotNull
    @Column(name = "price")
    private Long price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}

package com.praveen.shethe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by plank-abhijit on 5/11/16.
 */

@Entity
@Table(name = "ROLE")
public class Role extends AbstractEntity {

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Role role = (Role) o;

        return getName().equals(role.getName());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}

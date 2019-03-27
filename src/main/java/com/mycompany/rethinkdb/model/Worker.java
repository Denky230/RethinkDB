
package com.mycompany.rethinkdb.model;

import java.time.OffsetDateTime;

/**
 *
 * @author grupo4
 */
public class Worker {

    private String username;
    private String password;
    private String name;
    private String surname;
    private OffsetDateTime lastLogin;

    public Worker(String username, String password, String name, String surname, OffsetDateTime lastLogin) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.lastLogin = lastLogin;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public OffsetDateTime getLastLogin() {
        return lastLogin;
    }

    @Override
    public String toString() {
        return "Worker{" + "username=" + username + ", password=" + password + ", name=" + name + ", surname=" + surname + ", lastLogin=" + lastLogin + '}';
    }
}
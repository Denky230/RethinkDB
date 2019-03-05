
package com.mycompany.rethinkdb.model;

import java.util.Date;

/**
 *
 * @author grupo4
 */
public class Empleado {

    private String username;
    private String password;
    private String name;
    private String surname;
    private Date lastLogin;

    public Empleado(String username, String password, String name, String surname, Date lastLogin) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.lastLogin = lastLogin;
    }
}
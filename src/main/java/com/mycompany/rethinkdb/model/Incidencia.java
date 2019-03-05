
package com.mycompany.rethinkdb.model;

import com.mycompany.rethinkdb.constants.UrgencyLevel;
import java.util.Date;

/**
 *
 * @author alu2017310
 */
public class Incidencia {

    private Empleado origin;
    private Empleado destination;
    private String description;
    private Date date;
    private UrgencyLevel urgency;

    public Incidencia(Empleado origin, Empleado destination, String description, Date date, UrgencyLevel urgency) {
        this.origin = origin;
        this.destination = destination;
        this.description = description;
        this.date = date;
        this.urgency = urgency;
    }
}
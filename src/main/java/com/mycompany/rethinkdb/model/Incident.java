
package com.mycompany.rethinkdb.model;

import com.mycompany.rethinkdb.constants.UrgencyLevel;
import java.time.OffsetDateTime;

/**
 *
 * @author alu2017310
 */
public class Incident {

    private int id;
    private Worker origin;
    private Worker destination;
    private String description;
    private OffsetDateTime date;
    private UrgencyLevel urgency;

    public Incident(int id, Worker origin, Worker destination, String description, OffsetDateTime date, UrgencyLevel urgency) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.description = description;
        this.date = date;
        this.urgency = urgency;
    }

    public int getId() {
        return id;
    }
    public Worker getOrigin() {
        return origin;
    }
    public Worker getDestination() {
        return destination;
    }
    public String getDescription() {
        return description;
    }
    public OffsetDateTime getDate() {
        return date;
    }
    public UrgencyLevel getUrgency() {
        return urgency;
    }

    @Override
    public String toString() {
        return "Incident{" + "id=" + id + ", origin=" + origin + ", destination=" + destination + ", description=" + description + ", date=" + date + ", urgency=" + urgency + '}';
    }
}
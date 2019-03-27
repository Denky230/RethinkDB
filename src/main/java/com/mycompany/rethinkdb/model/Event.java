
package com.mycompany.rethinkdb.model;

import com.mycompany.rethinkdb.constants.EventType;
import java.time.OffsetDateTime;

/**
 *
 * @author alu2017310
 */
public class Event {

    private int id;
    private Worker worker;
    private EventType type;
    private OffsetDateTime date;

    public Event(int id, Worker worker, EventType type, OffsetDateTime date) {
        this.id = id;
        this.worker = worker;
        this.type = type;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }
    public Worker getWorker() {
        return this.worker;
    }
    public EventType getType() {
        return type;
    }
    public OffsetDateTime getDate() {
        return date;
    }
}
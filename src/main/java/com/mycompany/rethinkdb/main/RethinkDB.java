
package com.mycompany.rethinkdb.main;

import com.mycompany.rethinkdb.constants.EventType;
import com.mycompany.rethinkdb.constants.UrgencyLevel;
import com.mycompany.rethinkdb.model.Event;
import com.mycompany.rethinkdb.model.Incident;
import com.mycompany.rethinkdb.model.Worker;
import com.mycompany.rethinkdb.persistence.DAO;

import com.rethinkdb.gen.exc.ReqlError;
import java.time.OffsetDateTime;

/**
 *
 * @author Denky + Danielo
 */
public class RethinkDB {

    public static void main(String[] args) {

        try {

            DAO dao = DAO.getInstance();
            dao.connect();

            Worker w = new Worker("denky", "123", "oscar", "rossello", OffsetDateTime.now());
            Incident i = new Incident(4, w, w, "description", OffsetDateTime.now(), UrgencyLevel.URGENT);
            Event e = new Event(4, w, EventType.URGENT_INCIDENT, OffsetDateTime.now());

            dao.disconnect();

        } catch (ReqlError e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
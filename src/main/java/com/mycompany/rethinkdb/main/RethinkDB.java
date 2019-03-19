
package com.mycompany.rethinkdb.main;

import com.mycompany.rethinkdb.persistence.DAO;

import com.rethinkdb.gen.exc.ReqlError;

/**
 *
 * @author Denky + Danielo
 */
public class RethinkDB {

    public static void main(String[] args) {

        try {

            DAO dao = DAO.getInstance();
            dao.connect();

//            Worker w = new Worker("denky", "123", "oscar", "rossello", OffsetDateTime.now());
//            Incident i = new Incident(0, w, w, "description", OffsetDateTime.now(), UrgencyLevel.URGENT);
//            dao.insertWorker(w);
//            dao.insertIncident(i);

            dao.disconnect();

        } catch (ReqlError e) {
            System.out.println(e.getMessage());
        }
    }
}
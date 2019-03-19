
package com.mycompany.rethinkdb.main;

import com.mycompany.rethinkdb.model.Incident;
import com.mycompany.rethinkdb.persistence.DAO;

import com.rethinkdb.gen.exc.ReqlError;
import java.util.List;

/**
 *
 * @author Denky + Danielo
 */
public class RethinkDB {

    public static void main(String[] args) {

        try {

            DAO dao = DAO.getInstance();
            dao.connect();

            List<Incident> is = dao.selectAllIncidents();
            is.forEach((i) -> {
                System.out.println(i);
            });

            dao.disconnect();

        } catch (ReqlError e) {
            System.out.println(e.getMessage());
        }
    }
}

package com.mycompany.rethinkdb.persistence;

import com.mycompany.rethinkdb.constants.UrgencyLevel;
import com.mycompany.rethinkdb.model.Worker;
import com.mycompany.rethinkdb.model.Event;
import com.mycompany.rethinkdb.model.Incident;
import com.mycompany.rethinkdb.model.RankingTO;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import java.time.OffsetDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author grupo4
 */
public class DAO implements DAOInterface {

    private final String DATABASE = "test";
    private final RethinkDB r;
    private Connection c;

    private DAO() {
        r = RethinkDB.r;
    }
    private static DAO instance;
    public static DAO getInstance() {
        if (instance == null)
            instance = new DAO();
        return instance;
    }

    @Override public boolean loginWorker(String user, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override public void insertWorker(Worker w) {
        r.table("worker").insert(r.array(
                r.hashMap("username", w.getUsername())
                    .with("password", w.getPassword())
                    .with("name", w.getName())
                    .with("surname", w.getSurname())
                    .with("last_login", w.getLastLogin().toString())
        )).run(c);
    }
    @Override public void updateWorker(Worker w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override public void removeWorker(Worker w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Worker workerFromMap(HashMap<String, Object>  map) {
        return new Worker(
                map.get("username").toString(),
                map.get("password").toString(),
                map.get("name").toString(),
                map.get("surname").toString(),
                OffsetDateTime.parse(map.get("lastLogin").toString())
        );
    }

    @Override public Incident getIncidentById(int id) {
        // Fetch Incident data from database
        Cursor cursor = r.table("incident").filter(
                r.hashMap("id", id)
        ).run(c);

        // Fill Incident with database data
        Map<String, Object> map = (Map<String, Object>) cursor.next();
        Incident incident = incidentFromMap(map);

        return incident;
    }
    @Override public List<Incident> selectAllIncidents() {
        return r.table("incident").run(c);
    }
    @Override public void insertIncident(Incident i) {
        r.table("incident").insert(r.array(
                r.hashMap("id", i.getId())
                    .with("origin", i.getOrigin())
                    .with("destination", i.getDestination())
                    .with("description", i.getDescription())
                    .with("date_submit", i.getDate().toString())
                    .with("urgency", i.getUrgency().name())
        )).run(c);
    }
    @Override public List<Incident> getIncidentByDestination(Worker w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override public List<Incident> getIncidentByOrigin(Worker w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Incident incidentFromMap(Map<String, Object> map) {
        return new Incident(
                Integer.parseInt(map.get("id").toString()),
                workerFromMap((HashMap) map.get("origin")),
                workerFromMap((HashMap) map.get("destination")),
                map.get("description").toString(),
                OffsetDateTime.parse(map.get("date_submit").toString()),
                UrgencyLevel.valueOf(map.get("urgency").toString())
        );
    }

    @Override public void insertEvent(Event e) {}
    @Override public Event getLastLogin(Worker w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override public List<RankingTO> getRankingWorkers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createDatabaseStructure() {
        // Create tables
        r.tableCreate("worker").run(c);
        r.tableCreate("incident").run(c);
        r.tableCreate("record").run(c);
    }
    public void connect() {
        this.c = r.connection().hostname("localhost").port(28015).db(DATABASE).connect();
    }
    public void disconnect() { this.c.close(); }
}
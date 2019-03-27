
package com.mycompany.rethinkdb.persistence;

import com.mycompany.rethinkdb.constants.EventType;
import com.mycompany.rethinkdb.constants.UrgencyLevel;
import com.mycompany.rethinkdb.model.Worker;
import com.mycompany.rethinkdb.model.Event;
import com.mycompany.rethinkdb.model.Incident;
import com.mycompany.rethinkdb.model.RankingTO;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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

    /* --- WORKER --- */

    @Override public boolean loginWorker(String user, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Worker getWorkerByUsername(String username) {
        Worker worker = null;
        try {
            // Fetch Worker data from database
            Cursor cursor = r.table("worker").filter(
                    r.hashMap("username", username)
            ).run(c);

            // Fill Worker with database data
            Map<String, Object> map = (Map<String, Object>) cursor.next();
            worker = workerFromMap(map);

        } catch (NoSuchElementException e) { }

        return worker;
    }
    @Override public void insertWorker(Worker w) {
        // Make sure Worker is not already registered
        if (getWorkerByUsername(w.getUsername()) != null)
            // TO DO: Throw custom exception
            throw new RuntimeException("User already registered");

        r.table("worker").insert(r.array(
                r.hashMap("username", w.getUsername())
                    .with("password", w.getPassword())
                    .with("name", w.getName())
                    .with("surname", w.getSurname())
                    .with("lastLogin", w.getLastLogin().toString())
        )).run(c);
    }
    @Override public void updateWorker(Worker w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override public void removeWorker(Worker w) {
        // Delete Worker from database
        r.table("worker").filter(
                r.hashMap("username", w.getUsername())
        ).delete().run(c);
    }
    private Worker workerFromMap(Map<String, Object>  map) {
        return new Worker(
                map.get("username").toString(),
                map.get("password").toString(),
                map.get("name").toString(),
                map.get("surname").toString(),
                OffsetDateTime.parse(map.get("lastLogin").toString())
        );
    }

    /* --- INCIDENT --- */

    @Override public List<Incident> selectAllIncidents() {
        // Fetch Incidents data from database
        Cursor cursor = r.table("incident").run(c);

        // Parse each Incident found + add to list
        List<Incident> incidents = new ArrayList<>();
        cursor.forEach((i) -> {
            Map<String, Object> map = (Map<String, Object>) i;
            Incident incident = incidentFromMap(map);
            incidents.add(incident);
        });

        return incidents;
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
    @Override public List<Incident> getIncidentByDestination(Worker w) {
        // Fetch Incidents data from database
        Cursor cursor = r.table("incident").filter(
            incident -> incident.g("destination").g("username").eq(w.getUsername())
        ).run(c);

        // Parse each Incident found + add to list
        List<Incident> incidents = new ArrayList<>();
        cursor.forEach((i) -> {
            Map<String, Object> map = (Map<String, Object>) i;
            Incident incident = incidentFromMap(map);
            incidents.add(incident);
        });

        return incidents;
    }
    @Override public List<Incident> getIncidentByOrigin(Worker w) {
        // Fetch Incidents data from database
        Cursor cursor = r.table("incident").filter(
            incident -> incident.g("origin").g("username").eq(w.getUsername())
        ).run(c);

        // Parse each Incident found + add to list
        List<Incident> incidents = new ArrayList<>();
        cursor.forEach((i) -> {
            Map<String, Object> map = (Map<String, Object>) i;
            Incident incident = incidentFromMap(map);
            incidents.add(incident);
        });

        return incidents;
    }
    @Override public void insertIncident(Incident i) {
        r.table("incident").insert(r.array(
                r.hashMap("id", i.getId())
                    .with("origin", i.getOrigin())
                    .with("destination", i.getDestination())
                    .with("description", i.getDescription())
                    .with("dateSubmit", i.getDate().toString())
                    .with("urgency", i.getUrgency().name())
        )).run(c);
    }
    private Incident incidentFromMap(Map<String, Object> map) {
        return new Incident(
                Integer.parseInt(map.get("id").toString()),
                workerFromMap((HashMap) map.get("origin")),
                workerFromMap((HashMap) map.get("destination")),
                map.get("description").toString(),
                OffsetDateTime.parse(map.get("dateSubmit").toString()),
                UrgencyLevel.valueOf(map.get("urgency").toString())
        );
    }

    /* --- EVENT --- */

    @Override public void insertEvent(Event e) {
        r.table("event").insert(r.array(
                r.hashMap("id", e.getId())
                    .with("worker", e.getWorker())
                    .with("type", e.getType().name())
                    .with("date", e.getDate().toString())
        )).run(c);
    }
    @Override public Event getLastLogin(Worker w) {
        // Fetch Event data from database
        Map<String, Object> map = r.table("event").orderBy().optArg(
                "index", r.desc("worker_lastLogin")
        ).nth(0).run(c);

        // Fill Event with database data
        Event event = eventFromMap(map);

        return event;
    }
    private List<Event> getEventsByType(EventType type) {
        // Fetch Events data from database
        Cursor cursor = r.table("event").filter(
            event -> event.g("type").eq(type.name())
        ).run(c);

        // Parse each Event found + add to list
        List<Event> events = new ArrayList<>();
        cursor.forEach((e) -> {
            Map<String, Object> map = (Map<String, Object>) e;
            Event event = eventFromMap(map);
            events.add(event);
        });

        return events;
    }
    private Event eventFromMap(Map<String, Object> map) {
        return new Event(
                Integer.parseInt(map.get("id").toString()),
                workerFromMap((HashMap) map.get("worker")),
                EventType.valueOf(map.get("type").toString()),
                OffsetDateTime.parse(map.get("date").toString())
        );
    }

    @Override public List<RankingTO> getRankingWorkers() {
        List<RankingTO> ranking = new ArrayList<>();
        List<Event> events = getEventsByType(EventType.URGENT_INCIDENT);

        // TO DO: Sort by Worker urgency events

        return ranking;
    }

    private void createDatabaseStructure() {
        // Create tables
        r.tableCreate("worker").run(c);
        r.tableCreate("incident").run(c);
        r.tableCreate("event").run(c);
        // Create index on Worker -> lastLogin so it's easier to access
        r.table("event").indexCreate("worker_lastLogin", row -> row.g("worker").g("lastLogin")).run(c);
    }
    public void connect() {
        this.c = r.connection().hostname("localhost").port(28015).db(DATABASE).connect();
    }
    public void disconnect() { this.c.close(); }
}
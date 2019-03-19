
package com.mycompany.rethinkdb.persistence;

import com.mycompany.rethinkdb.model.Worker;
import com.mycompany.rethinkdb.model.Event;
import com.mycompany.rethinkdb.model.Incident;
import com.mycompany.rethinkdb.model.RankingTO;
import java.util.List;

/**
 *
 * @author mfontana
 */
public interface DAOInterface {

    // Método para insertar un nuevo empleado.
    public void insertWorker(Worker e);
    // Método para validar el login de un empleado.
    public boolean loginWorker(String user, String pass);
    // Método para modificar el perfil de un empleado.
    public void updateWorker(Worker e);
    // Método para eliminar un empleado.
    public void removeWorker(Worker e);

    // Obtener una Incident a partir de su Id.
    public Incident getIncidentById(int id);
    // Obtener una lista de todas las incidencias
    public List<Incident> selectAllIncidents();
    // Insertar una incidencia a partir de un objeto incidencia
    public void insertIncident(Incident i);
    // Obtener la lista de incidencias con destino un determinado
    // empleado, a partir de un objeto empleado.
    public List<Incident> getIncidentByDestination(Worker e);
    // Obtener la lista de incidencias con origen un determinado
    // empleado, a partir de un objeto empleado.
    public List<Incident> getIncidentByOrigin(Worker e);

    // Método para insertar un evento en la tabla historial.
    // Pasaremos como parámetro un objeto tipo evento, y no devolverá nada.
    // Llamaremos a este método desde los métodos
    // que producen los eventos, que son 3:
    // 1) Cuando un usuario hace login
    // 2) Cuando un usuario crea una incidencia de tipo urgente
    // 3) Cuando se consultan las incidencias destinadas a un usuario
    public void insertEvent(Event e);

    // Obtener la fecha-hora del último inicio de sesión para un empleado.
    public Event getLastLogin(Worker e);

    // Obtener el ranking de los empleados por cantidad de incidencias
    // urgentes creadas (más incidencias urgentes primero).
    public List<RankingTO> getRankingWorkers();
}
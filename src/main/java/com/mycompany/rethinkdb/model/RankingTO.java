
package com.mycompany.rethinkdb.model;

/**
 *
 * @author alu2017310
 */
public class RankingTO {

    private Empleado worker;
    private int numberOfUrgentIncidents;

    public RankingTO(Empleado worker, int numberOfUrgentIncidents) {
        this.worker = worker;
        this.numberOfUrgentIncidents = numberOfUrgentIncidents;
    }
}
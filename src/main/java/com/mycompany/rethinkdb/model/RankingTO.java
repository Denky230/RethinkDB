
package com.mycompany.rethinkdb.model;

/**
 *
 * @author alu2017310
 */
public class RankingTO {

    private Worker worker;
    private int numberOfUrgentIncidents;

    public RankingTO(Worker worker, int numberOfUrgentIncidents) {
        this.worker = worker;
        this.numberOfUrgentIncidents = numberOfUrgentIncidents;
    }
}
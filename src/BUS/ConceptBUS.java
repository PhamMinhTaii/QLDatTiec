package BUS;

import DAO.ConceptDAO;

public class ConceptBUS {

    private final ConceptDAO conceptDAO = new ConceptDAO();

    public double priceConcept(String conceptID) {
        try {
            return conceptDAO.priceConcept(conceptID);
        } catch (Exception e) {
            throw e;
        }
    }

}

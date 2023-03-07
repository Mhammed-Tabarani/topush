package org.example;

public class Grade {

    private int ordreHierarchique;
    private String libelle;
    private int salary;

    /**
     * Constructor
     */
    public Grade(int ordreHierarchique, String libelle, int salary) {
        super();
        this.ordreHierarchique = ordreHierarchique;
        this.libelle = libelle;
        this.salary = salary;
    }

    /** Getters & Setters **/
    public int getOrdreHierarchique() {
        return ordreHierarchique;
    }
    public void setOrdreHierarchique(int ordreHierarchique) {
        this.ordreHierarchique = ordreHierarchique;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public int getSalary() {
        return salary>10000?10000 : salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }


}
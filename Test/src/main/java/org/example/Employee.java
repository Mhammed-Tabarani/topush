package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee implements Comparable<Employee>{

    private String nomComplet;
    private String dateNaissance;
    private Grade grade;

    /**
     * Constructor
     */
    public Employee(String nomComplet, String dateNaissance, Grade grade) {
        super();
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        this.grade = grade;
    }

    /** Getters & Setters **/
    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public LocalDate getDateNaissance() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(this.dateNaissance, formatter);
    }

    @Override
    public int compareTo(Employee o) {
        if(this.getGrade().getOrdreHierarchique() == o.getGrade().getOrdreHierarchique()) {
            return this.getNomComplet().compareTo(o.getNomComplet());
        }else {
            return this.getGrade().getOrdreHierarchique() - o.getGrade().getOrdreHierarchique();
        }
    }


}
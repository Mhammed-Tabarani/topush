package org.example;

import java.time.LocalDate;
import java.time.Period;

public enum CoefficientCotisation {

    /** Values **/
    MOINS_50(0.2f, 0.17f),
    ENTRE_50_60(0.13f, 0.13f),
    ENTRE_60_65(0.075f, 0.09f),
    PLUS_65(0.05f, 0.075f);

    /** Attributes **/
    private float coeffEmployee;
    private float coeffEmployer;

    /**
     * Constructors
     */
    CoefficientCotisation(float coeffEmployee, float coeffEmployer) {
        this.coeffEmployee = coeffEmployee;
        this.coeffEmployer = coeffEmployer;
    }

    /** Getters & Setters **/
    public float getCoeffEmployee() {
        return coeffEmployee;
    }

    public void setCoeffEmployee(float coeffEmployee) {
        this.coeffEmployee = coeffEmployee;
    }

    public float getCoeffEmployer() {
        return coeffEmployer;
    }

    public void setCoeffEmployer(float coeffEmployer) {
        this.coeffEmployer = coeffEmployer;
    }

    /** Get value by age **/
    public static CoefficientCotisation getCoefficientsByDateNaissance(LocalDate dateNaissance) {
        int age = Period.between(dateNaissance, LocalDate.now()).getYears();
        if(age <= 50) return MOINS_50;
        if(age > 50 && age <= 60) return ENTRE_50_60;
        if(age > 60 && age <= 65) return ENTRE_60_65;
        else return PLUS_65;
    }
}
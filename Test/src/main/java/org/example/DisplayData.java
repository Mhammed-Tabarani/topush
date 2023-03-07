package org.example;

public class DisplayData {

    private String nomComplet;
    private String age;
    private String grade;
    private String cotisation;

    /**
     * Constructor
     */
    public DisplayData(String nomComplet, String age, String grade, String cotisation) {
        super();
        this.nomComplet = nomComplet;
        this.age = age;
        this.grade = grade;
        this.cotisation = cotisation;
    }


    /** Getters & Setters **/
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getCotisation() {
        return cotisation;
    }
    public void setCotisation(String cotisation) {
        this.cotisation = cotisation;
    }

    /** toString **/
    @Override
    public String toString() {
        return "Nom complet: "+this.nomComplet+" -Age : "+this.age+" ans - Grade : "
                +this.grade+" -Total de la cotisation : "+this.cotisation+"€";
    }
}
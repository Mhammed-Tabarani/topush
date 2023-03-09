package org.example;

public class Main {
    public static void main(String[] args) {
        ComputeData data = new ComputeData();
        DisplayData[] result = data.processData();
        for(int i = 0; i< result.length; ++i) {
            System.out.println(result[i].toString());
        }
    }
}


//////////////////////////////////////////////////


package org.example;

import java.time.LocalDate;
import java.time.Period;

public enum ClasseVoyage {
    ECONOMIQUE(1),
    AFFAIRE(2),
    PREMIER(3);
    private int coeff;

    ClasseVoyage(int coeff) {
        this.coeff = coeff;
    }

    public int getCoeff() {
        return coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }
    public static ClasseVoyage getClasseVoyage(int nbr){

        if (nbr==2) return AFFAIRE;
        if (nbr==3) return PREMIER;
        else return ECONOMIQUE;
    }

}


///////////////////////////////////


package org.example;

import java.time.LocalDate;
import java.time.Period;

public enum CoefficientDate {
    PLUS_DE_30(0.8f),
    PLUS_DE_15_MOINS_DE_30(1.0f),
    PLUS_DE_7_MOINS_DE_15(1.2f),
    MOINS_DE_7(1.5f),
    ;
    private float coefficient;

    CoefficientDate(float coefficient) {
        this.coefficient = coefficient;
    }

    public float getCoefficientDate() {
        return coefficient;
    }

    public void setCoefficientDate(float coefficient) {
        this.coefficient = coefficient;
    }

    public static CoefficientDate coefficientDate(LocalDate dateReservation){
        int nbrJour = Period.between(dateReservation,LocalDate.now()).getDays();

        if (nbrJour>=15&&nbrJour<30) return PLUS_DE_15_MOINS_DE_30;
        if (nbrJour>=7&&nbrJour<15) return PLUS_DE_7_MOINS_DE_15;
        if (nbrJour<7) return MOINS_DE_7;
        else return PLUS_DE_30;
    }
}


////////////////////////////////
package org.example;

public class ComputeData {
}



////////////////


package org.example;

public class Destination {
    private String name;
    private int tarif;

    public Destination(String name, int tarif) {
        this.name = name;
        this.tarif = tarif;
    }

    public Destination() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }
}


package org.example;

public enum Months {
    Janvier(1),
    Fevrier(2),
    Mars(3),
    Avril(4),
    Mai(5),
    Juin(6),
    Juillet(7),
    Aout(8),
    September(9),
    Octobre(10),
    Novembre(11),
    Decembre(12)
    ;
    private int month;

    Months(int month) {
        this.month = month;
    }

    public Months getMonth(int nbr) {
        switch (nbr){
            case 1:
                return Janvier;
            case 2:
                return Fevrier;
            case 3:
                return Mars;
            case 4:
                return Avril;
            case 5:
                return Mai;
            case 6:
                return Juin;
            case 7:
                return Juillet;
            case 8:
                return Aout;
            case 9:
                return September;
            case 10:
                return Octobre;
            case 11:
                return Novembre;
        }
        return Decembre;
    }
}

/////////////////////////////////////////////


package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Voyage {
    private Destination destination;
    private LocalDate date;
    private ClasseVoyage classeVoyage;
    private float cout;

    public Voyage(Destination destination, LocalDate date, ClasseVoyage classeVoyage, float cout) {
        this.destination = destination;
        this.date = date;
        this.classeVoyage = classeVoyage;
        this.cout = cout;
    }

    public float calculeCout(){
        return this.destination.getTarif()*this.classeVoyage.getCoeff()*CoefficientDate.coefficientDate(date).getCoefficientDate();
    }
    public LocalDate getRandomDate(){
        Random rand = new Random();
        int randomDay =  rand.nextInt(60);
        return LocalDate.now().plusDays(randomDay);
    }
    public ClasseVoyage getRandomClasseVoyage(){
        Random rand=new Random();
        int randomClasse=rand.nextInt(4);
        return ClasseVoyage.getClasseVoyage(randomClasse);
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ClasseVoyage getClasseVoyage() {
        return classeVoyage;
    }

    public void setClasseVoyage(ClasseVoyage classeVoyage) {
        this.classeVoyage = classeVoyage;
    }

    public float getCout() {
        return cout;
    }

    public void setCout(float cout) {
        this.cout = cout;
    }
}


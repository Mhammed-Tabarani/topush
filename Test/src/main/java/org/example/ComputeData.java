package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ComputeData {

    Map<Integer, Grade> grades = new HashMap<Integer, Grade>();
    List<Employee> employees = new ArrayList<Employee>();

    public void setData() {

        // Cr�ation des grades
        Grade manager = new Grade(1, "Manager operationnel", 25000);
        grades.put(1, manager);
        Grade chef = new Grade(2, "Chef de departement", 20000);
        grades.put(2, chef);
        Grade cadreSup = new Grade(3, "Cadre superieur", 15000);
        grades.put(3, cadreSup);
        Grade cadreCom = new Grade(4, "Cadre commercial", 12500);
        grades.put(4, cadreCom);
        Grade ing = new Grade(5, "Ingenieur", 10000);
        grades.put(5, ing);
        Grade tech = new Grade(6, "Technicien", 8000);
        grades.put(6, tech);
        Grade autre = new Grade(7, "Autre", 5000);
        grades.put(7, autre);

        // Cr�ation des employ�s
        Grade gradeEmp = this.getRandomGrade(grades);
        Employee employee = new Employee("BENKHALIL Mouad", "23/06/1987", gradeEmp);
        employees.add(employee);
        gradeEmp = this.getRandomGrade(grades);
        employee = new Employee("BENCHAKIB Laila", "05/05/1988", gradeEmp);
        employees.add(employee);
        gradeEmp = this.getRandomGrade(grades);
        employee = new Employee("BOULMANE Sarah", "05/07/1999", gradeEmp);
        employees.add(employee);
        gradeEmp = this.getRandomGrade(grades);
        employee = new Employee("CIQAL Mohamed", "19/02/1980", gradeEmp);
        employees.add(employee);
        gradeEmp = this.getRandomGrade(grades);
        employee = new Employee("CHABA Meriem", "01/02/1973", gradeEmp);
        employees.add(employee);
        gradeEmp = this.getRandomGrade(grades);
        employee = new Employee("DEDDI Salah", "15/03/1957", gradeEmp);
        employees.add(employee);
        gradeEmp = this.getRandomGrade(grades);
        employee = new Employee("ENNAJAR Fouad", "08/08/1962", gradeEmp);
        employees.add(employee);
        gradeEmp = this.getRandomGrade(grades);
        employee = new Employee("MERZOUKI Bilal", "10/12/1968", gradeEmp);
        employees.add(employee);
        gradeEmp = this.getRandomGrade(grades);
        employee = new Employee("WADOUD Faouzi", "05/07/1963", gradeEmp);
        employees.add(employee);
        gradeEmp = this.getRandomGrade(grades);
        employee = new Employee("ZEROUAL Adnane", "19/02/1955", gradeEmp);
        employees.add(employee);
    }

    public Grade getRandomGrade(Map<Integer, Grade> grades) {
        int min = 1, max = 7;
        Random rand = new Random();
        int index =  rand.nextInt((max - min) + 1) + min;
        return grades.get(index);
    }

    public DisplayData[] processData() {
        DisplayData[] result = new DisplayData[10];
        this.setData();
        Collections.sort(this.employees);
        int i = 0;
        for(Employee unEmpl : this.employees) {
            CoefficientCotisation coeff = CoefficientCotisation.getCoefficientsByDateNaissance(unEmpl.getDateNaissance());
            int age = Period.between(unEmpl.getDateNaissance(), LocalDate.now()).getYears();
            int salary =  unEmpl.getGrade().getSalary();
            float cotisation = salary * (coeff.getCoeffEmployee() + coeff.getCoeffEmployer());
            String cotisationStr = String.format("%.2f", cotisation);
            result[i] = new DisplayData(unEmpl.getNomComplet(), Integer.toString(age), unEmpl.getGrade().getLibelle(), cotisationStr);
            ++i;
        }
        return result;
    }
}
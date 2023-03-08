package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ComputeDataTest {
    ComputeData computeData=new ComputeData();
    @BeforeEach
    void beforeEach(){
        computeData.setData();
    }
    @RepeatedTest(10)
    void randomTest(){
        int sizeOfEnum = 7;
        int count = 0;
        String firstGrade = computeData.getRandomGrade(computeData.grades).getLibelle();

        for (int i =0 ; i < sizeOfEnum ; i++){
            if (Objects.equals(computeData.getRandomGrade(computeData.grades).getLibelle(), firstGrade))
                count ++;
        }
        System.out.println(count);

        assertNotEquals(sizeOfEnum+1, count);
    }

}


/////////////////////////////////////////
package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ComputeDataTest {
  /*  ComputeData computeData=new ComputeData();
    @BeforeEach
    void setComputeData(){
        computeData.setData();
    }
    @RepeatedTest(10)
    void getRandomTest(){
        Grade firstGrade=computeData.getRandomGrade(computeData.grades);
        int sizeOfGradesMap=computeData.grades.size();
        int count =0;
        for (int i =0;i<=sizeOfGradesMap;i++){
            if(Objects.equals(firstGrade,computeData.getRandomGrade(computeData.grades)))
                count++;
        }
        System.out.println(count);
        assertNotEquals(computeData.grades.size(),count);
    }*/


    @RepeatedTest(100)
    void randomGradeTest(){
        ComputeData computeData1=new ComputeData();
        ComputeData computeData2=new ComputeData();
        computeData1.setData();
        computeData2.setData();
/*        computeData1.employees.forEach(employee -> {
            System.out.println("ComputeData1"+employee.getGrade().toString());
        });
        computeData2.employees.forEach(employee -> {
            System.out.println("ComputeData2"+employee.getGrade().toString());
        });*/
        assertNotEquals(computeData1.employees,computeData2.employees,"Les grades sont egaux");

    }


    private int returnGrade(String grade){
        switch(grade) {
            case "Manager operationnel":
                return 1;
            case "Chef de departement":
                return 2;
            case "Cadre superieur":
                return 3;
            case "Cadre commercial":
                return 4;
            case "Ingenieur":
                return 5;
            case "Technicien":
                return 6;
            case "Autre":
                return 7;
        }
        return 0;
    }
    private DisplayData[] sortdDisplayData(DisplayData[] displayData){
        DisplayData temp=null;
        for (int i = 0; i < displayData.length-1; i++) {
            for (int j = i+1; j < displayData.length; j++) {
                if(returnGrade(displayData[j].getGrade())<returnGrade(displayData[i].getGrade())){
                    temp=displayData[i];
                    displayData[i]=displayData[j];
                    displayData[j]=temp;
                } else if (returnGrade(displayData[i].getGrade())==returnGrade(displayData[j].getGrade())) {

                            if(displayData[i].getNomComplet().compareTo(displayData[j].getNomComplet())>0){
                                temp=displayData[i];
                                displayData[i]=displayData[j];
                                displayData[j]=temp;
                            }

                }
            }
        }
        return displayData;
    }
    @Test
    void checkIfOrdred(){
/*        DisplayData[] data = new DisplayData[5];

            data[0]=new DisplayData("CHABA Meriem","50 ans","Ingenieur","3700,00€");
            data[1]=new DisplayData("BENCHAKIB Laila","50 ans","Chef de departement","3700,00€");
            data[2]=new DisplayData("BENKHALIL Mouad","50 ans","Chef de departement","3700,00€");
            data[3]=new DisplayData("MERZOUKI Bilal","50 ans","Manager operationnel","3700,00€");
            data[4]=new DisplayData("CIQAL Mohamed","50 ans","Autre","3700,00€");
        data=sortdDisplayData(data);
        for(int i = 0; i< data.length; ++i) {
            System.out.println(data[i].toString());
        }*/
        ComputeData computeData=new ComputeData();
        DisplayData[] result=computeData.processData();
        DisplayData[] test=sortdDisplayData(result);
        assertEquals(result,test,"Les résultats ne sont pas triés par ordre de la hiérarchie puis le nom complet de l’employé affichés ");

    }
}

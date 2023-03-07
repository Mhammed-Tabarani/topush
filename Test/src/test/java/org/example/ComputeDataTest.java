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
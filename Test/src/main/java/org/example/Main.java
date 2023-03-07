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
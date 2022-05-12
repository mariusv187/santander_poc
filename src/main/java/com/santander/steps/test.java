package com.santander.steps;

public class test {
    public static void main(String[] args) {
        int[] numbers = new int[5];
        numbers[0] = 5;
        numbers[1] = 4;
        numbers[2] = 10;
        numbers[3] = 1;
        numbers[4] = 8;
        int aux;
        for (int i = 0; i < 4; i++) {
            if (numbers[i] > numbers[i + 1]) {
                aux = numbers[i + 1];
                numbers[i + 1] = numbers[i];
                numbers[i] = aux;
                i=-1;
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(numbers[i]);
        }
    }

}

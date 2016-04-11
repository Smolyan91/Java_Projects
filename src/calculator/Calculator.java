package calculator;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Введите нужную операцию: ");
        System.out.println("1- сложить ");
        System.out.println("2 - вычесть ");
        System.out.println("3 - умножить ");
        System.out.println("4- разделить ");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            String result = bufferedReader.readLine();
            if (result.equals(Operation.valueOf(result))){

            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}

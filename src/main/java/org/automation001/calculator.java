package org.automation001;

public class calculator {
    public static int add(int a , int b){
        System.out.println("Addition of a and b is: "+(a+b));
        return a + b;

    }

    public static int sub(int a , int b){
        System.out.println("Subtraction of A and B: " + (a-b));
        return a - b;
    }
    public static int div(int a , int b){
        System.out.println("Division of A and B is: " + a/b);
        return a / b;
    }
    public static int mul(int a , int b){
        System.out.println("Multiplication of a and B is: "+ a*b);
        return a * b;
    }
    public static int mod(int a , int b){
        System.out.println("Mod of a and b is: "+a%b);
        return a % b;
    }

}

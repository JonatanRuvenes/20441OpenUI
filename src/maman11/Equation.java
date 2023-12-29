package maman11;

/**
 * Equation.java
 *
 * the program use the quadratic formula to find the solution(s) for quadratic equation. 
 */

import java.util.Scanner;

public class Equation{
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter 3 coefficients of the polynomial equation:");
        double a = scan.nextDouble();
        double b = scan.nextDouble();
        double c = scan.nextDouble();
        
        double discriminant = Math.pow(b,2) - 4*a*c;
        
        if(discriminant < 0) // There is no solution for the equation
            System.out.println("There is no solution.");
        else if (discriminant == 0){ // There is only one solution for the equation
                double X1 = -b/(2*a);
                System.out.println("There is 1 solution. X1 = " + X1 + ".");
            }
            else{ // There are two solution for the equation
                double X1 = (-b + Math.sqrt(discriminant))/(2*a);
                double X2 = (-b - Math.sqrt(discriminant))/(2*a);
                System.out.println("There are 2 solutions. X1 = " + X1 + ", X2 = " + X2 + ".");
            }

    }
    
}

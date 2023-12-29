package maman11;

/**
 * Origin.java
 * 
 * program that find the nearest point to the origin from three given pints.
 */



import java.util.Scanner;

public class Origin{
    
     public static void main (String [] args){
         Scanner scan = new Scanner (System.in);
         System.out.println("Enter first point coordinates:");
         int x1 = scan.nextInt();
         int y1 = scan.nextInt();
         System.out.println ("Enter second point coordinates:");
         int x2 = scan.nextInt();
         int y2 = scan.nextInt();
         System.out.println ("Enter third point coordinates:");
         int x3 = scan.nextInt();
         int y3 = scan.nextInt();
         
         double dis1 = Math.sqrt(Math.pow(x1,2) + Math.pow(y1,2)); // calculate the distance of the first point from the origin
         double dis2 = Math.sqrt(Math.pow(x2,2) + Math.pow(y2,2)); // calculate the distance of the second point from the origin
         double dis3 = Math.sqrt(Math.pow(x3,2) + Math.pow(y3,2)); // calculate the distance of the third point from the origin
         
         if (dis1 <= dis2 && dis1 <= dis3) //the first point is the nearest (or close as the nearest) point to the origin
             System.out.println ("The nearest point to the origin is (" + x1 + "," + y1 +")");
         else if (dis2 <= dis3) //the second point is the nearest (or close as the third) point to the origin
                System.out.println ("The nearest point to the origin is (" + x2 + "," + y2 +")");
            else //the third point is the nearest point to the origin
                System.out.println ("The nearest point to the origin is (" + x3 + "," + y3 +")"); 
        

     } // end of method main
     
} //end of class Origin 

package com.hari;

import java.util.Scanner;
public class Main {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       
       System.out.println("=======================================");
       System.out.println("  WELCOME TO TAX CALCULATION APP");
       System.out.println("=======================================");
       System.out.println("\nPLEASE LOGIN TO CONTINUE -");
       System.out.print("USERNAME - ");
       String username = scanner.nextLine();
       System.out.print("PASSWORD - ");
       String password = scanner.nextLine();
      
       if (username.equals("admin") && password.equals("admin123")) {
           System.out.println("\nLogin Successful!\n");
           TaxCalculationApp app = new TaxCalculationApp();
           app.start();
       } else {
           System.out.println("\nInvalid credentials!");
       }
       scanner.close();
   }
}

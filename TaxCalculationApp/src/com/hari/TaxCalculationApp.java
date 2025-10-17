package com.hari;

import java.util.ArrayList;
import java.util.Scanner;
public class TaxCalculationApp {
   private ArrayList<Property> properties;
   private ArrayList<Vehicle> vehicles;
   private Scanner scanner;
   public TaxCalculationApp() {
       properties = new ArrayList<>();
       vehicles = new ArrayList<>();
       scanner = new Scanner(System.in);
   }
   public void start() {
       boolean running = true;
       while (running) {
           displayMainMenu();
           int choice = getIntInput("Enter your choice: ");
           switch (choice) {
               case 1:
                   propertyTaxMenu();
                   break;
               case 2:
                   vehicleTaxMenu();
                   break;
               case 3:
                   displayTotal();
                   break;
               case 4:
                   System.out.println("\nThank you for using Tax Calculation App!");
                   running = false;
                   break;
               default:
                   System.out.println("\nInvalid choice! Please try again.");
           }
       }
   }
   private void displayMainMenu() {
       System.out.println("\n=======================================");
       System.out.println("         MAIN MENU");
       System.out.println("=======================================");
       System.out.println("1. PROPERTY TAX");
       System.out.println("2. VEHICLE TAX");
       System.out.println("3. TOTAL");
       System.out.println("4. EXIT");
       System.out.println("=======================================");
   }
   private void propertyTaxMenu() {
       boolean back = false;
       while (!back) {
           System.out.println("\n=======================================");
           System.out.println("         PROPERTY TAX MENU");
           System.out.println("=======================================");
           System.out.println("1. ADD PROPERTY DETAILS");
           System.out.println("2. CALCULATE PROPERTY TAX");
           System.out.println("3. DISPLAY ALL PROPERTIES");
           System.out.println("4. BACK TO MAIN MENU");
           System.out.println("=======================================");
           int choice = getIntInput("Enter your choice: ");
           switch (choice) {
               case 1:
                   addPropertyDetails();
                   break;
               case 2:
                   calculatePropertyTax();
                   break;
               case 3:
                   displayAllProperties();
                   break;
               case 4:
                   back = true;
                   break;
               default:
                   System.out.println("\nInvalid choice!");
           }
       }
   }
   private void addPropertyDetails() {
       System.out.println("\n--- ADD PROPERTY DETAILS ---");
       double baseValue = getDoubleInput("ENTER THE BASE VALUE OF LAND - ");
       int builtUpArea = getIntInput("ENTER THE BUILT-UP AREA OF LAND - ");
       int age = getIntInput("ENTER THE AGE OF LAND IN YEARS - ");
       System.out.print("IS THE LAND LOCATED IN CITY?(Y: YES, N: NO) - ");
       String location = scanner.next();
       scanner.nextLine(); 
       boolean inCity = location.equalsIgnoreCase("Y");
       Property property = new Property(baseValue, builtUpArea, age, inCity);
       properties.add(property);
       System.out.println("\nProperty added successfully!");
   }
   private void calculatePropertyTax() {
       if (properties.isEmpty()) {
           System.out.println("\nNo properties added yet!");
           return;
       }
       System.out.println("\n--- CALCULATE PROPERTY TAX ---");
       System.out.print("ENTER THE PROPERTY ID TO CALCULATE THE TAX - ");
       int id = scanner.nextInt() - 1;
       scanner.nextLine();
       if (id >= 0 && id < properties.size()) {
           Property p = properties.get(id);
           p.calculateTax();
           System.out.println("\nPROPERTY TAX FOR PROPERTY ID " + (id + 1) + " IS RS " +
                            String.format("%.2f", p.getTax()));
       } else {
           System.out.println("\nInvalid property ID!");
       }
   }
   private void displayAllProperties() {
       if (properties.isEmpty()) {
           System.out.println("\nNo Data Present at This Moment!");
           return;
       }
       System.out.println("\n=======================================================================================================");
       System.out.printf("%-5s %-15s %-15s %-15s %-15s %-20s%n",
                        "ID", "BUILT-UP AREA", "BASE PRICE", "AGE(YEARS)", "IN CITY", "PROPERTY TAX");
       System.out.println("=======================================================================================================");
       for (int i = 0; i < properties.size(); i++) {
           Property p = properties.get(i);
           System.out.printf("%-5d %-15d %-15.2f %-15d %-15s %-20.2f%n",
                           (i + 1), p.getBuiltUpArea(), p.getBaseValue(),
                           p.getAge(), p.isInCity() ? "Y" : "N", p.getTax());
       }
       System.out.println("=======================================================================================================");
   }
   private void vehicleTaxMenu() {
       boolean back = false;
       while (!back) {
           System.out.println("\n=======================================");
           System.out.println("         VEHICLE TAX MENU");
           System.out.println("=======================================");
           System.out.println("1. ADD VEHICLE DETAILS");
           System.out.println("2. CALCULATE VEHICLE TAX");
           System.out.println("3. DISPLAY ALL VEHICLES");
           System.out.println("4. BACK TO MAIN MENU");
           System.out.println("=======================================");
           int choice = getIntInput("Enter your choice: ");
           switch (choice) {
               case 1:
                   addVehicleDetails();
                   break;
               case 2:
                   calculateVehicleTax();
                   break;
               case 3:
                   displayAllVehicles();
                   break;
               case 4:
                   back = true;
                   break;
               default:
                   System.out.println("\nInvalid choice!");
           }
       }
   }
   private void addVehicleDetails() {
       System.out.println("\n--- ADD VEHICLE DETAILS ---");
       System.out.print("ENTER THE VEHICLE REGISTRATION NUMBER - ");
       String regNo = scanner.next();
       scanner.nextLine();
       System.out.print("ENTER BRAND OF THE VEHICLE - ");
       String brand = scanner.nextLine();
       int velocity = getIntInput("ENTER THE MAXIMUM VELOCITY OF THE VEHICLE(KMPH) - ");
       int capacity = getIntInput("ENTER CAPACITY(NUMBER OF SEATS) OF THE VEHICLE - ");
       System.out.println("\nCHOOSE THE TYPE OF THE VEHICLE -");
       System.out.println("1. PETROL DRIVEN");
       System.out.println("2. DIESEL DRIVEN");
       System.out.println("3. CNG/LPG DRIVEN");
       int typeChoice = getIntInput("Enter choice: ");
       double purchaseCost = getDoubleInput("ENTER THE PURCHASE COST OF THE VEHICLE - ");
       Vehicle vehicle = new Vehicle(regNo, brand, velocity, capacity, typeChoice, purchaseCost);
       vehicles.add(vehicle);
       System.out.println("\nVehicle added successfully!");
   }
   private void calculateVehicleTax() {
       if (vehicles.isEmpty()) {
           System.out.println("\nNo vehicles added yet!");
           return;
       }
       System.out.println("\n--- CALCULATE VEHICLE TAX ---");
       System.out.print("ENTER THE VEHICLE ID TO CALCULATE THE TAX - ");
       int id = scanner.nextInt() - 1;
       scanner.nextLine();
       if (id >= 0 && id < vehicles.size()) {
           Vehicle v = vehicles.get(id);
           v.calculateTax();
           System.out.println("\nVEHICLE TAX FOR VEHICLE ID " + (id + 1) + " IS RS " +
                            String.format("%.2f", v.getTax()));
       } else {
           System.out.println("\nInvalid vehicle ID!");
       }
   }
   private void displayAllVehicles() {
       if (vehicles.isEmpty()) {
           System.out.println("\nNo Data Present at This Moment!");
           return;
       }
       System.out.println("\n================================================================================================================");
       System.out.printf("%-5s %-12s %-15s %-12s %-10s %-15s %-15s %-15s%n",
                        "ID", "REG NO", "BRAND", "VELOCITY", "CAPACITY", "TYPE", "COST", "VEHICLE TAX");
       System.out.println("================================================================================================================");
       for (int i = 0; i < vehicles.size(); i++) {
           Vehicle v = vehicles.get(i);
           System.out.printf("%-5d %-12s %-15s %-12d %-10d %-15s %-15.2f %-15.2f%n",
                           (i + 1), v.getRegNo(), v.getBrand(), v.getVelocity(),
                           v.getCapacity(), v.getTypeString(), v.getPurchaseCost(), v.getTax());
       }
       System.out.println("================================================================================================================");
   }
   private void displayTotal() {
       double totalPropertyTax = 0;
       double totalVehicleTax = 0;
       for (Property p : properties) {
           totalPropertyTax += p.getTax();
       }
       for (Vehicle v : vehicles) {
           totalVehicleTax += v.getTax();
       }
       System.out.println("\n=======================================");
       System.out.println("         TAX SUMMARY");
       System.out.println("=======================================");
       System.out.println("Total number of properties: " + properties.size());
       System.out.println("Total property tax: RS " + String.format("%.2f", totalPropertyTax));
       System.out.println("\nTotal number of vehicles: " + vehicles.size());
       System.out.println("Total vehicle tax: RS " + String.format("%.2f", totalVehicleTax));
       System.out.println("\nTotal tax payable: RS " + String.format("%.2f", (totalPropertyTax + totalVehicleTax)));
       System.out.println("=======================================");
       if (properties.isEmpty() && vehicles.isEmpty()) {
           System.out.println("\nNo Data Present at This Moment!");
       }
   }
   private int getIntInput(String prompt) {
       System.out.print(prompt);
       while (!scanner.hasNextInt()) {
           System.out.print("Invalid input! " + prompt);
           scanner.next();
       }
       int value = scanner.nextInt();
       scanner.nextLine(); 
       return value;
   }
   private double getDoubleInput(String prompt) {
       System.out.print(prompt);
       while (!scanner.hasNextDouble()) {
           System.out.print("Invalid input! " + prompt);
           scanner.next();
       }
       double value = scanner.nextDouble();
       scanner.nextLine(); 
       return value;
   }
}

package com.hari;

public class Vehicle {
	   private String regNo;
	   private String brand;
	   private int velocity;
	   private int capacity;
	   private int type; 
	   private double purchaseCost;
	   private double tax;
	   public Vehicle(String regNo, String brand, int velocity, int capacity,
	                  int type, double purchaseCost) {
	       this.regNo = regNo;
	       this.brand = brand;
	       this.velocity = velocity;
	       this.capacity = capacity;
	       this.type = type;
	       this.purchaseCost = purchaseCost;
	       this.tax = 0;
	   }
	   public void calculateTax() {
	       // Vehicle tax = velocity + capacity + percentage of purchase cost
	       double percentage = 0;
	       switch (type) {
	           case 1: 
	               percentage = 0.10; 
	               break;
	           case 2: 
	               percentage = 0.11; 
	               break;
	           case 3: 
	               percentage = 0.12; 
	               break;
	       }
	       tax = velocity + capacity + (percentage * purchaseCost);
	   }
	   public String getTypeString() {
	       switch (type) {
	           case 1: return "PETROL";
	           case 2: return "DIESEL";
	           case 3: return "CNG/LPG";
	           default: return "UNKNOWN";
	       }
	   }
	   
	   public String getRegNo() { return regNo; }
	   public String getBrand() { return brand; }
	   public int getVelocity() { return velocity; }
	   public int getCapacity() { return capacity; }
	   public int getType() { return type; }
	   public double getPurchaseCost() { return purchaseCost; }
	   public double getTax() { return tax; }
	}
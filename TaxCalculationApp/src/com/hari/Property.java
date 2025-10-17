package com.hari;

public class Property {
	   private double baseValue;
	   private int builtUpArea;
	   private int age;
	   private boolean inCity;
	   private double tax;
	   public Property(double baseValue, int builtUpArea, int age, boolean inCity) {
	       this.baseValue = baseValue;
	       this.builtUpArea = builtUpArea;
	       this.age = age;
	       this.inCity = inCity;
	       this.tax = 0;
	   }
	   public void calculateTax() {
	      
	       double ageFactor = 1.0 - (age * 0.05); 
	       if (inCity) {
	         
	           tax = (builtUpArea * ageFactor * baseValue) + (0.05 * builtUpArea);
	       } else {
	           
	           tax = builtUpArea * ageFactor * baseValue;
	       }
	   }
	   // Getters
	   public double getBaseValue() { return baseValue; }
	   public int getBuiltUpArea() { return builtUpArea; }
	   public int getAge() { return age; }
	   public boolean isInCity() { return inCity; }
	   public double getTax() { return tax; }
	}
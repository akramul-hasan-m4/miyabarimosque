package com.panjura.mosque.miyabarimosque.bean;


public enum IncomeType  {
	
	MONTHLY_DONATION("Monthly Donation"),
	WEEKLY_COLLECTION("Weekly Collection"), 
	BY_FRUIT_SALE("By Fruit Sale"), 
	SPECIAL_COLLECTION("Special Collection"), 
	SPECIAL_DONATION("Special Donation"), 
	OTHERS("Others");
	
	private final String label;
	
	IncomeType(String label) {
		this.label = label;
	}
	
	public String label() {
		return label;
	}

}

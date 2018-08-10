package com.agency.model;

public class CommissionPlan {
	public String planName;
	public String planDescription;
	
	public CommissionPlan(String planName, String planDescription) {
		this.planName = planName;
		this.planDescription = planDescription;
	}
	
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanDescription() {
		return planDescription;
	}	
	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}
}

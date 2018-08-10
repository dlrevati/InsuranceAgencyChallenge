package com.agency.model;

public class AgentCommission {
	//Agent level name
	String name;
	//Agent commission	
	double commissionPercent;
	//Agent hierarchy level
	int hierarchy;	
	//Commission Plan percent
	double commissionPlanPercent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public double getCommissionPercent() {
		return commissionPercent;
	}
	public void setCommissionPercent(double commissionPercent) {
		this.commissionPercent = commissionPercent;
	}
	
	public int getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
	}	
	public double getCommissionPlanPercent() {
		return commissionPlanPercent;
	}
	public void setCommissionPlanPercent(double commissionPlanPercent) {
		this.commissionPlanPercent = commissionPlanPercent;
	}
	
}

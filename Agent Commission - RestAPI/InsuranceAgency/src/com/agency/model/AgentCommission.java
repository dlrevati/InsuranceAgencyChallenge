package com.agency.model;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AgentCommission implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	String name;
	double commissionPercent;
	int hierarchy;	
	double commissionPlanPercent;
	double commissionAmount;
	
	public AgentCommission() {}
	
	public AgentCommission(String name, double commissionPercent, int hierarchy, double commissionPlanPercent,
			double commissionAmount) {
		
		this.name = name;
		this.commissionPercent = commissionPercent;
		this.hierarchy = hierarchy;
		this.commissionPlanPercent = commissionPlanPercent;
		this.commissionAmount = commissionAmount;
	}

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

	public double getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}


}

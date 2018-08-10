package com.agency.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CalculateCommission {
	 public double policyAmount;
	 public String commissionPlan;
	 public int agentCount;
	 
	 public CalculateCommission() {}
		
	 public CalculateCommission(double policyAmount, String commissionPlan, int agentCount) {
		super();
		this.policyAmount = policyAmount;
		this.commissionPlan = commissionPlan;
		this.agentCount = agentCount;
	}
	public double getPolicyAmount() {
		return policyAmount;
	}

	public void setPolicyAmount(double policyAmount) {
		this.policyAmount = policyAmount;
	}

	public String getCommissionPlan() {
		return commissionPlan;
	}

	public void setCommissionPlan(String commissionPlan) {
		this.commissionPlan = commissionPlan;
	}

	public int getAgentCount() {
		return agentCount;
	}

	public void setAgentCount(int agentCount) {
		this.agentCount = agentCount;
	}

}

package com.agency.calc.impl;

import java.text.DecimalFormat;
import java.util.*;
import com.agency.calc.ICalculate;
import com.agency.context.AppContext;
import com.agency.factory.AgentFactory;
import com.agency.model.AgentCommission;
import com.agency.ui.UIHelper;

public class CalculateController implements ICalculate{
	LinkedHashMap<AgentCommission, Double> commissionMap;
	char commissionPlan='A'; //default to Commission Plan A
	public void calculateCommission() {
		getInputsAndCalculate();
		displayResults();
	}

	public void getInputsAndCalculate() {
		char choice='n';
		int agentCount=0;// denotes hierarchy
		
		int agentsThreshold=AppContext.getInstance().getAgentsThreshold();
		UIHelper.writeToDisplay("Enter the policy amount:");
		double policyAmount= Integer.parseInt(UIHelper.getInputFromUser());
		UIHelper.writeToDisplay("Select Commission Plan [A or B...]: ");
		commissionPlan= UIHelper.getInputFromUserChar();
		
		boolean isCommissionPlanCorrect=AppContext.getInstance().setCommisionPlanValues(commissionPlan);
		if(isCommissionPlanCorrect)
		{
			List<AgentCommission> listofAgents=new ArrayList<AgentCommission>();
			do {			
				AgentCommission agent= getAgent(agentCount);
				if(agent!=null) {
					listofAgents.add(agent);
					UIHelper.writeToDisplay(agent.getName()+" added");
				}
				agentCount+=1;
				if(agentCount<=agentsThreshold) {
					UIHelper.writeToDisplay("Do you have any super agents? [Y/N]");
					choice= UIHelper.getInputFromUserChar();
				}else {
					UIHelper.writeToDisplay("Can have only upto "+ agentsThreshold + " agents." );
				}		
				
			}while(Character.toUpperCase(choice)=='Y' && agentCount<=agentsThreshold);	

			/*System.out.println("Agents:");
			for(AgentCommission currAgent:listofAgents) {
				System.out.println(currAgent.getName()+ "-"+ currAgent.getCommissionPercent()+" - "+ currAgent.getCommissionPlanPercent());
			}*/
			calculateCommsionForAgent(policyAmount,listofAgents,commissionPlan);			
		}
	}

	private AgentCommission getAgent(int hierarchy) {
		AgentFactory afInstance=AgentFactory.getInstance();
		AgentCommission currentAgent=afInstance.getAgentCommission(hierarchy);		
		return currentAgent;
	}
	
	private void calculateCommsionForAgent(double policyAmount, List<AgentCommission> listofAgents,
			char commissionPlan) 
	{
		commissionMap=new LinkedHashMap<AgentCommission, Double>();
		for(AgentCommission agent: listofAgents)
		{
			double commissionAmount;
			commissionAmount= (agent.getCommissionPlanPercent()*agent.getCommissionPercent()*policyAmount)/10000;
			//System.out.println(agent.getName()+" gets "+ (int)agent.getCommissionPlanPercent()+ "%" +" of the agent commission % of policy amount =$"+(int)commissionAmount);
		
			commissionMap.put(agent, commissionAmount);	
		}
		
	}
	
	private void displayResults() 
	{
		if(commissionMap!=null) 
		{
			DecimalFormat df2 = new DecimalFormat("#.##");
			for(Map.Entry<AgentCommission, Double> entry:commissionMap.entrySet()) {
				AgentCommission agent=entry.getKey();
				double commissionAmount=entry.getValue();
				System.out.println(agent.getName()+" gets "+ df2.format(agent.getCommissionPlanPercent())+ "%" +" of the agent commission % of policy amount = $"+df2.format(commissionAmount));
			}
		}
		
	}

}

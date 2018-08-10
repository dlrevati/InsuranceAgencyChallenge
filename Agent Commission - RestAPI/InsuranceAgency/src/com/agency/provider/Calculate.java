package com.agency.provider;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.agency.model.AgentCommission;
import com.agency.startup.AgentFactory;

public class Calculate {
	
	private static Calculate calculateInstance;
	
	public static Calculate getInstance()
	{
		if(calculateInstance==null)
		{
			calculateInstance=new Calculate();
		}
		return calculateInstance;
	}
	
	public LinkedHashMap<AgentCommission, Double> calculateCommission(double policyAmount, int agentCount, char commissionPlan) {
		List<AgentCommission> listOfAgents=new ArrayList<AgentCommission>();
		for(int i=0;i<agentCount;i++) {
			AgentCommission agent= getAgent(i);
			if(agent!=null) {
				listOfAgents.add(agent);
			}
		}
		/*System.out.println("Agent count:"+ agentCount);
		System.out.println("Agents:");
		for(AgentCommission currAgent:listOfAgents) {
			System.out.println(currAgent.getName()+ "-"+ currAgent.getCommissionPercent()+" - "+ currAgent.getCommissionPlanPercent());
		}*/
		LinkedHashMap<AgentCommission, Double> commissionMap=calculateCommssionForAgent(policyAmount, listOfAgents, commissionPlan);
		return commissionMap;
	}
	private AgentCommission getAgent(int hierarchy) {
		AgentFactory afInstance=AgentFactory.getInstance();
		AgentCommission currentAgent=afInstance.getAgentCommission(hierarchy);		
		return currentAgent;
	}
	private LinkedHashMap<AgentCommission, Double> calculateCommssionForAgent(double policyAmount, List<AgentCommission> listofAgents, char commissionPlan) 
	{
		LinkedHashMap<AgentCommission, Double> commissionMap=new LinkedHashMap<AgentCommission, Double>();
		for(AgentCommission agent: listofAgents)
		{
			double commissionAmount;
			commissionAmount= (agent.getCommissionPlanPercent()*agent.getCommissionPercent()*policyAmount)/10000;
			//System.out.println(agent.getName()+" gets "+ (int)agent.getCommissionPlanPercent()+ "%" +" of the agent commission % of policy amount =$"+(int)commissionAmount);
		    commissionMap.put(agent, commissionAmount);	
		}
		return commissionMap;
	}
	public List<String> displayResults(LinkedHashMap<AgentCommission, Double> commissionMap) 
	{
		List<String> agentCommissionResults=null;
		if(commissionMap!=null) 
		{
			agentCommissionResults=new ArrayList<String>();
			DecimalFormat df2 = new DecimalFormat("#.##");
			for(Map.Entry<AgentCommission, Double> entry:commissionMap.entrySet()) {
				AgentCommission agent=entry.getKey();
				double commissionAmount=entry.getValue();
				agentCommissionResults.add(agent.getName()+" gets $"+ df2.format(commissionAmount) +" which is "+ df2.format(agent.getCommissionPlanPercent())+ "% of the commission amount of the policy");
			}
		}
		//AgentFactory.getInstance().displayAllAgents();
		return agentCommissionResults;		
	}
}

package com.agency.factory;

import java.util.HashMap;
import java.util.List;

import com.agency.model.AgentCommission;

public class AgentFactory {
	/** The factory instance. */
	private static AgentFactory factoryInstance;
	
	/** Mapping the hierarchy of agent with the Agent object
	*/
	private HashMap<Integer,AgentCommission> mapWithHierarchy;
	
	/** Mapping the name of agent with the Agent object
	 */
	private HashMap<String,AgentCommission> mapWithName;
	
	private AgentFactory(){
		mapWithHierarchy=new HashMap<Integer,AgentCommission>();
	}

	/**
	 * Gets the single instance of AgentFactory.
	 * @return single instance of AgentFactory
	 */
	public static AgentFactory getInstance()
	{
		if(factoryInstance==null)
		{
			factoryInstance=new AgentFactory();
		}
		return factoryInstance;
	}
	
	/**
	 * Creates a new AgentCommission object.
	 * @param name the name
	 * @param commissionPercent the commission percent
	 * @param hierarchy the hierarchy level
	 */
	public void createAgentCommission(String name,double commissionPercent, int hierarchy)
	{
		AgentCommission newAgent=new AgentCommission();
		newAgent.setName(name);
		newAgent.setCommissionPercent(commissionPercent);
		newAgent.setHierarchy(hierarchy);
		mapWithHierarchy.put(newAgent.getHierarchy(), newAgent);
	}
	/**
	 * Updates a AgentCommission object.
	 * @param name the name
	 * @param commissionPlanPercent the commission Plan percent
	 * @param hierarchy the hierarchy level
	 */
	public void setCommisionPlanPercent(String name, double commissionPlanPercent,int hierarchy)
	{
		AgentCommission currentAgent= AgentFactory.getInstance().getAgentCommission(hierarchy);
		if(currentAgent.getName().trim().equalsIgnoreCase(name.trim())) 
		{
			currentAgent.setCommissionPlanPercent(commissionPlanPercent);
			mapWithHierarchy.put(currentAgent.getHierarchy(), currentAgent);
		}
		
	}
	
	/**
	 * Gets the AgentCommission.	 *
	 * @param hierarchy the agent hierarchy
	 * @return the Agent-Commission
	 */
	public AgentCommission getAgentCommission(int hierarchy)
	{
		return mapWithHierarchy.get(hierarchy);
	}
	
	/**
	 * Display all Agent-Commission.
	 */
	public void displayAllAgents()
	{
		System.out.println(mapWithHierarchy);
	}
}

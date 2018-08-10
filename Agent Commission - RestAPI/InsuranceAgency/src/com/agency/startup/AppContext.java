package com.agency.startup;
import java.util.HashMap;

import com.agency.common.AgentCommissionConstants;
import com.agency.model.AgentCommission;
import com.agency.model.CommissionPlan;
import com.agency.startup.AgentFactory;

/**
* AppContext for storing Application configurations
*/
public class AppContext {

	/** The app context instance. */
	private static AppContext appContextInstance;
	/**
	 * Instantiates a new app context.
	 */
	private AppContext() {
	}

	/**
	 * Gets the single instance of AppContext
	 * @return single instance of AppContext
	 */
	public static AppContext getInstance() {
		if (appContextInstance == null) {
			appContextInstance = new AppContext();
		}
		return appContextInstance;
	}
	/** The agent threshold*/
	private int  agentsThreshold;
	
	/** The commission plans */
	private CommissionPlan[] commissionPlansList;	
	
	/** The agent agent-commission hierarchy*/
	private String agentCommissions;
		
	/** The commission plans*/
	private String commissionPlans;
	
	/** Mapping the name of commission plan with the plan values
	 */
	private HashMap<String,String> commissionPlanMap;
	
	public int getAgentsThreshold() {
		return agentsThreshold;
	}

	public void setAgentsThreshold(int agentsThreshold) {
		this.agentsThreshold = agentsThreshold;
	}

	public String getAgentCommissions() {
		return agentCommissions;
	}

	public void setAgentCommissions(String agentCommissions) {
		this.agentCommissions = agentCommissions;
	}

	public String getCommissionPlans() {
		return commissionPlans;
	}

	public void setCommissionPlans(String commissionPlans) {
		this.commissionPlans = commissionPlans;
	}	
	public CommissionPlan[] getCommissionPlansList() {
		return commissionPlansList;
	}

	public void setCommissionPlansList(CommissionPlan[] commissionPlansList) {
		this.commissionPlansList = commissionPlansList;
	}	

	/**
	 * Creates the agent-commissions.
	 * Setting the values of the name, commission percent and hierarchy
	 */
	public void createAgentCommissions() {
		if (agentCommissions != null && agentCommissions.length() > 0) {
			String[] list_of_agents= agentCommissions
					.split(AgentCommissionConstants.AGENT_SPLITTER);
			int numOfAgents=list_of_agents.length;
			for (int i=0;i<numOfAgents;i++) 
			{
				String[] agentAndCommission = list_of_agents[i].split(AgentCommissionConstants.AGENT_COMMISSION_SPLITTER);
				AgentFactory.getInstance().createAgentCommission(agentAndCommission[0],
						Double.parseDouble(agentAndCommission[1]),i);
			}
		}
		
		//set commission plan map on loading
		if (commissionPlans != null && commissionPlans.length() > 0) {
			
			commissionPlanMap=new HashMap<String,String>();
			String[] list_of_commissionPlans= commissionPlans
					.split(AgentCommissionConstants.COMMISSION_PLAN_SPLITTER);
			commissionPlansList= new CommissionPlan[list_of_commissionPlans.length];
			for(int i=0;i<list_of_commissionPlans.length;i++) {
				String plan=list_of_commissionPlans[i];
				String[] commissionPlanAndAgents= plan
						.split(AgentCommissionConstants.COMMISSION_PLAN_AND_AGENTS_SPLITTER);
				commissionPlanMap.put(commissionPlanAndAgents[0].toUpperCase(), commissionPlanAndAgents[1]);
				commissionPlansList[i]= new CommissionPlan(commissionPlanAndAgents[0].toUpperCase(), commissionPlanAndAgents[1]);
			}
			/*System.out.println("Commission Plans Map: \n"+commissionPlanMap +"\n List:");
			for(CommissionPlan p: commissionPlansList ) {
				System.out.println(p.getPlanName()+"-"+p.getPlanDescription());
			}*/
		}
	}
	/**
	 * Setting the percentage value of Commission Plan to agent-commission objects
	 */
	public boolean setCommisionPlanValues(Character commissionPlanName) 
	{
			if(commissionPlanMap.containsKey(commissionPlanName.toString().toUpperCase())) {
				String planValues=commissionPlanMap.get(commissionPlanName.toString().toUpperCase());
				String[] list_of_agents= planValues.split(AgentCommissionConstants.AGENT_SPLITTER);
				int numOfAgents=list_of_agents.length;
				for (int i=0;i<numOfAgents;i++) 
				{
					String[] agentAndCommission = list_of_agents[i].split(AgentCommissionConstants.AGENT_COMMISSION_SPLITTER);
					AgentFactory.getInstance().setCommisionPlanPercent(agentAndCommission[0],
							Double.parseDouble(agentAndCommission[1]), i);				
				}
				return true;
				
			}else {
				System.out.println("There is no Commission Plan "+ commissionPlanName.toString().toUpperCase());
				return false;
			}
		
	}
	
}



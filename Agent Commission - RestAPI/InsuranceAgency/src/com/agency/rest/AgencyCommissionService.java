package com.agency.rest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.agency.model.AgentCommission;
import com.agency.model.CalculateCommission;
import com.agency.provider.Calculate;
import com.agency.startup.AppContext;

@Path("/api")
public class AgencyCommissionService {

	@GET
	@Path("getAppContext")
	@Produces({ MediaType.APPLICATION_JSON })
	public AppContext getAppContext() {
		AppContext context= AppContext.getInstance();
		if(context!=null) {
			return context;
		}else {
			return null;
		}		
	}
	
	@POST
	@Path("calculate")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces( MediaType.APPLICATION_JSON )
	public List<String> calculateCommission(CalculateCommission input) {
		
		List<String> agentCommissionResults=new ArrayList<String>();
		int agentsThreshold=AppContext.getInstance().getAgentsThreshold();
		if(input.agentCount>agentsThreshold) {
			String message="Can have only upto "+ agentsThreshold + " agents." ;
			agentCommissionResults.add(message);
			return agentCommissionResults;
		}
		 boolean isCommissionPlanCorrect=AppContext.getInstance().setCommisionPlanValues(input.commissionPlan.charAt(0));
		 if(isCommissionPlanCorrect)
		 {
			 LinkedHashMap<AgentCommission, Double> commissionMap=Calculate.getInstance().calculateCommission(input.policyAmount,input.agentCount,input.commissionPlan.charAt(0)); 
			agentCommissionResults=Calculate.getInstance().displayResults(commissionMap);	
		 } 
		 else
		 {
			 	String message="There is no Commission Plan "+ input.commissionPlan.toUpperCase();
			 	agentCommissionResults.add(message);
		 }
		return agentCommissionResults;
	}

}
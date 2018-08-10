package com.agency.startup;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.agency.common.AgentCommissionConstants;
/**
 * Helper class for Loading AgentCommission App configuration
 */
public class ContextLoader {
	Properties configProperties = null;
	/**
	 * Load context.
	 */
	public void loadContext(InputStream input) {
		
		try {
			configProperties = new Properties();		
			configProperties.load(input);
			//System.out.println(configProperties);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Sets the application context.
	 */
	public void setApplicationContext() {
		AppContext context = AppContext.getInstance();
		if (configProperties != null && configProperties.size() > 0) {
			context.setAgentCommissions(configProperties
					.getProperty(AgentCommissionConstants.AGENT_COMMISSION_HIERARCHY));						
			context.setCommissionPlans(configProperties
					.getProperty(AgentCommissionConstants.COMMISSION_PLANS));
			
			context.createAgentCommissions();
			
			try {
				int agents_threshold = Integer.parseInt(configProperties.getProperty(AgentCommissionConstants.NUMBER_OF_AGENTS_THRESHOLD));
				context.setAgentsThreshold(agents_threshold);
			} catch (NumberFormatException nfexception) {
				System.out.println(nfexception.getMessage());
			}

		} else {
			System.out.println(AgentCommissionConstants.CANNOT_LOAD_CONTEXT);			
		}
	}

}


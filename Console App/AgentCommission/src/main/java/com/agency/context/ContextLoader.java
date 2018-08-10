package com.agency.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import com.agency.common.AgentCommissionConstants;
import com.agency.ui.UIHelper;
import com.agency.utils.FileUtils;
import com.agency.utils.IOUtils;
/**
 * Helper class for Loading AgentCommission App configuration
 */
public class ContextLoader {
	Properties configProperties = null;

	/**
	 * Load context.
	 */
	public void loadContext() {
		FileInputStream propertyStream = null;
		try {
			File configFile = FileUtils.getFile(getConfigurationFile());
			configProperties = new Properties();
			propertyStream = new FileInputStream(configFile);
			configProperties.load(propertyStream);
			//System.out.println(configProperties);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (propertyStream != null) {
				try {
					propertyStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Gets the configuration file.
	 * @return the configuration file
	 */
	private String getConfigurationFile() {
		StringBuilder configFilePath = new StringBuilder();
		configFilePath.append(IOUtils.getCurrentDirectory());
		configFilePath.append(File.separator);
		configFilePath.append(AgentCommissionConstants.CONFIG_DIR);
		configFilePath.append(File.separator);
		configFilePath.append(AgentCommissionConstants.CONFIG_FILE);
		//return path to properties file
		return configFilePath.toString();
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
				UIHelper.writeToDisplay(nfexception.getMessage());
				// Threshold will be get at run time.
			}

		} else {
			context.setGetConfigurationAtRunTime(true);
			UIHelper.writeToDisplay(AgentCommissionConstants.CANNOT_LOAD_CONTEXT);
		}
	}

}

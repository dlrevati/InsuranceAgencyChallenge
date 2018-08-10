package com.agency.startup;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.agency.common.AgentCommissionConstants;
import com.agency.startup.ContextLoader;

/**
 * Servlet implementation class StartUpServlet
 */
public class StartUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartUpServlet() {
        super();       
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Loading startup configurations..");
		ContextLoader appContextLoader=new ContextLoader();
		InputStream input = config.getServletContext().getResourceAsStream(getConfigurationFile());
		appContextLoader.loadContext(input);
		appContextLoader.setApplicationContext();
	}
	/**
	 * Gets the properties file.
	 * @return the path to properties file
	 */
	private String getConfigurationFile() {
		StringBuilder configFilePath = new StringBuilder();
		configFilePath.append("/");
		configFilePath.append(AgentCommissionConstants.CONFIG_DIR);
		configFilePath.append("/");
		configFilePath.append(AgentCommissionConstants.CONFIG_FILE);		
		//System.out.println(configFilePath.toString());
		return configFilePath.toString();
	}

}

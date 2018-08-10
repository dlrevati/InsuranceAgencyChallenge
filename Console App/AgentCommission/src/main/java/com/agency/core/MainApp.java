package com.agency.core;

import com.agency.calc.impl.CalculateController;
import com.agency.common.AgentCommissionConstants;
import com.agency.context.ContextLoader;
import com.agency.ui.UIHelper;

public class MainApp 
{
    public static void main( String[] args )
    {
    	UIHelper.writeToDisplay(AgentCommissionConstants.TITLE);
		ContextLoader appContextLoader=new ContextLoader();
		appContextLoader.loadContext();
		appContextLoader.setApplicationContext();
		CalculateController controller=new CalculateController();
		controller.calculateCommission();
    }
		
}

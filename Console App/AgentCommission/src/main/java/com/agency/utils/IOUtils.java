package com.agency.utils;

/**
* Utils class for handling IO Operations
*/
public class IOUtils {
	/**
	 * Gets the current directory.	 *
	 * @return the current directory
	 */
	public static String getCurrentDirectory(){
		//System.out.println(System.getProperty("user.dir"));
		return System.getProperty("user.dir");
	}
}

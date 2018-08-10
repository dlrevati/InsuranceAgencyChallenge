package com.agency.utils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Utils for File handling mechanisms
 */
public class FileUtils {

	/**
	 * Gets the file.
	 * @param filePath- the path of the file
	 * @return File
	 * @throws FileNotFoundException the file not found exception
	 */
	public static File getFile(String filePath) throws FileNotFoundException
	{
		File l_objFile=new File(filePath);
		if(!l_objFile.exists())
		{
			throw new FileNotFoundException(filePath);
		}
		return l_objFile;
	}
}


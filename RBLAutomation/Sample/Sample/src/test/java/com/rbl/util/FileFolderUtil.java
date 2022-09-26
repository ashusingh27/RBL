package com.rbl.util;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;



public class FileFolderUtil {
	
	

	private static String folderNameStr;


		
	
	public static void copyPastingReportToExecutionFolder() {

    	CommonCode commonCode=new CommonCode();
		String executionFolderName=commonCode.readConfig().getProperty("ExecutionReport");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 folderNameStr=timestamp.toString().replace(":", "_");
		 
        File testNgLocation= new File(System.getProperty("user.dir")+"\\test-output\\Default suite");
        File testNgNewFolder= new File(executionFolderName+"\\ExecutionReport-"+folderNameStr+"\\TestNGReport");
        File targeFolder= new File(System.getProperty("user.dir")+"\\target\\custom-reports");
        File targetLocation = new File(executionFolderName+"\\ExecutionReport-"+folderNameStr);

        try {
			FileUtils.copyDirectory(testNgLocation, testNgNewFolder);
			FileUtils.copyDirectory(targeFolder, targetLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	 	
	
}



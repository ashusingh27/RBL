package com.rbl.util;

import java.util.Map;

public class StatusRestAPI {
	
	private StatusRequestBody objStatusRequestBody;
	
	public StatusRestAPI() {
		objStatusRequestBody=new StatusRequestBody();
	}

	public void statusRestAPIExecutin(String mandateType,Map<String,String> transactionReferNoMap) {
		for(Map.Entry<String,String> entry : transactionReferNoMap.entrySet()) {
			if(entry.getKey().equalsIgnoreCase("mandateType"))
			{
				String transactionReferNoStr=entry.getValue();
				
			}
		}
	}
}

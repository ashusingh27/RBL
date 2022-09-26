$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/com/features/rbl/RBLApi.feature");
formatter.feature({
  "name": "RBL - STP Workflow",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I am requesting \"BaseURI\".",
  "keyword": "Given "
});
formatter.match({
  "location": "RBLStepDefinition.i_am_requesting(String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Scanario_Id_STP",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Duplicate"
    }
  ]
});
formatter.step({
  "name": "I want to \"/ILMAPI/LMAPI/ADDTRANSACTIONTEST\" and \"post\" API Request saved in JSON File \"Payment_I.json\" without charset",
  "rows": [
    {
      "cells": [
        "MANDATETYPE",
        "AMOUNT",
        "BENEFICIARYACCOUNTNO",
        "BENEFICIARYIFSC",
        "BENEFICIARYACCOUNTHOLDERNAME",
        "REMITTERNAME"
      ]
    },
    {
      "cells": [
        "N",
        "2300",
        "409000015845",
        "ICIC0000555",
        "Dasi",
        "Dasi"
      ]
    },
    {
      "cells": [
        "D",
        "2300",
        "409000015845",
        "RATN0000001",
        "Dasi",
        "Dasi"
      ]
    },
    {
      "cells": [
        "I",
        "2300",
        "112101505172011",
        "SIMB0002233",
        "Dasi",
        "Dasi"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.i_want_to_and_API_Request_saved_in_JSON_File_without_charset(String,String,String,DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I go to status \"/ILMAPI/LMAPI/STATUSCHECKTEST\" and \"post\" API Request saved in JSON File \"Status_I.json\" without charset",
  "rows": [
    {
      "cells": [
        "MANDATETYPE"
      ]
    },
    {
      "cells": [
        "I"
      ]
    },
    {
      "cells": [
        "D"
      ]
    },
    {
      "cells": [
        "N"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.i_go_to_status_and_API_Request_saved_in_JSON_File_without_charset(String,String,String,DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I am fetch Status response",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_am_fetch_Status_response()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Navigate To Specific RBL Portal",
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.navigate_to_specific_rbl_portal()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Login to User Admin Using Following Credentials :",
  "rows": [
    {
      "cells": [
        "USERNAME",
        "PASSWORD"
      ]
    },
    {
      "cells": [
        "rbladmin",
        "password@2"
      ]
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.login_to_User_Admin_Using_Following_Credentials(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I check a transaction log inquiry using corporate reference number",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_check_a_transaction_log_inquiry_using_corporate_reference_number()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I check a PPS log inquiry of above transaction, for acknowledgement sent to WU",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_check_a_PPS_log_inquiry_of_above_transaction_for_acknowledgement_sent_to_WU()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I Check whether the transaction appears in mandate wise Report or transaction count report under MIS",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_Check_whether_the_transaction_appears_in_mandate_wise_Report_or_transaction_count_report_under_MIS()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I go to Transaction Dashborad for validating response status with application status",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_go_to_Transaction_Dashborad_for_validating_response_status_with_application_status()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I am close browser",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_am_close_browser()"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "I am requesting \"BaseURI\".",
  "keyword": "Given "
});
formatter.match({
  "location": "RBLStepDefinition.i_am_requesting(String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Scanario_Duplicate",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Duplicate"
    }
  ]
});
formatter.step({
  "name": "I want to \"/ILMAPI/LMAPI/ADDTRANSACTIONTEST\" and \"post\" API Request saved in JSON File \"Payment_I.json\" with duplicate data",
  "rows": [
    {
      "cells": [
        "MANDATETYPE",
        "AMOUNT",
        "BENEFICIARYACCOUNTNO",
        "BENEFICIARYIFSC",
        "BENEFICIARYACCOUNTHOLDERNAME",
        "REMITTERNAME"
      ]
    },
    {
      "cells": [
        "N",
        "2300",
        "409000015845",
        "ICIC0000555",
        "Dasi",
        "Dasi"
      ]
    },
    {
      "cells": [
        "D",
        "2300",
        "409000015845",
        "RATN0000001",
        "Dasi",
        "Dasi"
      ]
    },
    {
      "cells": [
        "I",
        "2300",
        "112101505172011",
        "SIMB0002233",
        "Dasi",
        "Dasi"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.i_want_to_and_API_Request_saved_in_JSON_File_with_duplicate_data(String,String,String,DataTable)"
});
formatter.result({
  "error_message": "java.net.UnknownHostException: rbllastmile.remit.in\r\n\tat java.net.Inet6AddressImpl.lookupAllHostAddr(Native Method)\r\n\tat java.net.InetAddress$2.lookupAllHostAddr(Unknown Source)\r\n\tat java.net.InetAddress.getAddressesFromNameService(Unknown Source)\r\n\tat java.net.InetAddress.getAllByName0(Unknown Source)\r\n\tat java.net.InetAddress.getAllByName(Unknown Source)\r\n\tat java.net.InetAddress.getAllByName(Unknown Source)\r\n\tat org.apache.http.impl.conn.SystemDefaultDnsResolver.resolve(SystemDefaultDnsResolver.java:44)\r\n\tat org.apache.http.impl.conn.DefaultClientConnectionOperator.resolveHostname(DefaultClientConnectionOperator.java:259)\r\n\tat org.apache.http.impl.conn.DefaultClientConnectionOperator.openConnection(DefaultClientConnectionOperator.java:159)\r\n\tat org.apache.http.impl.conn.ManagedClientConnectionImpl.open(ManagedClientConnectionImpl.java:304)\r\n\tat org.apache.http.impl.client.DefaultRequestDirector.tryConnect(DefaultRequestDirector.java:611)\r\n\tat org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:446)\r\n\tat org.apache.http.impl.client.AbstractHttpClient.doExecute(AbstractHttpClient.java:863)\r\n\tat org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:82)\r\n\tat org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:57)\r\n\tat org.apache.http.client.HttpClient$execute$0.call(Unknown Source)\r\n\tat io.restassured.internal.RequestSpecificationImpl$RestAssuredHttpBuilder.doRequest(RequestSpecificationImpl.groovy:2146)\r\n\tat io.restassured.internal.http.HTTPBuilder.post(HTTPBuilder.java:349)\r\n\tat io.restassured.internal.http.HTTPBuilder$post$2.call(Unknown Source)\r\n\tat io.restassured.internal.RequestSpecificationImpl.sendRequest(RequestSpecificationImpl.groovy:1274)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n\tat java.lang.reflect.Method.invoke(Unknown Source)\r\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:98)\r\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1225)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1034)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:822)\r\n\tat io.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:47)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:152)\r\n\tat io.restassured.internal.filter.SendRequestFilter.filter(SendRequestFilter.groovy:30)\r\n\tat io.restassured.filter.Filter$filter$0.call(Unknown Source)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:47)\r\n\tat io.restassured.filter.Filter$filter.call(Unknown Source)\r\n\tat io.restassured.internal.filter.FilterContextImpl.next(FilterContextImpl.groovy:72)\r\n\tat io.restassured.filter.time.TimingFilter.filter(TimingFilter.java:56)\r\n\tat io.restassured.filter.Filter$filter.call(Unknown Source)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:47)\r\n\tat io.restassured.filter.Filter$filter.call(Unknown Source)\r\n\tat io.restassured.internal.filter.FilterContextImpl.next(FilterContextImpl.groovy:72)\r\n\tat io.restassured.filter.log.RequestLoggingFilter.filter(RequestLoggingFilter.java:122)\r\n\tat io.restassured.filter.Filter$filter.call(Unknown Source)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:47)\r\n\tat io.restassured.filter.Filter$filter$0.call(Unknown Source)\r\n\tat io.restassured.internal.filter.FilterContextImpl.next(FilterContextImpl.groovy:72)\r\n\tat io.restassured.filter.FilterContext$next.call(Unknown Source)\r\n\tat io.restassured.internal.RequestSpecificationImpl.applyPathParamsAndSendRequest(RequestSpecificationImpl.groovy:1749)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n\tat java.lang.reflect.Method.invoke(Unknown Source)\r\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:98)\r\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1225)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1034)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:822)\r\n\tat io.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:47)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.callCurrent(PogoInterceptableSite.java:57)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185)\r\n\tat io.restassured.internal.RequestSpecificationImpl.applyPathParamsAndSendRequest(RequestSpecificationImpl.groovy:1755)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n\tat java.lang.reflect.Method.invoke(Unknown Source)\r\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:98)\r\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1225)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1034)\r\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:822)\r\n\tat io.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:47)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.callCurrent(PogoInterceptableSite.java:57)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185)\r\n\tat io.restassured.internal.RequestSpecificationImpl.post(RequestSpecificationImpl.groovy:175)\r\n\tat io.restassured.internal.RequestSpecificationImpl.post(RequestSpecificationImpl.groovy)\r\n\tat com.rbl.util.RestAssuredCode.restApiHit(RestAssuredCode.java:54)\r\n\tat com.stepDefinition.API.RBL.RBLStepDefinition.i_want_to_and_API_Request_saved_in_JSON_File_with_duplicate_data(RBLStepDefinition.java:468)\r\n\tat ✽.I want to \"/ILMAPI/LMAPI/ADDTRANSACTIONTEST\" and \"post\" API Request saved in JSON File \"Payment_I.json\" with duplicate data(src/test/java/com/features/rbl/RBLApi.feature:71)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "I am validating object \"[0].ErrorDesc\" for String value \"Duplicate Transaction Id\".",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_am_validating_object_for_String_value(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I go to status \"/ILMAPI/LMAPI/STATUSCHECKTEST\" and \"post\" API Request saved in JSON File \"Status_I.json\" without charset",
  "rows": [
    {
      "cells": [
        "MANDATETYPE"
      ]
    },
    {
      "cells": [
        "I"
      ]
    },
    {
      "cells": [
        "D"
      ]
    },
    {
      "cells": [
        "N"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.i_go_to_status_and_API_Request_saved_in_JSON_File_without_charset(String,String,String,DataTable)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I am fetch Status response",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_am_fetch_Status_response()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Navigate To Specific RBL Portal",
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.navigate_to_specific_rbl_portal()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Login to User Admin Using Following Credentials :",
  "rows": [
    {
      "cells": [
        "USERNAME",
        "PASSWORD"
      ]
    },
    {
      "cells": [
        "rbladmin",
        "password@2"
      ]
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.login_to_User_Admin_Using_Following_Credentials(DataTable)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I check a transaction log inquiry using corporate reference number",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_check_a_transaction_log_inquiry_using_corporate_reference_number()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I check a PPS log inquiry of above transaction, for acknowledgement sent to WU",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_check_a_PPS_log_inquiry_of_above_transaction_for_acknowledgement_sent_to_WU()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I Check whether the transaction appears in mandate wise Report or transaction count report under MIS",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_Check_whether_the_transaction_appears_in_mandate_wise_Report_or_transaction_count_report_under_MIS()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I go to Transaction Dashborad for validating response status with application status",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_go_to_Transaction_Dashborad_for_validating_response_status_with_application_status()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I am close browser",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_am_close_browser()"
});
formatter.result({
  "status": "skipped"
});
});
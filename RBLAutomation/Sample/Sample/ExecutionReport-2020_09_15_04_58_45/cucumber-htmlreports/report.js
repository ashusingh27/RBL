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
      "name": "@RestApiSingle"
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
        "BENEFICIARYIFSC"
      ]
    },
    {
      "cells": [
        "R",
        "200010",
        "409000015845",
        "ICIC0000555"
      ]
    },
    {
      "cells": [
        "N",
        "2300",
        "409000015845",
        "ICIC0000555"
      ]
    },
    {
      "cells": [
        "D",
        "2300",
        "409000015845",
        "RATN0000001"
      ]
    },
    {
      "cells": [
        "I",
        "2300",
        "112101505172011",
        "SIMB0002233"
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
    },
    {
      "cells": [
        "R"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.i_go_to_status_and_API_Request_saved_in_JSON_File_without_charset(String,String,String,DataTable)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat com.rbl.util.RestAssuredCode.statusResponseProcess(RestAssuredCode.java:83)\r\n\tat com.stepDefinition.API.RBL.RBLStepDefinition.i_go_to_status_and_API_Request_saved_in_JSON_File_without_charset(RBLStepDefinition.java:290)\r\n\tat ✽.I go to status \"/ILMAPI/LMAPI/STATUSCHECKTEST\" and \"post\" API Request saved in JSON File \"Status_I.json\" without charset(src/test/java/com/features/rbl/RBLApi.feature:30)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "I am validating object \"Status\" for String value \"Sent to Authorizer\".",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_am_validating_object_for_String_value(String,String)"
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
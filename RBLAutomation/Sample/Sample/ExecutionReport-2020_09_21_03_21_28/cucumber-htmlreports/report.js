$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/com/features/rbl/RBL_Bulk.feature");
formatter.feature({
  "name": "RBL - STP Bulk transaction Workflow",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "RBL - STP Bulk transaction",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestAPI"
    }
  ]
});
formatter.step({
  "name": "I want to execute payment \"\u003cBulkTransaction\u003e\" using excel file \"\u003cExeclFile\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "I want to execute status rest api",
  "keyword": "Then "
});
formatter.step({
  "name": "I am fetch Status response",
  "keyword": "Then "
});
formatter.step({
  "name": "Navigate To Specific RBL Portal",
  "keyword": "When "
});
formatter.step({
  "name": "Login to User Admin Using Following Credentials :",
  "keyword": "Then ",
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
  ]
});
formatter.step({
  "name": "I Check whether the transaction appears in mandate wise Report or transaction count report under MIS",
  "keyword": "Then "
});
formatter.step({
  "name": "I am close browser",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "BulkTransaction",
        "ExeclFile"
      ]
    },
    {
      "cells": [
        "Bulk Transaction for N",
        "RBLBulkData"
      ]
    },
    {
      "cells": [
        "Bulk Transaction for D",
        "RBLBulkData-D"
      ]
    },
    {
      "cells": [
        "Bulk Transaction for I",
        "RBLBulkData-I"
      ]
    },
    {
      "cells": [
        "Bulk Transaction for I,N",
        "RBLBulkData-I-N"
      ]
    }
  ]
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
  "name": "RBL - STP Bulk transaction",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestAPI"
    }
  ]
});
formatter.step({
  "name": "I want to execute payment \"Bulk Transaction for N\" using excel file \"RBLBulkData\"",
  "keyword": "When "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_execute_payment_using_excel_file(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to execute status rest api",
  "keyword": "Then "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_execute_status_rest_api()"
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
  "name": "RBL - STP Bulk transaction",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestAPI"
    }
  ]
});
formatter.step({
  "name": "I want to execute payment \"Bulk Transaction for D\" using excel file \"RBLBulkData-D\"",
  "keyword": "When "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_execute_payment_using_excel_file(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to execute status rest api",
  "keyword": "Then "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_execute_status_rest_api()"
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
  "name": "RBL - STP Bulk transaction",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestAPI"
    }
  ]
});
formatter.step({
  "name": "I want to execute payment \"Bulk Transaction for I\" using excel file \"RBLBulkData-I\"",
  "keyword": "When "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_execute_payment_using_excel_file(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to execute status rest api",
  "keyword": "Then "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_execute_status_rest_api()"
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
  "name": "RBL - STP Bulk transaction",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestAPI"
    }
  ]
});
formatter.step({
  "name": "I want to execute payment \"Bulk Transaction for I,N\" using excel file \"RBLBulkData-I-N\"",
  "keyword": "When "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_execute_payment_using_excel_file(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to execute status rest api",
  "keyword": "Then "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_execute_status_rest_api()"
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
  "name": "I am close browser",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_am_close_browser()"
});
formatter.result({
  "status": "passed"
});
});
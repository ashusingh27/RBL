$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/com/features/rbl/RBL_Bulk.feature");
formatter.feature({
  "name": "RBL - STP Bulk transaction Workflow",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "RBL - STP Bulk transaction for invalid data",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestAPIForInvalid"
    }
  ]
});
formatter.step({
  "name": "I want to execute payment \"\u003cBulkTransaction\u003e\" using excel file \"\u003cExeclFile\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "I want to validate error massage for \"\u003cBulkTransaction\u003e\"",
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
        "Bulk Transaction for N with beneficary name contain caution word",
        "InvalidBeneficiaryAccountNo"
      ]
    },
    {
      "cells": [
        "Bulk Transaction for N with remitter name contain caution word",
        "InvalidRemitterName"
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
  "name": "RBL - STP Bulk transaction for invalid data",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestAPIForInvalid"
    }
  ]
});
formatter.step({
  "name": "I want to execute payment \"Bulk Transaction for N with beneficary name contain caution word\" using excel file \"InvalidBeneficiaryAccountNo\"",
  "keyword": "When "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_execute_payment_using_excel_file(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to validate error massage for \"Bulk Transaction for N with beneficary name contain caution word\"",
  "keyword": "Then "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_validate_error_massage_for(String)"
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
  "name": "RBL - STP Bulk transaction for invalid data",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestAPIForInvalid"
    }
  ]
});
formatter.step({
  "name": "I want to execute payment \"Bulk Transaction for N with remitter name contain caution word\" using excel file \"InvalidRemitterName\"",
  "keyword": "When "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_execute_payment_using_excel_file(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to validate error massage for \"Bulk Transaction for N with remitter name contain caution word\"",
  "keyword": "Then "
});
formatter.match({
  "location": "RBL_BulkStepDefinition.i_want_to_validate_error_massage_for(String)"
});
formatter.result({
  "status": "passed"
});
});
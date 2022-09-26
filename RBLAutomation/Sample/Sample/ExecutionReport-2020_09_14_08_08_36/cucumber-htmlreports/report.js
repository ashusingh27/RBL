$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/com/features/rbl/RBLApi.feature");
formatter.feature({
  "name": "RBL - STP Workflow",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Scanario_Id_STP",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestApiSingle"
    }
  ]
});
formatter.step({
  "name": "I want to payment api with \"\u003cMANDATETYPE\u003e\",\"\u003cAMOUNT\u003e\",\"\u003cBENEFICIARYACCOUNTNO\u003e\",\"\u003cBENEFICIARYIFSC\u003e\" option without charset",
  "keyword": "When "
});
formatter.step({
  "name": "I want to status api with \"\u003cMANDATETYPE\u003e\" option without charset",
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
        "MANDATETYPE",
        "AMOUNT",
        "BENEFICIARYACCOUNTNO",
        "BENEFICIARYIFSC"
      ]
    },
    {
      "cells": [
        "I",
        "2300",
        "112101505172011",
        "SIMB0002233"
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
  "name": "Scanario_Id_STP",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestApiSingle"
    }
  ]
});
formatter.step({
  "name": "I want to payment api with \"I\",\"2300\",\"112101505172011\",\"SIMB0002233\" option without charset",
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.i_want_to_payment_api_with_option_without_charset(String,String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to status api with \"I\" option without charset",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_want_to_status_api_with_option_without_charset(String)"
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
  "name": "Scanario_Id_STP",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestApiSingle"
    }
  ]
});
formatter.step({
  "name": "I want to payment api with \"R\",\"200010\",\"409000015845\",\"ICIC0000555\" option without charset",
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.i_want_to_payment_api_with_option_without_charset(String,String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to status api with \"R\" option without charset",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_want_to_status_api_with_option_without_charset(String)"
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
  "name": "Scanario_Id_STP",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestApiSingle"
    }
  ]
});
formatter.step({
  "name": "I want to payment api with \"N\",\"2300\",\"409000015845\",\"ICIC0000555\" option without charset",
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.i_want_to_payment_api_with_option_without_charset(String,String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to status api with \"N\" option without charset",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_want_to_status_api_with_option_without_charset(String)"
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
  "name": "Scanario_Id_STP",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RestApiSingle"
    }
  ]
});
formatter.step({
  "name": "I want to payment api with \"D\",\"2300\",\"409000015845\",\"RATN0000001\" option without charset",
  "keyword": "When "
});
formatter.match({
  "location": "RBLStepDefinition.i_want_to_payment_api_with_option_without_charset(String,String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I want to status api with \"D\" option without charset",
  "keyword": "Then "
});
formatter.match({
  "location": "RBLStepDefinition.i_want_to_status_api_with_option_without_charset(String)"
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
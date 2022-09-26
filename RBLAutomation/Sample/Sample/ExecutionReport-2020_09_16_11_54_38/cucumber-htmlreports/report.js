$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/com/features/rbl/API.feature");
formatter.feature({
  "name": "Example",
  "description": "  Given I am requesting \"BaseURI\".",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Scanario_Id_STP",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@RestSingle"
    }
  ]
});
formatter.step({
  "name": "I want to \"/ILMAPI/LMAPI/ADDTRANSACTIONTEST\" and \"post\" API Request for \"IMPS\"",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I go to status \"/ILMAPI/LMAPI/STATUSCHECKTEST\" and \"post\" API Request for \"IMPS\"",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I am fetch Status response",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Navigate To Specific RBL Portal",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
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
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I check a transaction log inquiry using corporate reference number",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I check a PPS log inquiry of above transaction, for acknowledgement sent to WU",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I go to Transaction Dashborad for validating response status with application status",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I am close browser",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});
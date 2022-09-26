Feature: Example
  
  Given I am requesting "BaseURI".

  @RestSingle
  Scenario: Scanario_Id_STP
    When I want to "/ILMAPI/LMAPI/ADDTRANSACTIONTEST" and "post" API Request for "I" "Status_I.json"
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      | R           | 200010 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
    When I want to "/ILMAPI/LMAPI/STATUSCHECKTEST" and "post" API Request for "I" "Status_I.json"
    Then I am fetch Status response
    When Navigate To Specific RBL Portal
    Then Login to User Admin Using Following Credentials :
      | USERNAME | PASSWORD   |
      | rbladmin | password@2 |
    Then I check a transaction log inquiry using corporate reference number
    Then I check a PPS log inquiry of above transaction, for acknowledgement sent to WU
    Then I go to Transaction Dashborad for validating response status with application status
    Then I am close browser

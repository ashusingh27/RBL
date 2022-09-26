Feature: RBL - STP Workflow

  Background: 
    Given I am requesting "BaseURI".

  #@Duplicate
  @SingleFlow
  Scenario: Scenario_Id_STP
    When I want to "/ILMAPI/LMAPI/ADDTRANSACTIONTEST" and "post" API Request saved in JSON File "Payment_I.json" without charset
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
    #| D           |   2300 |         409000015845 | RATN0000001     | Dasi                         | Dasi         |
    #| I           |   2300 |      112101505172011 | SIMB0002233     | Dasi                         | Dasi         |
    When I go to status "/ILMAPI/LMAPI/STATUSCHECKTEST" and "post" API Request saved in JSON File "Status_I.json" without charset
      | MANDATETYPE |
      #| I           |
      #| D           |
      | N           |
    #| R           |
    Then I am fetch Status response
    When Navigate To Specific RBL Portal
    Then Login to User Admin Using Following Credentials :
      | USERNAME | PASSWORD   |
      | rbladmin | password@2 |
    Then I check a transaction log inquiry using corporate reference number
    Then I check a PPS log inquiry of above transaction, for acknowledgement sent to WU
    Then I Check whether the transaction appears in mandate wise Report or transaction count report under MIS
    Then I go to Transaction Dashborad for validating response status with application status
    Then I am close browser

  @Duplicate
  Scenario: Scenario_Duplicate
    When I want to "/ILMAPI/LMAPI/ADDTRANSACTIONTEST" and "post" API Request saved in JSON File "Payment_I.json" with duplicate data
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
    #| D           |   2300 |         409000015845 | RATN0000001     | Dasi                         | Dasi         |
    #| I           |   2300 |      112101505172011 | SIMB0002233     | Dasi                         | Dasi         |
    Then I am validating object "[0].ErrorDesc" for String value "Duplicate Transaction Id".
    When I go to status "/ILMAPI/LMAPI/STATUSCHECKTEST" and "post" API Request saved in JSON File "Status_I.json" without charset
      | MANDATETYPE |
      #| I           |
      #| D           |
      | N           |
    #| R           |
    Then I am fetch Status response
    When Navigate To Specific RBL Portal
    Then Login to User Admin Using Following Credentials :
      | USERNAME | PASSWORD   |
      | rbladmin | password@2 |
    Then I check a transaction log inquiry using corporate reference number
    Then I check a PPS log inquiry of above transaction, for acknowledgement sent to WU
    Then I Check whether the transaction appears in mandate wise Report or transaction count report under MIS
    Then I go to Transaction Dashborad for validating response status with application status
    Then I am close browser

  @OtherTechnicalError
  Scenario: STP_Other_then_Technical Error
    When I want to "/ILMAPI/LMAPI/ADDTRANSACTIONTEST" and "post" API Request saved in JSON File "Payment_I.json" without charset
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
    #| D           |   2300 |         409000015845 | RATN0000001     | Dasi                         | Dasi         |
    #| I           |   2300 |      112101505172011 | SIMB0002233     | Dasi                         | Dasi         |
    When I want to "/ILMAPI/LMAPI/ADDTRANSACTIONTEST" and "post" API Request saved in JSON File "Payment_I.json" with duplicate data
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
    #| D           |   2300 |         409000015845 | RATN0000001     | Dasi                         | Dasi         |
    #| I           |   2300 |      112101505172011 | SIMB0002233     | Dasi                         | Dasi         |
    Then I am validating object "[0].ErrorDesc" for String value "Duplicate Transaction Id".
    Then I am close browser

  @InvalidBankAccountNumber
  Scenario: Scenario_Invalid_BankAccountNumber
    When I want to "/ILMAPI/LMAPI/ADDTRANSACTIONTEST" and "post" API Request saved in JSON File "Payment_I.json" without charset
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |                  115 | SIMB0002233     | Dasi                         | Dasi         |
      | N           |   2300 |                  112 | SIMB0002233     | Dasi                         | Dasi         |
    #| D           |   2300 |           1111111111 | RATN0000001     | Dasi                         | Dasi         |
    #| I           |   2300 |                  111 | SIMB0002233     | Dasi                         | Dasi         |
    When I go to status "/ILMAPI/LMAPI/STATUSCHECKTEST" and "post" API Request saved in JSON File "Status_I.json" without charset
      | MANDATETYPE |
      | D           |
    Then I am validating object "[0].ErrorDesc" for Error String value "Error in beneAccountNumber. Minimum length required 4 and Maximum 20,".
    Then I am close browser

  @TotalFreezed
  Scenario: Scenario_TotalFreezed_BankAccountNumber
    When I want to "/ILMAPI/LMAPI/ADDTRANSACTIONTEST" and "post" API Request saved in JSON File "Payment_I.json" without charset
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |      112101505172032 | SIMB0002233     | Dasi                         | Dasi         |
      | N           |     23 |      112101505172032 | SIMB0002233     | Dasi                         | Dasi         |
    #| D           |     23 |         409000849400 | RATN0000001     | Dasi                         | Dasi         |
    #| I           |     23 |      112101505172021 | SIMB0002233     | Dasi                         | Dasi         |
    When I go to status "/ILMAPI/LMAPI/STATUSCHECKTEST" and "post" API Request saved in JSON File "Status_I.json" without charset
      | MANDATETYPE |
      #| I           |
      #| D           |
      | N           |
    #| R           |
    Then I am validating error massage
    Then I am close browser

  @InactiveBankAccountNumber
  Scenario: Scenario_Inactive_BankAccountNumber
    When I want to "/ILMAPI/LMAPI/ADDTRANSACTIONTEST" and "post" API Request saved in JSON File "Payment_I.json" without charset
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      | D           |    230 |         409000645831 | RATN0000001     | Dasi                         | Dasi         |
    When I go to status "/ILMAPI/LMAPI/STATUSCHECKTEST" and "post" API Request saved in JSON File "Status_I.json" without charset
      | MANDATETYPE |
      | D           |
    Then I am validating object "ErrorDesc" for Error for Mandate D String value "Account not allowed for credit".
    Then I am close browser

  @TechnicalError
  Scenario Outline: Scenario_Technical_Error
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>" to check technical eror
    Then I am going to execute status rest api
    Then I am validating jsonobject "ErrorDesc" for error  String value "Technical Failure".
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>" for duplicate
    Then I am going to execute status rest api
    #Then I am going to run status rest api for STP
    Then I am going to validating response
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      | R           | 200010 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Dasi                         | Dasi         |
      | D           |   2300 |         409000015845 | RATN0000001     | Dasi                         | Dasi         |
      | I           |   2300 |      112101505172011 | SIMB0002233     | Dasi                         | Dasi         |

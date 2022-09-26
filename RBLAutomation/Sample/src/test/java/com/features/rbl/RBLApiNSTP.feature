Feature: RBL - NSTP Workflow

  Background: 
    Given I am requesting NSTP "BaseURI".
    
    @NSTP_SingleFlow
  Scenario Outline: Scenario_NSTP_SingleFlow
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by maker
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to Check whether same  transaction can be again initiated by maker
    Then Login to User Using Following Credentials :
      | USERNAME       | PASSWORD   |
      | RBLOPSCHECKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by checker
    Then Login to User Using Following Credentials :
      | USERNAME       | PASSWORD   |
      | RBLOPSCHECKER1 | password@2 |
    Then I am going to Check whether same  transaction can be authorised again  by checker
    Then I am going to run status rest api
    #Then I am validating jsonobject "PaymentStatus" for NSTP String value "Success".
    Then I am going to validating status API response
    Then Login to User Using Following Credentials :
      | USERNAME | PASSWORD   |
      | rbladmin | password@2 |
    Then I check a transaction log inquiry using corporate reference number for NSTP
    Then I check a PPS log inquiry of above transaction, for acknowledgement sent to WU for NSTP
    Then I Check whether the transaction appears in mandate wise Report or transaction count report under MIS for NSTP
    Then I go to Transaction Dashborad for validating response status with application status for NSTP
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      #| D           |   2300 |         409000015845 | RATN0000001     | Chit                         | Chit         |
      #| I           |   2300 |      112101505172011 | SIMB0002233     | Chit                         | Chit         |

    

  @TechnicalErrorNSTP
  Scenario: Scenario_NSTP_Technical_Error
    When I want to  "/ILMAPI/LMAPI/ADDTRANSACTIONTEST" and "post" NSTP API Request saved in JSON File "TechnicalErrorNSTP.json" without charset
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      #| D           |   2300 |         409000015845 | RATN0000001     | Chit                         | Chit         |
      #| I           |   2300 |      112101505172011 | SIMB0002233     | Chit                         | Chit         |
    Then I am validating object "ErrorDesc" for NSTP String value "Technical Failure".
    Then I am closing browser for NSTP

  @InvalidBankAccountNumberNSTP
  Scenario: Scenario_NSTP_Invalid_BankAccountNumber
    When I want to  "/ILMAPI/LMAPI/ADDTRANSACTIONTEST" and "post" NSTP API Request saved in JSON File "Payment_I.json" without charset
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |                  115 | SIMB0002233     | Chit                         | Chit         |
      #| N           |   2300 |                  112 | SIMB0002233     | Chit                         | Chit         |
      | D           |   2300 |         111111111111 | RATN0000001     | Chit                         | Chit         |
    #| I           |   2300 |                  111 | SIMB0002233     | Chit                         | Chit         |
    When I go to status "/ILMAPI/LMAPI/STATUSCHECKTEST" and "post" NSTP API Request saved in JSON File "Status_I.json" without charset
      | MANDATETYPE |
      | D           |
    Then I am validating object "[0].ErrorDesc" for NSTP Error String value "Error in beneAccountNumber. Minimum length required 4 and Maximum 20,".
    Then I am closing browser for NSTP

    @AgainInitiated 
  Scenario Outline: Scenario_NSTP_Maker_Can_Not_Initiated_Again
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by maker
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to Check whether same  transaction can be again initiated by maker
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      #| D           |   2300 |         409000015845 | RATN0000001     | Chit                         | Chit         |
      #| I           |   2300 |      112101505172011 | SIMB0002233     | Chit                         | Chit         |

  @AgainAuthorised 
  Scenario Outline: Scenario_NSTP_check_whether_same_transaction_can_be_authorised_again_by_him
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by maker
    Then Login to User Using Following Credentials :
      | USERNAME       | PASSWORD   |
      | RBLOPSCHECKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by checker
    Then Login to User Using Following Credentials :
      | USERNAME       | PASSWORD   |
      | RBLOPSCHECKER1 | password@2 |
    Then I am going to Check whether same  transaction can be authorised again  by checker
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      #| D           |   2300 |         409000015845 | RATN0000001     | Chit                         | Chit         |
      #| I           |   2300 |      112101505172011 | SIMB0002233     | Chit                         | Chit         |

  @TechnicalError
  Scenario Outline: Scenario_Technical_Error
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>" to check technical eror
    Then I am going to execute status rest api
    Then I am validating jsonobject "ErrorDesc" for error  String value "Technical Failure".
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>" for duplicate
    Then I am going to execute status rest api
    Then I am going to validating response
    #Then I am validating jsonobject "PaymentStatus" for NSTP String value "Hold".
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      #| D           |   2300 |         409000015845 | RATN0000001     | Chit                         | Chit         |
      #| I           |   2300 |      112101505172011 | SIMB0002233     | Chit                         | Chit         |

  @TwoChecker
  @Execute
  Scenario Outline: Scenario_NSTP_check_whether_same_transaction_can_be_authorised_again_by_him
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by maker
    #Then I am going to Check whether same  transaction can be again initiated by maker
    Then Login to User Using Following Credentials :
      | USERNAME       | PASSWORD   |
      | RBLOPSCHECKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by checker
    Then Login to User Using Following Credentials :
      | USERNAME      | PASSWORD   |
      | rblopschecker | password@2 |
    Then I am going to Check whether same  transaction can be again authorise by checker
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      #| D           |   2300 |         409000015845 | RATN0000001     | Chit                         | Chit         |
      #| I           |   2300 |      112101505172011 | SIMB0002233     | Chit                         | Chit         |

  @TwoMaker
  @Execute
  Scenario Outline: Scenario_NSTP_check_whether_same_transaction_can_be_authorised_again_by_him
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by maker
    #Then I am going to Check whether same  transaction can be again initiated by maker
    Then Login to User Using Following Credentials :
      | USERNAME    | PASSWORD   |
      | Rblopsmaker | password@2 |
    Then I am going to Check whether same  transaction can be again initiated by another maker
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      | N           |   2300 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      #| D           |   2300 |         409000015845 | RATN0000001     | Chit                         | Chit         |
      #| I           |   2300 |      112101505172011 | SIMB0002233     | Chit                         | Chit         |

  @InvalidData
  @Execute
  Scenario Outline: Scenario_NSTP_Invalid_Data
    When I want to execute "<TestCaseName>" for "<MANDATETYPE>"
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    Then I am going to execute status rest api
    Then I am going to validating error massage in response
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME | TestCaseName                                            |
      #| N           | 10000001 |         409000015845 | ICIC0000555     | Chit                         | Chit         | balance above defined threshold goes for NSTP |
      #| D           | 10000001 |         409000015845 | RATN0000001     | Chit                         | Chit         | balance above defined threshold goes for NSTP |
      #| I           | 10000001 |      112101505172011 | SIMB0002233     | Chit                         | Chit         | balance above defined threshold goes for NSTP |
      #| R           | 10000001 |         409000015845 | ICIC0000555     | Chit                         | Chit         | balance above defined threshold goes for NSTP |
      | N           |     23 |         409000015845 | ICIC0000555     | Aashram                      | Chit         | beneficiary name containing caution words goes for NSTP |
      #| D           |     23 |         409000015845 | RATN0000001     | Aashram                      | Chit         | beneficiary name containing caution words goes for NSTP |
      #| I           |     23 |      112101505172011 | SIMB0002233     | Aashram                      | Chit         | beneficiary name containing caution words goes for NSTP |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Aashram                      | Chit         | beneficiary name containing caution words goes for NSTP |

  #| R           | 200010 |         409000015845 | ICIC0000555     | Chit                         | Aashram      | remitter name containing caution words goes for NSTP |
  #| N           |   2300 |         409000015845 | ICIC0000555     | Chit                         | Aashram      | remitter name containing caution words goes for NSTP |
  #| D           |   2300 |         409000015845 | RATN0000001     | Chit                         | Aashram      | remitter name containing caution words goes for NSTP |
  #| I           |   2300 |      112101505172011 | SIMB0002233     | Chit                         | Aashram      | remitter name containing caution words goes for NSTP |
  @Duplicate
  @Execute
  Scenario Outline: Scenario_NSTP_a_transaction_reference_number_which_is_already_used
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by maker
    Then Login to User Using Following Credentials :
      | USERNAME       | PASSWORD   |
      | RBLOPSCHECKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by checker
    Then I am going to execute status rest api
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>" for duplicate
    Then I am going to compare duplicate response status with normal response
    Then Login to User Using Following Credentials :
      | USERNAME | PASSWORD   |
      | rbladmin | password@2 |
    Then I check a transaction log inquiry using corporate reference number for NSTP
    Then I check a PPS log inquiry of above transaction, for acknowledgement sent to WU for NSTP
    Then I Check whether the transaction appears in mandate wise Report or transaction count report under MIS for NSTP
    Then I go to Transaction Dashborad for validating response status with application status for NSTP
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      # | R           | 200010 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      #| N           |   2300 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      | D           |   2300 |         409000015845 | RATN0000001     | Chit                         | Chit         |

  #| I           |   2300 |      112101505172011 | SIMB0002233     | Chit                         | Chit         |
  @OtherThenTechnical
  @Execute
  Scenario Outline: Scenario_NSTP_transaction_other_than_failure_due_to_technical_error
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by maker
    Then Login to User Using Following Credentials :
      | USERNAME       | PASSWORD   |
      | RBLOPSCHECKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by checker
    Then I am going to execute status rest api
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>" for duplicate
    Then I am going to compare duplicate response status with normal response
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>" for duplicate
    Then I am going to validate duplicate response status with normal response
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      #| N           |   2300 |         409000015845 | ICIC0000555     | Chit                         | Chit         |
      #| D           |   2300 |         409000015845 | RATN0000001     | Chit                         | Chit         |
      | I           |   2300 |      112101505172011 | SIMB0002233     | Chit                         | Chit         |

  @TotalFreezed
  @Execute
  Scenario Outline: Scenario_NSTP_Transaction_for_a_Total_freezed_account
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by maker
    Then Login to User Using Following Credentials :
      | USERNAME       | PASSWORD   |
      | RBLOPSCHECKER1 | password@2 |
    Then I am going to Check whether above transactions can be processed by checker
    Then I am going to run status rest api
    #Then I am validating jsonobject "PaymentStatus" for NSTP String value "Success".
    Then I am going to validating error
    Then Login to User Using Following Credentials :
      | USERNAME | PASSWORD   |
      | rbladmin | password@2 |
    Then I check a transaction log inquiry using corporate reference number for NSTP
    Then I check a PPS log inquiry of above transaction, for acknowledgement sent to WU for NSTP
    Then I Check whether the transaction appears in mandate wise Report or transaction count report under MIS for NSTP
    Then I go to Transaction Dashborad for validating response status with application status for NSTP
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      #| R           | 200010 |      112101505172032 | SIMB0002233     | Chit                         | Chit         |
      #| N           |   2300 |      112101505172032 | SIMB0002233     | Chit                         | Chit         |
      | I           |   2300 |      112101505172021 | SIMB0002233     | Chit                         | Chit         |

  @TotalFreezedForD
  @Execute
  Scenario Outline: Scenario_NSTP_FT_Transaction_for_a_Total_Freezed_account
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    Then I am going to run status rest api
    #Then I am validating jsonobject "PaymentStatus" for NSTP String value "Success".
    Then I am going to validating error
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME | PASSWORD   |
      | rbladmin | password@2 |
    Then I check a transaction log inquiry using corporate reference number for NSTP
    Then I check a PPS log inquiry of above transaction, for acknowledgement sent to WU for NSTP
    Then I Check whether the transaction appears in mandate wise Report or transaction count report under MIS for NSTP
    Then I go to Transaction Dashborad for validating response status with application status for NSTP
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      | D           | 200010 |         409000849400 | RATN0000001     | Chit                         | Chit         |

  @InactiveAccountForD
  @Execute
  Scenario Outline: Scenario_NSTP_FT_Transaction_for_Inactive_Account
    When I want to run payment rest api with "<MANDATETYPE>", "<AMOUNT>" , "<BENEFICIARYACCOUNTNO>" , "<BENEFICIARYIFSC>" , "<BENEFICIARYACCOUNTHOLDERNAME>" ,"<REMITTERNAME>"
    Then I am going to run status rest api
    Then I am validating status response for this jsonobject "ErrorDesc" for error  String value "Account not allowed for credit".
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME | PASSWORD   |
      | rbladmin | password@2 |
    Then I check a transaction log inquiry using corporate reference number for NSTP
    Then I check a PPS log inquiry of above transaction, for acknowledgement sent to WU for NSTP
    Then I Check whether the transaction appears in mandate wise Report or transaction count report under MIS for NSTP
    Then I go to Transaction Dashborad for validating response status with application status for NSTP
    Then I am closing browser for NSTP

    Examples: 
      | MANDATETYPE | AMOUNT | BENEFICIARYACCOUNTNO | BENEFICIARYIFSC | BENEFICIARYACCOUNTHOLDERNAME | REMITTERNAME |
      | D           |    200 |         409000645831 | RATN0000001     | Chit                         | Chit         |

Feature: RBL - STP Bulk transaction Workflow

  Background: 
    Given I am requesting "BaseURI".

  @RestAPI
  Scenario Outline: RBL - STP Bulk transaction
    When I want to execute payment "<BulkTransaction>" using excel file "<ExeclFile>"
    Then I want to execute status rest api
    Then I am fetch Status response
    When Navigate To Specific RBL Portal
    Then Login to User Admin Using Following Credentials :
      | USERNAME | PASSWORD   |
      | rbladmin | password@2 |
    Then I Check whether the transaction appears in mandate wise Report or transaction count report under MIS
    Then I am close browser

     Examples: 
      | BulkTransaction                    | ExeclFile              |
      #| Bulk Transaction for I,D | RBLBulkData-I-D |
      #| Bulk Transaction for R | RBLBulkData-R |
      #| Bulk Transaction for N | RBLBulkData-N |
      #| Bulk Transaction for D | RBLBulkData-D |
      #| Bulk Transaction for I | RBLBulkData-I |
      # | Mixture Bulk Transaction for I , N | MixtureRBLBulkData-N-I |
      #| Mixture Bulk Transaction for D ,N | MixtureRBLBulkData-N-D |
      | Mixture Bulk Transaction for D , I | MixtureRBLBulkData-D-I |

  #| Mixture Bulk Transaction for D , R | MixtureRBLBulkData-R-D |
  #| Mixture Bulk Transaction for D , N and I | MixtureRBLBulkData-D-N-I |
  #| Mixture Bulk Transaction for D , N and R | MixtureRBLBulkData-R-N-D |
  #| Mixture Bulk Transaction for R ,N | MixtureRBLBulkData-R-N |
  #| Mixture Bulk Transaction for D , I and R | MixtureRBLBulkData-D-I-R |
  #| Mixture Bulk Transaction for N , I and R | MixtureRBLBulkData-N-I-R |
  #| Mixture Bulk Transaction for I , R | MixtureRBLBulkData-I-R |
  #| Mixture Bulk Transaction for D , N, I and R | MixtureRBLBulkData-R-N-D-I |
  #| Bulk Transaction for I,N,D | RBLBulkData-I-N-D |
  
  @BalanceAboveDefinedThreshold
  Scenario Outline: Bulk Transaction with balance threshold amount, Benificiary and Remitter name
    When I want to execute payment "<BulkTransaction>" using excel file "<ExeclFile>"
    Then I want to execute status rest api
    Then I want to validate error massage for "<BulkTransaction>"
    Then I am close browser

    Examples: 
      | BulkTransaction                                             | ExeclFile                      |
      # | Bulk Transaction for N with balance above defined threshold      | BalanceAboveDefinedThreshold-N |
      # | Bulk Transaction for N with beneficiary name contain caution word | InvalidBeneficiaryName_N       |
      # | Bulk Transaction for N with remitter name contain caution word   | InvalidRemitterName_N          |
      #| Bulk Transaction for I with balance above defined threshold      | BalanceAboveDefinedThreshold-I |
      #  | Bulk Transaction for I with beneficiary name contain caution word | InvalidBeneficiaryName_I       |
      # | Bulk Transaction for I with remitter name contain caution word   | InvalidRemitterName_I          |
      | Bulk Transaction for D with balance above defined threshold | BalanceAboveDefinedThreshold-D |

  | Bulk Transaction for D with beneficiary name contain caution word | InvalidBeneficiaryName_D       |
  | Bulk Transaction for D with remitter name contain caution word    | InvalidRemitterName_D          |
  #| Bulk Transaction for R with balance above defined threshold      | BalanceAboveDefinedThreshold-R |
  #| Bulk Transaction for R with beneficiary name contain caution word | InvalidBeneficiaryName_R       |
  # | Bulk Transaction for R with remitter name contain caution word   | InvalidRemitterName_R          |
    
  @mixtureBulkBenificaryThresholdRemitters
  Scenario Outline: Mixture Bulk Transaction with balance Threshold amount, Beneficiary and Remitter name
    When I want to execute payment "<BulkTransaction>" using excel file "<ExeclFile>"
    Then I want to execute status rest api
    Then I want to validate error massage for "<BulkTransaction>"
    Then I am close browser
    Examples:
      | BulkTransaction                                                               | ExeclFile                                   |
      | Bulk Transaction for R and N with balance above defined threshold      | MixtureBalanceAboveDefinedThreshold-R-N |
      | Bulk Transaction for R and N with beneficiary name contain caution word | InvalidMixtureBeneficiaryName_R-N       |
      | Bulk Transaction for R and N with remitter name contain caution word   | InvalidMixtureRemitterName_R-N          |
      #| Bulk Transaction for D and N with balance above defined threshold      | MixtureBalanceAboveDefinedThreshold-D-N |
      #| Bulk Transaction for D and N with beneficiary name contain caution word | InvalidMixtureBeneficiaryName_D-N       |
      #| Bulk Transaction for D and N with remitter name contain caution word   | InvalidMixtureRemitterName_D-N          |
      #| Bulk Transaction for D and I with balance above defined threshold      | MixtureBalanceAboveDefinedThreshold-D-I |
      #| Bulk Transaction for D and I with beneficiary name contain caution word | InvalidMixtureBeneficiaryName_D-I       |
      # | Bulk Transaction for D and I with remitter name contain caution word   | InvalidMixtureRemitterName_D-I          |
      #| Bulk Transaction for R and D with balance above defined threshold      | MixtureBalanceAboveDefinedThreshold-R-D |
      #| Bulk Transaction for R and D with beneficiary name contain caution word | InvalidMixtureBeneficiaryName_R-D       |
      #| Bulk Transaction for R and D with remitter name contain caution word   | InvalidMixtureRemitterName_R-D           |
      #| Bulk Transaction for R, N and D with balance above defined threshold      | MixtureBalanceAboveDefinedThreshold-R-N-D |
      #| Bulk Transaction for R, N and D with beneficiary name contain caution word| InvalidMixtureBeneficiaryName_R-N-D       |
      #| Bulk Transaction for R, N and D with remitter name contain caution word   | InvalidMixtureRemitterName_R-N-D          |
      #| Bulk Transaction for R, N, D and I with balance above defined threshold       | MixtureBalanceAboveDefinedThreshold-R-N-D-I |
      #| Bulk Transaction for R, N, D and I with beneficiary name contain caution word | InvalidMixtureBeneficiaryName_R-N-D-I       |
      #| Bulk Transaction for R, N, D and I with remitter name contain caution word    | InvalidMixtureRemitterName_R-N-D-I          |

  @Duplicate
  Scenario Outline: RBL - STP Bulk transaction
    When I want to execute payment "<BulkTransaction>" using excel file "<ExeclFile>"
    Then I want to execute status rest api
    Then I am fetch Status response
    When I want to execute payment "<BulkTransaction>" using excel file "<ExeclFile>" for duplicate
    Then I want to validate error massage for "<BulkTransaction>" in STP
    #Then I want to validate error massage for "<BulkTransaction>" 
    Then I want to execute status rest api
    Then I am fetch Status response for duplicate
    Then I am close browser

      Examples: 
      | BulkTransaction                                                     | ExeclFile       |
      #| Bulk Transaction reference number which is already used for D | RBLBulkData-D |
      # | Bulk Transaction reference number which is already used for I | RBLBulkData-I |
      #| Bulk Transaction reference number which is already used for N | RBLBulkData-N |
      #| Bulk Transaction reference number which is already used for R | RBLBulkData-R |
      | Bulk Transaction reference number which is already used for I and D | RBLBulkData-I-D |
      #| Bulk Transaction reference number which is already used for R, N and D | MixtureRBLBulkData-R-N-D |       
      #| Bulk Transaction reference number which is already used for R and N | MixtureRBLBulkData-R-N |
      #| Bulk Transaction reference number which is already used for R and D | MixtureRBLBulkData-R-D |
      #| Bulk Transaction reference number which is already used for N and D | MixtureRBLBulkData-N-D |
      #| Bulk Transaction reference number which is already used for R and I | MixtureRBLBulkData-R-I |
      #| Bulk Transaction reference number which is already used for N and I | MixtureRBLBulkData-N-I |
      #| Bulk Transaction reference number which is already used for R, N, D I | MixtureRBLBulkData-R-N-D-I |


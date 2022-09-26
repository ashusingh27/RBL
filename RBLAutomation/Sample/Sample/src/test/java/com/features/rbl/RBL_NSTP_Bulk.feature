Feature: RBL - NSTP Bulk transaction Workflow

  Background: 
    Given I am requesting "BaseURI".

  @RestAPI
  Scenario Outline: RBL - NSTP Bulk transaction
    When I want to execute payment "<BulkTransaction>" using excel file "<ExeclFile>"
    Then I want to execute status rest api
    Then I am fetch Status response then validate
    Then I am close browser

      Examples: 
      | BulkTransaction                  | ExeclFile       |
      #| Bulk Transaction for NSTP with D | RBL_NSTP_Bulk_D |
      #| Bulk Transaction for NSTP with R | RBL_NSTP_Bulk_R |
      #| Bulk Transaction for NSTP with N | RBL_NSTP_Bulk_N |
      #| Bulk Transaction for NSTP with I | RBL_NSTP_Bulk_I |
      #|Mixture Bulk Transaction for NSTP with D and I | RBL_NSTP_MixtureBulk_D-I |
      #|Mixture Bulk Transaction for NSTP with D and N | RBL_NSTP_MixtureBulk_D-N |
      #|Mixture Bulk Transaction for NSTP with I and N | RBL_NSTP_MixtureBulk_I-N |
      #|Mixture Bulk Transaction for NSTP with D and R | RBL_NSTP_MixtureBulk_D-R |
      #|Mixture Bulk Transaction for NSTP with D, N and R | RBL_NSTP_MixtureBulk_D-N-R |
      #|Mixture Bulk Transaction for NSTP with R and N | RBL_NSTP_MixtureBulk_R-N |
      #|Mixture Bulk Transaction for NSTP with D, I and R | RBL_NSTP_MixtureBulk_D-I-R |
      #|Mixture Bulk Transaction for NSTP with N, I and R | RBL_NSTP_MixtureBulk_N-I-R |
      #|Mixture Bulk Transaction for NSTP with I and R | RBL_NSTP_MixtureBulk_I-R |
      |Mixture Bulk Transaction for NSTP with D, N, I and R | RBL_NSTP_MixtureBulk_D-N-I-R |
      
  @DuplicateBulk
  Scenario Outline: Duplicate_Transaction_Reference_Numbers_Are_Processed_in_Bulk
    When I want to execute payment "<BulkTransaction>" using excel file "<ExeclFile>"
    When Navigate To Maker RBL Portal
    Then Login to User Using Following Credentials :
      | USERNAME     | PASSWORD   |
      | RBLOPSMAKER1 | password@2 |
    Then I am going to check whether above transactions in bulk can be initiated by maker
    Then Login to User Using Following Credentials :
      | USERNAME       | PASSWORD   |
      | RBLOPSCHECKER1 | password@2 |
    Then I am going to check whether above transactions in bulk can be authorised by checker
    Then I want to execute status rest api
    Then I am fetch Status response
    Then I am fetch Status response in bulk then validate
    When I want to execute payment "<BulkTransaction>" using excel file "<ExeclFile>" for duplicate
    Then I want to validate object "[0].ErrorDesc" for this string "Duplicate Transaction Id" in payment response
    Then I want to execute status rest api
    Then I am fetch Status response for duplicate
    Then I am close browser for bulk

    Examples: 
      | BulkTransaction                      | ExeclFile              |
      #| Bulk Transaction for NSTP with R-N-D | RBL_NSTP_Bulk_R-N-D |
      #| Bulk Transaction for NSTP with R-D-I | RBL_NSTP_Bulk_R-D-I |
      #| Bulk Transaction for NSTP with R-I | RBL_NSTP_Bulk_R-I |
      #| Bulk Transaction for NSTP with N-I | RBL_NSTP_Bulk_N-I |
      #| Bulk Transaction for NSTP with N-D-I | RBL_NSTP_Bulk_N-D-I |
      #| Bulk Transaction for NSTP with D-I | RBL_NSTP_Bulk_D-I |
      #| Bulk Transaction for NSTP with N-D | RBL_NSTP_Bulk_N-D |
      #| Bulk Transaction for NSTP with R-D | RBL_NSTP_Bulk_R-D |
      #| Bulk Transaction for NSTP with R-N | RBL_NSTP_Bulk_R-N |
      #| Bulk Transaction for NSTP with R-N-D-I | RBL_NSTP_Bulk_R-N-D-I |
      #| Bulk Transaction for NSTP with R-N-I | RBL_NSTP_Bulk_R-N-I |

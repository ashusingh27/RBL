Feature: FUNDTRANSFER IMPS

  @TestCaseKey=CCC-T1
  Scenario: TC_001_IMPS_CIB
    Given I am requesting "BaseURI"
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Base Test "BaseTestJson.json" data Replace with RunTime Test Data"JsonTestData.json"
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "JsonTestData.json"without charset
    Then Save Final Response in "JsonResponse.json" output File.
    Then I compare Json Schema file "Json_Schema.json" with Response File "JsonResponse.json".
    Then I am validating object "generateTokenResponse.Status.ErrorMessage" for string value "SUCCESS" For "GET TOKEN".

  @TestCaseKey=CCC-T2
  Scenario: TC_002_IMPS_CIB
    Given I am requesting "BaseURI"
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Base Test "BaseTestJson.json" data Replace with RunTime Test Data"JsonTestData.json"
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "JsonTestData.json"without charset
    Then Save Final Response in "JsonResponse.json" output File.
    Then I compare Json Schema file "Json_Schema.json" with Response File "JsonResponse.json".
    Then I am validating object "generateTokenResponse.Status.ErrorMessage" for string value "SUCCESS" For "GET TOKEN".

  @TestCaseKey=CCC-T3
  Scenario: TC_003_IMPS_CIB
    Given I am requesting "BaseURI"
    Given Load TestData Form Specific Api sheet "Generate OTP"
    Given Base Test "BaseTestJson.json" data Replace with RunTime Test Data"JsonTestData.json"
    Then Create Input File for json from Excel Sheet
    Then I go to "/otp/api/v1/generateOtp" and "Post" Api Request Saved in Json File "JsonTestData.json"without charset
    Then Save Final Response in "JsonResponse.json" output File.
    Then I compare Json Schema file "Json_Schema.json" with Response File "JsonResponse.json".
    Then I am validating object "generateTokenResponse.Status.ErrorMessage" for string value "SUCCESS" For "GET TOKEN".

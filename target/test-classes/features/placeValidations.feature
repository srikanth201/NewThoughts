Feature: Validating place API's

Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
Given Add Place payload "<name>" "<langauge>" "<address>"
When user calls "AddPlaceAPI" with "POST" http request 
Then the API call is success with status code 200
And "status" in response body is "OK" 
And "scope" in response body is "APP"
And Verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:

|name|langauge|address|
|srikanth|english|nuzvid|
#|setgang|telugu|bangalore|






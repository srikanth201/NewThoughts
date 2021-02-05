package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild t = new TestDataBuild();

	@Given("Add Place payload {string} {string} {string}")
	public void add_place_payload(String name, String langauge, String address) throws IOException {

		res = given().spec(requestSpecification()).body(t.addPlacePayload(name, langauge, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("post")) {

			response = res.when().post(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("get")) {

			response = res.when().post(resourceAPI.getResource());
		}

	}

	@Then("^the API call is success with status code 200$")
	public void the_api_call_is_success_with_status_code_200() throws Throwable {

		assertEquals(response.getStatusCode(), 200);

	}

	@And("{string} in response body is {string}")
	public void something_in_response_body_is_something(String keyValue, String ExpectedValue) throws Throwable {

		assertEquals(getJsonPath(response, keyValue), ExpectedValue);
	}

	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		String place_id = getJsonPath(response, "place_id");

		given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualname=getJsonPath(response,"name");
		//assertEquals(actualname,expectedname);
	}

}
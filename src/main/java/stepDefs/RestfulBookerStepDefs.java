package stepDefs;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.Payload;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestfulBookerStepDefs extends BaseSteps {
    Response responseForGetBookingDetails;
    Response responseForGetBookingIds;
    Response responseForCreateBooking;

    @Given("I have restfulbooker api")
    public void i_have_restfulbooker_api() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I send a request to retrieve a bookingId {string}")
    public void i_send_a_request_to_retrieve_a_booking_id(String id) {
        // Write code here that turns the phrase above into concrete actions
        setEndpointPath(bookingEndpoint + id);
        responseForGetBookingDetails = getCallWithoutHeader();

    }

    @Then("the firstname {string}, lastname {string} and statusCode {int} are returned")
    public void the_firstname_lastname_and_status_code_are_returned(String fName, String lName, Integer sCode) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(responseForGetBookingDetails.statusCode(), equalTo(sCode));
        assertThat(responseForGetBookingDetails.body().jsonPath().get("firstname"), equalTo(fName));
        assertThat(responseForGetBookingDetails.body().jsonPath().get("lastname"), equalTo(lName));
    }



    @When("I send a request to retrieve a bookingIds")
    public void i_send_a_request_to_retrieve_a_booking_ids() {
        // Write code here that turns the phrase above into concrete actions
       setEndpointPath(bookingEndpoint);
        setHeadersWithContentTypeAndAccept();
        responseForGetBookingIds = getCall();
    }
    @Then("statusCode {int} are returned")
    public void status_code_are_returned(Integer sCode) {
        // Write code here that turns the phrase above into concrete actions
      assertThat(responseForGetBookingIds.statusCode(), equalTo(sCode));
    }

    @When("I send a request to createbooking with firstname {string}, lastname {string}, totalPrice {string}, depositPaid {string}, checking {string}, checkout {string}, additionalNeeds {string}")
    public void i_send_a_request_to_createbooking_with_firstname_lastname_total_price_deposit_paid_checking_checkout_additional_needs(String fName, String lName, String tPrice, String dPaid, String cIn, String cOut, String aNeeds) {
        // Write code here that turns the phrase above into concrete actions
        setHeadersWithContentTypeAndAccept();
        setEndpointPath(bookingEndpoint);
        Payload payload = new Payload();
        DocumentContext reqBody = loadJsonTemplate(creatBookingPayloadPath);
        payload.setPayloadForCreateBooking(reqBody,fName, lName, tPrice,dPaid,cIn, cOut, aNeeds);
        responseForCreateBooking = getPostCall();
    }
    @Then("the firstname {string}, lastname {string}, totalprice {int} and statusCode {int} are returned")
    public void the_firstname_lastname_totalprice_and_status_code_are_returned(String fName, String lName, Integer tPrice, Integer sCode) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(responseForCreateBooking.statusCode(), equalTo(sCode));
        assertThat(responseForCreateBooking.body().jsonPath().get("booking.firstname"), equalTo(fName));
        assertThat(responseForCreateBooking.body().jsonPath().get("booking.lastname"), equalTo(lName));
        assertThat(responseForCreateBooking.body().jsonPath().get("booking.totalprice"), equalTo(tPrice));
    }
}

package restAssuredTests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
//import static io.restassured.path.json.JsonPath.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class RestAssured_Test {


    @Test
    public void getBookingIds() {
        given().log().all().
                when().
                get("https://restful-booker.herokuapp.com/booking").
                then().log().all().
                statusCode(200).
                body("bookingid", hasItems(177, 206, 866));
    }

    @Test
    public void getBooking() {
        given().log().all().
                when().
                get("https://restful-booker.herokuapp.com/booking/{bookingID}", 812).
                then().log().all().
                statusCode(200).
                body("firstname", equalTo("John")).body("lastname", equalTo("Smith"));
    }

    @Test
    public void createBooking() {
        HashMap<String, Object> createBookingBody = new HashMap<>();
        createBookingBody.put("firstname", "JimEsthera");
        createBookingBody.put("lastname", "BrownAdefiraa");
        createBookingBody.put("totalprice", 2000);
        createBookingBody.put("depositpaid", true);
        createBookingBody.put("additionalneeds", "super bowls");

        HashMap<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-01-01");
        bookingDates.put("checkout", "2025-09-01");

        createBookingBody.put("bookingdates", bookingDates);

        given().log().all().contentType(ContentType.JSON).body(createBookingBody).
                when().
                post("https://restful-booker.herokuapp.com/booking").
                then().log().all().
                statusCode(200).
                body("booking.firstname", equalTo("JimEsthera")).body("booking.lastname", equalTo("BrownAdefiraa"));
    }
@Test
    public void updateBooking() {
        HashMap<String, Object> createBookingBody = new HashMap<>();
        createBookingBody.put("firstname", "JimEstherabb");
        createBookingBody.put("lastname", "BrownAdefiraabb");
        createBookingBody.put("totalprice", 5000);
        createBookingBody.put("depositpaid", true);
        createBookingBody.put("additionalneeds", "super bowls");

        HashMap<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-01-01");
        bookingDates.put("checkout", "2025-09-01");

        createBookingBody.put("bookingdates", bookingDates);

        given().log().all().header("Accept", "application/json").contentType(ContentType.JSON).header("Cookie","token=f207b0811875202").body(createBookingBody).
                when().
                put("https://restful-booker.herokuapp.com/booking/269").
                then().log().all().
                statusCode(200).
                body("firstname", equalTo("JimEstherabb")).body("lastname", equalTo("BrownAdefiraabb"));
    }

    @Test
    public void deleteBooking() {
        given().log().all().header("Authorization", " Basic YWRtaW46cGFzc3dvcmQxMjM=").
                when().
                delete("https://restful-booker.herokuapp.com/booking/{bookingID}", 622).
                then().log().all().
                statusCode(201);
    }
}

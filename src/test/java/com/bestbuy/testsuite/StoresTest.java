package com.bestbuy.testsuite;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.bestbuy.utils.TestUtils.getRandomValue;
import static com.bestbuy.utils.TestUtils.getRandomValueInt;

@RunWith(SerenityRunner.class)
public class StoresTest extends TestBase {

    @WithTags({
            @WithTag("storeFeature:Smoke"),
            @WithTag("storeFeature:Regression")
    })
    @Title("This test will get all stores")
    @Test
    public void getAllStores() {

        SerenityRest.rest()
                .given()

                .when()
               .get(EndPoints.GET_ALL_STORES)

                .then()
                .statusCode(200);
    }

    @WithTags({
            @WithTag("storeFeature:Sanity"),
            @WithTag("storeFeature:Regression")
    })
    @Title("This test will get a specific product by Id")
    @Test
    public void getStoreById() {

        SerenityRest.rest()
                .given()

                .when()
                .get("/stores/8")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @WithTags({
            @WithTag("storeFeature:Positive"),
            @WithTag("storeFeature:Regression")
    })
    @Title("This test will create a new product")
    @Test
    public void createAStore() {

         String name = "Square" + getRandomValue();
         String type = "One Stop Shop";
         String address = "Three petrol pump";
         String address2 = "Thane(W)";
         String city = "Mumbai";
         String state = "Maharastra";
         String zip = "400602";
         float lat = 2.22f + getRandomValueInt();
         float lng = 9.99f + getRandomValueInt();
         String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";


        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        SerenityRest.rest()
                .given()
                .log()
                .all()
                .header("Content-Type", "application/json")

                .when()
                .log()
                .all()
                .body(storesPojo)
                .post(EndPoints.CREATE_STORE)

                .then()
                .log()
                .all()
                .statusCode(201);
    }

    @WithTags({
            @WithTag("storeFeature:Positive"),
            @WithTag("storeFeature:Regression")
    })
    @Title("This test will update an existing new product")
    @Test
    public void UpdateAnStoreProduct() {

        String name = "Square" + getRandomValue();
        String type = "One Stop Shop";
        String address = "Three petrol pump";
        String address2 = "Thane(W)";
        String city = "Mumbai";
        String state = "Maharastra";
        String zip = "400602";
        float lat = 2.22f + getRandomValueInt();
        float lng = 9.99f + getRandomValueInt();
        String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";


        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        SerenityRest.rest()
                .given()
                .log()
                .all()
                .header("Content-Type", "application/json")

                .when()
                .log()
                .all()
                .body(storesPojo)
                .patch("/stores/8")

                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @WithTags({
            @WithTag("storeFeature:Negative"),
            @WithTag("storeFeature:Regression")
    })
    @Title("This test will provide an error code of 400 when user tries to access an invalid resource")
    @Test
    public void deleteAStoreUsingId() {
        SerenityRest.rest()
                .given()

                .when()
                .delete("/stores/id")

                .then()
                .statusCode(404);

    }

}

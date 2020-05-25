package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoresSteps {

    @Step("Creating product with name: {0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}, lat: {7}, lng: {8} and hours{9}")
    public int createStore(String name, String type, String address,
                           String address2, String city, String state, String zip,
                           float lat, float lng, String hours) {

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

        int value =
                SerenityRest.rest()
                        .given()
                        .log()
                        .all()
                        .contentType(ContentType.JSON)

                        .when()
                        .log()
                        .all()
                        .body(storesPojo)
                        .post(EndPoints.CREATE_STORE)

                        .then()
                        .log()
                        .all()
                        .extract()
                        .path("id");
        return value;
    }

    @Step("Getting the store information with storeId: {0}")
    public int getStoreById(int storeId) {
        int value = SerenityRest.rest()
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .pathParam("id", storeId)

                .when()
                .get(EndPoints.GET_STORE_BY_ID)

                .then()
                .log()
                .all()
                .extract()
                .path("id");
        return value;
    }

    @Step("Updating store information with storeId: {0}")
    public ValidatableResponse updateStore(int storeId, String name) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);

         return SerenityRest.rest()
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .pathParam("id",storeId)

                .when()
                .body(storesPojo)
                .patch(EndPoints.UPDATE_STORE_BY_ID)

                .then()
                .log()
                .all();
    }

    @Step("Deleting product information with storeId: {0}")
    public ValidatableResponse deleteStore(int storeId) {
        return SerenityRest.rest()
                .given()
                .log()
                .all()
                .pathParam("id", storeId)

                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)

                .then()
                .log()
                .all();

    }
}

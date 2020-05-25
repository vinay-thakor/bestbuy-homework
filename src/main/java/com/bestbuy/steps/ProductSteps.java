package com.bestbuy.steps;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductSteps {

    @Step("Creating product with name: {0}, type: {1}, upc: {2}, price: {3}, description: {4} and model: {5}")
    public int createProduct(String name, String type, String upc, double price,
                             String description, String model) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        int value = SerenityRest.rest()
                        .given()
                        .log()
                        .all()
                        .contentType(ContentType.JSON)

                        .when()
                        .log()
                        .all()
                        .body(productPojo)
                        .post(EndPoints.CREATE_PRODUCT)

                        .then()
                        .statusCode(201)
                        .log()
                        .all()
                        .extract()
                        .path("id");
        return value;

    }

    @Step("Getting the product information with productId: {0}")
    public int getProductById(int productId) {

        int value = SerenityRest.rest()
                .given()
                .log()
                .all()
                .pathParam("id", productId)
                .contentType(ContentType.JSON)

                .when()
                .get(EndPoints.GET_PRODUCT_BY_ID)

                .then()
                .log()
                .all()
                .extract()
                .path("id");
        return value;
    }

    @Step("Updating product information with productId: {0}")
    public ValidatableResponse updateProduct(int productId, double price) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setPrice(price);

        return SerenityRest.rest()
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .pathParam("id", productId)

                .when()
                .body(productPojo)
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)

                .then()
                .log()
                .all();
    }

     @Step("Deleting product information with productId: {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.rest()
                .given()
                .log()
                .all()
                .pathParam("id",productId)

                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)

                .then()
                .log()
                .all();
     }

}

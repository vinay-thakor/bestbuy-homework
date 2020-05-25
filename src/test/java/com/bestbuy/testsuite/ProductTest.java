package com.bestbuy.testsuite;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.bestbuy.utils.TestUtils.getRandomValue;

@RunWith(SerenityRunner.class)
public class ProductTest extends TestBase {


    @WithTags({
            @WithTag("productFeature:Smoke"),
            @WithTag("productFeature:Regression")
    })
    @Title("This test will get products with a limit of 10")
    @Test
    public void getAllProducts() {
        SerenityRest.rest()
                .given()

                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @WithTags({
            @WithTag("productFeature:Sanity"),
            @WithTag("productFeature:Regression")
    })
    @Title("This test will get a specific product by Id")
    @Test
    public void getProductById() {

        SerenityRest.rest()
                .given()

                .when()
                .get("/products/43900")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @WithTags({
            @WithTag("productFeature:Positive"),
            @WithTag("productFeature:Regression")
    })
    @Title("This test will create a new product")
    @Test
    public void createAProduct() {

        String name = "McVities Biscuits" + getRandomValue();
        String type = "Biscuits & Crackers";
        String upc = "12345" + getRandomValue();
        double price = 2.00;
        String description = "This is a placeholder request for creating a new product";
        String model = "xyz" + getRandomValue();

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);


        SerenityRest.rest()
                .given()
                .log()
                .all()
                .header("Content-Type", "application/json")

                .when()
                .log()
                .all()
                .body(productPojo)
                .post(EndPoints.CREATE_PRODUCT)

                .then()
                .log()
                .all()
                .statusCode(201);
    }

    @WithTags({
            @WithTag("productFeature:Positive"),
            @WithTag("productFeature:Regression")
    })
    @Title("This test will update an existing new product")
    @Test
    public void UpdateAnExistingProduct() {

        String name = "McVities Biscuits" + getRandomValue();
        String type = "Biscuits & Crackers" + getRandomValue();
        String upc = "12345" + getRandomValue();
        double price = 2.98 ;
        String description = "This is a placeholder request for updating a existing product";
        String model = "xyz" + getRandomValue();

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);


        SerenityRest.rest()
                .given()
                .log()
                .all()
                .header("Content-Type", "application/json")

                .when()
                .log()
                .all()
                .body(productPojo)
                .patch("/products/43900")

                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @WithTags({
            @WithTag("productFeature:Negative"),
            @WithTag("productFeature:Regression")
    })
    @Title("This test will provide an error code of 400 when user tries to access an invalid resource")
    @Test
    public void deleteAProductUsingId() {
        SerenityRest.rest()
                .given()

                .when()
                .delete("/products/id")

                .then()
                .statusCode(404);

    }
}
package com.bestbuy.crudtest;

import com.bestbuy.steps.ProductSteps;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.bestbuy.utils.TestUtils.getRandomValue;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
public class ProductCRUDTest extends TestBase {

    static String name = "McVities Biscuits" + getRandomValue();
    static String type = "Biscuits & Crackers";
    static String upc = "12345" + getRandomValue();
    static double price = 2.98;
    static String description = "This is a placeholder request for creating a new product";
    static String model = "xyz" + getRandomValue();
    static int productId;

    @Steps
    ProductSteps productSteps;

    @WithTag("ProductCrudTest")
    @Title("This test will create a new product")
    @Test
    public void test001() {

        int value = productSteps.createProduct(name, type, upc, price, description, model);
        productId = value;

//        System.out.println(value);
    }

    @WithTag("ProductCrudTest")
    @Title("Update the product information and verify the updated information")
    @Test
    public void test002() {
        price = price + 7;

        productSteps.updateProduct(productId,price).statusCode(200);

        int value = productSteps.getProductById(productId);
        assertThat(value,equalTo(productId));

//        System.out.println(value);
//        System.out.println(productId);
    }

    @WithTag("ProductCrudTest")
    @Title("Delete the product and verify if the product is deleted")
    @Test
    public void test003() {
        productSteps.deleteProduct(productId).statusCode(200);
        productSteps.deleteProduct(productId).statusCode(404);
    }

}

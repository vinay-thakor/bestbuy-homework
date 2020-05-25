package com.bestbuy.crudtest;

import com.bestbuy.steps.StoresSteps;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.bestbuy.utils.TestUtils.getRandomValue;
import static com.bestbuy.utils.TestUtils.getRandomValueInt;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
public class StoresCRUDTest extends TestBase {

    static String name = "Square" + getRandomValue();
    static String type = "One Stop Shop";
    static String address = "Three petrol pump";
    static String address2 = "Thane(W)";
    static String city = "Mumbai";
    static String state = "Maharastra";
    static String zip = "400602";
    static float lat = 2.22f + getRandomValueInt();
    static float lng = 9.99f + getRandomValueInt();
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;

    @Steps
    StoresSteps storesSteps;

    @WithTag("StoreCrudTest")
    @Title("This test will create a new store")
    @Test
    public void test001() {

        int value = storesSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        storeId = value;

//        System.out.println(value);
    }

    @WithTag("StoreCrudTest")
    @Title("Update the product information and verify the updated information")
    @Test
    public void test002() {
        name = name + "_updated";

        storesSteps.updateStore(storeId,name).statusCode(200);

        int value = storesSteps.getStoreById(storeId);
        assertThat(value, equalTo(storeId));

//        System.out.println(value);
//        System.out.println(storeId);
    }

    @WithTag("StoreCrudTest")
    @Title("Delete the product and verify if the product is deleted")
    @Test
    public void test003() {
        storesSteps.deleteStore(storeId).statusCode(200);
        storesSteps.deleteStore(storeId).statusCode(404);
    }

}

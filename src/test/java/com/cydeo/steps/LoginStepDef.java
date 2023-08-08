package com.cydeo.steps;

import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {

    LoginPage loginPage = new LoginPage();

    @Given("the user logged in {string} and {string}")
    public void theUserLoggedInAnd(String username, String password) {

        loginPage.login(username, password);
        BrowserUtil.waitFor(2);
    }

    @When("user gets username from user fields")
    public void user_gets_username_from_user_fields() {


    }
    @Then("the username be same with database")
    public void the_username_be_same_with_database() {



    }


}

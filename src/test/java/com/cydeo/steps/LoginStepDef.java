package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDef {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    String actualUserName;

    @Given("the user logged in {string} and {string}")
    public void theUserLoggedInAnd(String username, String password) {

        loginPage.login(username, password);
        BrowserUtil.waitFor(2);
    }

    @When("user gets username from user fields")
    public void user_gets_username_from_user_fields() {

         actualUserName = dashBoardPage.accountHolderName.getText();
        System.out.println("actualUserName = " + actualUserName);


    }
    @Then("the username be same with database")
    public void the_username_be_same_with_database() {

        DB_Util.runQuery("select full_name from users where email = 'librarian10@library'");

        String expectedUserName = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedUserName = " + expectedUserName);

        // compare actual vs expected
        Assert.assertEquals(actualUserName,expectedUserName);
    }


}

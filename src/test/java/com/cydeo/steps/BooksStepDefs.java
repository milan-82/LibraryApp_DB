package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.DashBoardPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class BooksStepDefs {
    BookPage bookPage = new BookPage();
    List<String> actualCategoryList;


    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);


    }


    @When("the user gets all book categories in webpage")
    public void the_user_gets_all_book_categories_in_webpage() {
        //potential wait here if tests fail
        actualCategoryList = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategoryList.remove(0);
        System.out.println("actualCategoryList = " + actualCategoryList);
    }

    @Then("user should be able to see following categories")
    public void user_should_be_able_to_see_following_categories(List<String> expectedCategoryList) {


        Assert.assertEquals(expectedCategoryList, actualCategoryList);

    }


    @When("I open book {string}")
    public void i_open_book(String bookName) {

        System.out.println("bookName = " + bookName);
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();

    }


    @Then("verify book categories must match book categories table from db")
    public void verifyBookCategoriesMustMatchBookCategoriesTableFromDb() {

        // run query to get data from db
        DB_Util.runQuery("select name from book_categories");

        // store the information inside the list
        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);
        System.out.println("expectedCategoryList = " + expectedCategoryList);

        // compare
        Assert.assertEquals(actualCategoryList, expectedCategoryList);

    }


    @Then("book information must match the database for {string}")
    public void bookInformationMustMatchTheDatabaseFor(String bookName) {

        //UI Steps
        // we need to get information from UI to compare with db

        System.out.println("UI book name = " + bookPage.bookName.getAttribute("value"));
        /*
        1.getText()--> it will return text from provided element
        2.getAttribute("value") --> if there is inputBox we are going to use getAttribute("value") this to get data
         */

        // create UI variables and save information
        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualAuthorName = bookPage.author.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");


        //run query to get data from db
        String query = "select name, isbn, year, author, description from books\n" +
                "where name = '"+bookName+"'";

        DB_Util.runQuery(query);

        Map<String, String> bookInfo = DB_Util.getRowMap(1);

        String expectedBookName = bookInfo.get("name");
        String expectedAuthorName = bookInfo.get("author");
        String expectedISBN = bookInfo.get("isbn");
        String expectedYear = bookInfo.get("year");

        //compare
        Assert.assertEquals(actualBookName,expectedBookName);
        Assert.assertEquals(actualAuthorName,expectedAuthorName);
        Assert.assertEquals(actualISBN,expectedISBN);
        Assert.assertEquals(actualYear,expectedYear);
        Assert.assertEquals(bookPage.description.getAttribute("value"), bookInfo.get("description"));


    }
}

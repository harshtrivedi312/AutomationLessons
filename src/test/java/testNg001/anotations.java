package testNg001;

import org.testng.annotations.*;
@Test
public class anotations {
//example of all testNG annotations

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("this will run before each suite");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("This method will run after each suite");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("this method will run before running any test");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("this method will run after all test are finish");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("this method will run before all the classes");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("this method will run after all the classes");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("this will run before any method is executed");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("this will run after all the methods are finish");
    }

//Hierarchy of annotations
    //1 BeforeSuite
    //2 BeforeTest
    //3 BeforeClass
    //4 BeforeMethod
    //5 Test
    //AfterMethod
    //AfterClass
    //AfterTest
    //AfterSuite

//Attributes

    //description
    //timeout
    //priority
    //dependsOnMethod
    //enabled
    //groups

//parameters
    //TestNG Parameters
    //TestNG DataProviders

}



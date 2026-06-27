package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;

public class Hooks {
    
    @BeforeAll
    public static void beforeAll(){
        System.out.println("this will execute before all only one time");
    }

    @Before
    public static void beforeEach(){
        System.out.println("this will execute before each test");
    }

    @BeforeStep
    public static void BeforeStep(){
        System.out.println("this will execute before each step");
    }

     @AfterAll
    public static void afterAll(){
        System.out.println("this will execute before all only one time");
    }

    @After
    public static void afterEach(){
        System.out.println("this will execute before each test");
    }

    @AfterStep
    public static void AfterStep(){
        System.out.println("this will execute before each step");
    }
}

package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import pages.BasePage;



@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        glue = "steps",
        tags = "@google"

)

public class runner {
    @AfterClass
    public static void cleanDriver() {
        BasePage.closeDriver();
    }
}


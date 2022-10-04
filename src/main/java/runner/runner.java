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
        tags = "@google",
        /*
          @since 0.2.0
         * Se agrega la configuración para habilitar la salida de tipo JSON y XML
        */
        plugin = {"junit:target/cucumber/result.xml", "json:target/cucumber/APITest.json"}


)

public class runner {
    @AfterClass
    public static void cleanDriver() {
        BasePage.closeDriver();
    }
}


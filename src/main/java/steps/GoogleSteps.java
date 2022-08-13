package steps;

import io.cucumber.java.en.*;
import pages.GooglePage;

public class GoogleSteps {
    GooglePage google = new GooglePage();
    @Given("^Ingresamos a la página de google$")
    public void navigateToGoogle (){
        google.navigateToGoogle();

    }
    @When("^Esccribimos algo en la barra de busqueda$")
    public void enterSearchCriteria (){

        google.enterSearchCriteria("Google");
    }

    @And("^Damos clic al boton de busqueda de google$")
    public void clickSearchButton () throws InterruptedException {
        Thread.sleep(1000);
        google.clickGoogleSearch();
    }
    @Then("^Nos muestra los resultados de la búsqueda$")
    public void validateResults (){
    }

}

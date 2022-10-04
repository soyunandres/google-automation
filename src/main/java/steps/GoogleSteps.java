package steps;

import io.cucumber.java.en.*;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import pages.GooglePage;

public class GoogleSteps {
    GooglePage google = new GooglePage();
    @Dado("^Que ingresamos a la página de google$")
    public void navigateToGoogle (){
        google.navigateToGoogle();

    }
    @Cuando("^Esccribimos algo en la barra de búsqueda$")
    public void enterSearchCriteria (){

        google.enterSearchCriteria("Google");
    }

    @Y("^Damos clic al botón de búsqueda de google$")
    public void clickSearchButton () throws InterruptedException {
        Thread.sleep(1000);
        google.clickGoogleSearch();
    }
    @Entonces("^Nos muestra los resultados de la búsqueda$")
    public void validateResults (){
    }

}

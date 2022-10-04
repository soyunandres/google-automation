package pages;





import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class BasePage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    protected static Duration durationtimeout;
    protected static Duration durationsleep;

    private Actions action;

    protected static Map<String, Region> regions;

    protected static Screen screen;
    protected static Pattern pattern;
    protected  static  DateTimeFormatter dateTimeFormatter;
    protected static LocalDateTime localDateTime;
    protected static Region region;



    static {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        durationtimeout = Duration.ofSeconds(10);
        durationsleep = Duration.ofSeconds(100);
        wait = new WebDriverWait(driver,durationtimeout ,durationsleep);
        regions = new TreeMap<String, Region>();
        screen = new Screen();
        pattern = new Pattern();
        ImagePath.add(System.getProperty("user.dir"));
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSS");
        localDateTime = LocalDateTime.now();
        region = new Region(screen.getRect());



    }

    public BasePage (WebDriver driver){
        BasePage.driver = driver;
        wait = new WebDriverWait(driver,durationtimeout, durationsleep);

    }

    public static void navigateTo (String url){
        driver.get(url);

    }

    public static void closeDriver() {
        driver.quit();
    }

    private WebElement Find(String locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }
    public void clickElement(String locator){


        Find(locator).click();
    }

    public void write(String locator, String textToWrite){
        Find(locator).clear();
        Find(locator).sendKeys(textToWrite);
    }

    public void selectFromDropDownByValue(String locator, String valueToSelect){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByValue(valueToSelect);


    }
    public void selectFromDropDownByIndex(String locator, int valueToSelect){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByIndex(valueToSelect);


    }
    public void selectFromDropDownByText(String locator, String valueToSelect){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByVisibleText(valueToSelect);


    }
    public void hoverOverElement(String locator){
        action.moveToElement(Find(locator));

    }

    public void doubleClickElement(String locator){
        action.doubleClick(Find(locator));
    }
    public void rightClickElement(String locator){
        action.contextClick(Find(locator));
    }

    public void sendKeys(String sendKeys) throws FindFailed {
        try {

            screen.type(sendKeys);

        } catch ( Exception e) {

            e.printStackTrace();
        }

    }
    public Stream bringmeAllPathsOfImages(String directoryOfImages) throws IOException {
        Stream<Path> path = Files.walk(Paths.get(directoryOfImages));
        path = path.filter(var -> var.toString().endsWith(".png"));
        //path.forEach(System.out::println);

        path.forEach(var -> {
            System.out.println(var.toString());
            pattern = pattern.setFilename(var.toString()).similar(0.2);

            try {
                region = region.setRect(screen.find(pattern).getRect());
                if(region.find(pattern) != null) {


                    //region.getImage().save(directoryOfImages + dateTimeFormatter.format(localDateTime)  + Math.random() +".png");
                    System.out.println(pattern.getSimilar() + " similar " );
                }


            } catch (FindFailed e) {
                e.printStackTrace();

            }
            ;

        });


        return path;    }
    //Read all images from folder and add to list of images

    //Find all images in screen and highlight them
    public void matchImagesInList() throws FindFailed  {
        try {
            screen.findAll(ImagePath.getPaths().get(1));


        } catch (FindFailed e) {
            e.printStackTrace();
        }
    }
    /*
    public void findImageAndCreateRegion(String image, String text) throws FindFailed {
        regions.put(text, screen.find(image));
        pattern = new Pattern(imagePath);
        screen.setAutoWaitTimeout(durationtimeout.toMillis());
        screen.find(pattern);
        regions.put(imagePath, screen.getLastMatch());
    }*/
    public Region findImage(String  path) throws FindFailed {
        region = region.setRect(screen);
        region.wait(pattern.setFilename(path).similar(0.6), durationtimeout.toMillis()).highlight(2);
        region.setRect(region.find(pattern));

        //region.highlight(2).getImage().save("src/main/resources/images/ToolBarOpenAppImages/RemoteDesktopConection/mstsc_3B591LqKfV_1.png");

        ;

        return region;
    }

    public Region findWordInRegion(String path, String text) throws FindFailed {
       findImage(path);
       region.wait(region.findWord(text), 1000);
       region.setRect(region.findWord(text)).highlight(1);
        return region;
    }
    public Region findImageInImage(String imagePath, String imagePathToFind) throws FindFailed {
        findImage(imagePath);
        region.find(imagePathToFind).highlight(1);
        return region;
    }
    public Region clickInImage() throws FindFailed {
      region.click();
        return region;
    }
    public Region dobleClickInImage() throws FindFailed {
        region.doubleClick();
        return region;
    }



}

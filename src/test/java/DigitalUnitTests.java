import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalUnitTests {
    WebDriver webDriver;

    @BeforeAll
    public void setup(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriver.get("https://www.digitalunite.com/practice-webform-learners");
       }

    @DisplayName("Check cookies window")
    @Test
    public void a() throws InterruptedException {

        if (webDriver.findElement(By.className("onetrust-close-btn-handler")).isDisplayed()){
            webDriver.findElement(By.className("onetrust-close-btn-handler")).click();
        }

        Thread.sleep(2000);

    }
    @DisplayName("Check input forms submission")
    @Test
    public void b()  {

        List<WebElement> webElements = webDriver.findElements(By.className("form-control"));

        webElements.get(0).sendKeys("You");
        webElements.get(1).sendKeys("012351212");
        webElements.get(2).sendKeys("10052000");
        webElements.get(3).sendKeys("emm7@gmail.com");
        webElements.get(4).sendKeys("SDET");


    }
    @DisplayName("Check radio button-age")
    @Test
    public void c()  {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-checkboxradio-label")));

        webDriver.findElements(By.className("ui-checkboxradio-label")).get(0).click();

    }
    @DisplayName("Check Uploads images for submission")
    @Test
    public void d()  {
       webDriver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir")+"/src/test/resources/logo.png");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2)); // Adjust the timeout as needed

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("js-form-submit")));

        boolean rmvBtn = webDriver.findElements(By.className("js-form-submit")).get(0).isDisplayed();
        Assertions.assertTrue(rmvBtn);
    }



    @DisplayName("Check radio button-confirmation")
    @Test
    public void e() throws InterruptedException {

//        if (webDriver.findElement(By.className("onetrust-close-btn-handler")).isDisplayed()){
//            webDriver.findElement(By.className("onetrust-close-btn-handler")).click();
//        }
        scroll(0,1100);
        Thread.sleep(1000);
        WebElement radioButton =  webDriver.findElements(By.id("edit-age")).get(0);

            radioButton.click();
    }


    @DisplayName("Check Form Submission")
    @Test
    public void f() {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("js-form-submit")));

        webDriver.findElements(By.className("js-form-submit")).get(1).click();
        Assertions.assertTrue(webDriver.findElement(By.tagName("h1")).getText().contains("Thank you"));

    }


    public void scroll(int x, int y){
        JavascriptExecutor js= (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollTo("+x+","+y+")");
    }
    @AfterAll
    public void quitWebDriver(){
//        webDriver.quit();
    }
}

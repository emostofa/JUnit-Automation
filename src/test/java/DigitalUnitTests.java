import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.function.BooleanSupplier;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalUnitTests {
    WebDriver webDriver;

    @BeforeAll
    public void setup() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @DisplayName("Check if form is submitted properly")
    @Test
    public void automateForms() throws InterruptedException {
        webDriver.get("https://www.digitalunite.com/practice-webform-learners");
        webDriver.findElements(By.className("form-control")).get(0).sendKeys("You");
        webDriver.findElements(By.className("form-control")).get(1).sendKeys("012351212");
        webDriver.findElements(By.className("form-control")).get(2).sendKeys("10052000");
        webDriver.findElements(By.className("form-control")).get(3).sendKeys("emm7@gmail.com");
        webDriver.findElements(By.className("form-control")).get(4).sendKeys("SDET");
        webDriver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir")+"/src/test/resources/logo.png");
        Thread.sleep(2000);

        boolean rmvBtn = webDriver.findElements(By.className("js-form-submit")).get(0).isDisplayed();
        Assertions.assertTrue(rmvBtn);
        if (webDriver.findElement(By.className("onetrust-close-btn-handler")).isDisplayed()){
            webDriver.findElement(By.className("onetrust-close-btn-handler")).click();
        }
        webDriver.findElements(By.className("ui-checkboxradio-label")).get(1).click();
        scroll(0,1000);
        Thread.sleep(1000);
        webDriver.findElement(By.className("form-checkbox")).click();
        webDriver.findElements(By.className("js-form-submit")).get(1).click();
        Assertions.assertTrue(webDriver.findElement(By.tagName("h1")).getText().contains("Thank you"));

    }



    public void scroll(int x, int y){
        JavascriptExecutor js= (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollTo("+x+","+y+")");
    }
    @AfterAll
    public void quitWebDriver(){
        webDriver.quit();
    }
}

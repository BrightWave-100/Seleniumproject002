package part2;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;

import java.time.Duration;
import java.util.List;

public class FirstSeleniumTest {
    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    String loginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeClass
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private void openLoginPage() {
        driver.manage().deleteAllCookies();
        driver.get(loginUrl);
    }

    @Test
    public void testLoggingIntoApplication() throws InterruptedException{
        openLoginPage();
        loginPage.login("Admin", "admin123");

        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[normalize-space()='Dashboard']")));
        Assert.assertEquals(heading.getText(), "Dashboard",
                "Dashboard heading should be shown after valid login");
    }

    @Test
    public void testLoginWithInvalidPassword() throws InterruptedException{
        openLoginPage();
        loginPage.login("Admin", "InvalidPassword");

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-alert-content-text")));
        String actualResult = error.getText();
        String expectedResult = "Invalid credentials";
        Assert.assertEquals(actualResult, expectedResult,"user should not be logged into the application");
    }

    @Test
    public void testLoginWithInvalidUsername() throws InterruptedException{
        openLoginPage();
        loginPage.login("InvalidUser","admin123");

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-alert-content-text")));
        String actualResult = error.getText();
        String expectedResult = "Invalid credentials";
        Assert.assertEquals(actualResult, expectedResult,"user should not be logged into the application");
    }

    @Test
    public void testLoginWithEmptyUsername() throws InterruptedException{
        openLoginPage();
        loginPage.login("","admin123");

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-input-field-error-message")));
        String actualResult = error.getText();
        String expectedResult = "Required";
        Assert.assertEquals(actualResult, expectedResult,"user should not be logged into the application");
    }

    @Test
    public void testLoginWithEmptyPassword() throws InterruptedException{
        openLoginPage();
        loginPage.login("Admin","");

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".oxd-input-field-error-message")));
        String actualResult = error.getText();
        String expectedResult = "Required";
        Assert.assertEquals(actualResult, expectedResult,"user should not be logged into the application");
    }

    @Test
    public void testLoginWithEmptyUsernameAndPassword() throws InterruptedException{
        openLoginPage();
        loginPage.login("","");

        List<WebElement> errors = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".oxd-input-field-error-message")));
        Assert.assertEquals(errors.size(), 2, "Expected two Required messages");
        for (WebElement e : errors) {
            Assert.assertEquals(e.getText(), "Required" ,"user should not be logged into the application");
        }
    }


    @Test
    public void testLogout() {
        openLoginPage();
        loginPage.login("Admin", "admin123");
        Assert.assertEquals(dashboardPage.getHeadingText(), "Dashboard",
                "Dashboard should load before logout");

        dashboardPage.logout();

        wait.until(ExpectedConditions.urlContains("/auth/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/auth/login"),
                "User should be returned to the login page after logout");
    }

}
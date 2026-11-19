package part2;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SidebarPage;

public class SidebarNavigationTest {
    WebDriver driver;
    LoginPage loginPage;
    SidebarPage sidebarPage;
    String loginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        sidebarPage = new SidebarPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private void loginAndOpenSite() {
        driver.manage().deleteAllCookies();
        driver.get(loginUrl);
        loginPage.login("Admin", "admin123");
    }

    @Test
    public void testAdminMenuOpensAdminPage() {
        loginAndOpenSite();
        sidebarPage.openAdmin();
        sidebarPage.waitForHeading("Admin");
        Assert.assertEquals(sidebarPage.getPageHeading(), "Admin",
                "Clicking Admin should open the Admin page");
    }

    @Test
    public void testPimMenuOpensPimPage() {
        loginAndOpenSite();
        sidebarPage.openPim();
        sidebarPage.waitForHeading("PIM");
        Assert.assertEquals(sidebarPage.getPageHeading(), "PIM",
                "Clicking PIM should open the PIM page");
    }

    @Test
    public void testLeaveMenuOpensLeavePage() {
        loginAndOpenSite();
        sidebarPage.openLeave();
        sidebarPage.waitForHeading("Leave");
        Assert.assertEquals(sidebarPage.getPageHeading(), "Leave",
                "Clicking Leave should open the Leave page");
    }
}
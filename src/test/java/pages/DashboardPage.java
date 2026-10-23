package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private final WebDriverWait wait;

    private final By dashboardHeading = By.xpath("//h6[normalize-space()='Dashboard']");
    private final By userMenu = By.cssSelector(".oxd-userdropdown-tab");
    private final By logoutLink = By.xpath("//a[normalize-space()='Logout']");

    public DashboardPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getHeadingText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeading)).getText();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(userMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}
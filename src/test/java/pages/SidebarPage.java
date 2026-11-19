package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SidebarPage {
    private final WebDriverWait wait;

    private final By adminMenu = By.cssSelector("a.oxd-main-menu-item[href*='viewAdminModule']");
    private final By pimMenu = By.cssSelector("a.oxd-main-menu-item[href*='viewPimModule']");
    private final By leaveMenu = By.cssSelector("a.oxd-main-menu-item[href*='viewLeaveModule']");
    private final By pageHeading = By.cssSelector(".oxd-topbar-header-breadcrumb h6");

    public SidebarPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openAdmin() {
        click(adminMenu);
    }

    public void openPim() {
        click(pimMenu);
    }

    public void openLeave() {
        click(leaveMenu);
    }

    public void waitForHeading(String expected) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(pageHeading, expected));
    }

    public String getPageHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading)).getText();
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}

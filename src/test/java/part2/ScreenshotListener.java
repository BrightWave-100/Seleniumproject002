package part2;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

    public class ScreenshotListener implements ITestListener {

        @Override
        public void onTestFailure(ITestResult result) {
            Object testInstance = result.getInstance();
            if (!(testInstance instanceof FirstSeleniumTest)) {
                return;
            }
            FirstSeleniumTest test = (FirstSeleniumTest) testInstance;

            try {
                File source = ((TakesScreenshot) test.driver).getScreenshotAs(OutputType.FILE);
                Path folder = Paths.get("screenshots");
                Files.createDirectories(folder);
                Path target = folder.resolve(result.getMethod().getMethodName() + ".png");
                Files.copy(source.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Screenshot saved: " + target.toAbsolutePath());
            } catch (IOException e) {
                System.out.println("Could not save screenshot: " + e.getMessage());
            }
        }
    }


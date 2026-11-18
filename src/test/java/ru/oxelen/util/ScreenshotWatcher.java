package ru.oxelen.util;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.oxelen.BaseTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = BaseTest.getDriver();
        if (driver == null) {
            return;
        }

        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        saveScreenshotToFile(context.getDisplayName(), screenshotBytes);
        BaseTest.quitDriver();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        BaseTest.quitDriver();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        BaseTest.quitDriver();
    }

    private void saveScreenshotToFile(String testName, byte[] bytes) {
        try {
            Path dir = Paths.get("target", "screenshots");
            Files.createDirectories(dir);

            String safeName = testName.replaceAll("[^a-zA-Z0-9а-яА-Я._-]", "_");
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
            Path filePath = dir.resolve(safeName + "_" + timestamp + ".png");

            Files.write(filePath, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

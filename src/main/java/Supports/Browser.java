package Supports;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.How;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Browser {
    public static WebDriver driver;
    public static WebDriver openBrowser(String name) {
        if ("chrome".equalsIgnoreCase(name)) {
            driver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(name)) {
            driver = new FirefoxDriver();
        } else if ("safari".equalsIgnoreCase(name)) {
            driver = new SafariDriver();
        } else {
            throw new IllegalArgumentException("The browser " + name + "does not support");
        }
        return null;
    }

    public static WebElement find(How id, String how, String locator) throws IllegalAccessException {
        if("id".equalsIgnoreCase(how)) return driver.findElement(By.id(locator));
        else if("name".equalsIgnoreCase(how)) return driver.findElement(By.name(locator));
        else if("xpath".equalsIgnoreCase(how)) return driver.findElement(By.xpath(locator));
        else if("css".equalsIgnoreCase(how)) return driver.findElement(By.cssSelector(locator));
        else throw new IllegalAccessException("the selector " + how + " does not support!!!");
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void visit(String url){
        driver.get(url);
    }

    public static WebElement find(How how, String locator){
        return driver.findElement(how.buildBy(locator));
    }

    public static WebElement find(By locator){
        return driver.findElement(locator);
    }

    public static List<WebElement> all(How how, String locator){
        return driver.findElements(how.buildBy(locator));
    }

    public static void fill(How how, String locator, String withText) {
        find(how, locator).sendKeys(withText);
    }

    public static void click(How how, String locator){
        find(how, locator).click();
    }

    public static void backToPreviousPage(){
        driver.navigate().back();
    }

    public static void refreshPage(){driver.navigate().refresh();}

    public static void forwardToNextPage(){driver.navigate().forward();}

    public static void check(How how, String locator){
        if(!find(how, locator).isSelected())click(how, locator);
    }

    public static void uncheck(How how, String locator){
        if(find(how, locator).isSelected())click(how, locator);
    }

    public static void close(){
        if (driver!=null){
            driver.quit();
        }
    }

    public static void captureScreenShot(){
        File screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("./target/screenshot-"+System.currentTimeMillis()+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

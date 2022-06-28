package google;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.assertTrue;

public class Selenium {
    WebDriver driver;
    static Properties p = new Properties();
    @BeforeClass
    public static void before(){
        p = getEnvironmentVariable("application.properties");
        System.out.println(p);
    }

    @Test
    public void seleniumTest() throws InterruptedException {
        assertTrue("1" == "1");
        //
//        driver.findElement(By.xpath("")).click();
    }
    @Test
    public void seleniumTest2() throws InterruptedException {
        driver = visitGoogle();
        rightClick(driver);
    }
    
    public void rightClick(WebDriver driver){
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[2]/div/div[1]/div/div/div[1]/div/a"));
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(element)
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(1));
    }
    public WebDriver launchBrowser(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    public WebDriver visitGoogle() throws InterruptedException {
        driver = launchBrowser();
        driver.get(p.getProperty("url"));
        driver.findElement(By.name("q")).sendKeys(p.getProperty("surname") +" " + p.getProperty("name"));
        Thread.sleep(2000);
        driver.findElement(By.name("q")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@name='btnK']")).click();
        return driver;
    }
    public static Properties getEnvironmentVariable(String path){
        Properties prop = new Properties();
        try{
            prop.load((Selenium.class.getClassLoader().getResourceAsStream(path)));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return prop;
    }
    @AfterClass
    public static void after(){
        System.out.println("after");
    }
}

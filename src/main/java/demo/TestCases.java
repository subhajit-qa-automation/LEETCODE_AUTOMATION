package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;



public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01_VerifyHomePageUrl(){
//        System.out.println("Start Test case: testCase01");
//        driver.get("https://www.google.com");
//        System.out.println("end Test case: testCase02");

        try {
            System.out.println("Start Test case: testCase01");
            driver.get(" https://leetcode.com/");


            String currenturl = driver.getCurrentUrl();

            if (currenturl.contains("leetcode")) {
                System.out.println("URL contains leetcode");
            } else {
                System.out.println("URL does not contain leetcode");
            }

            System.out.println("end Test case: testCase01");


        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public void testCase02_Verify_Problem_Set_URL_And_Display_First_5_Questions() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        driver.get("https://leetcode.com/");
        Thread.sleep(3000);
        WebElement questions = driver.findElement(By.xpath("//p[contains(text(),\"View Questions\")]"));
        questions.click();
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.contains("problemset")) {
            throw new IllegalStateException("Not on problem set page");
        }

        List<WebElement> problems = driver.findElements(By.xpath("(//div[@class='overflow-hidden'])"));

      for(int i = 1; i <= 5; i++) {
          System.out.println(problems.get(i).getText());
      }
    }

    public void testCase03_Verify_The_Two_Sum_problem() throws InterruptedException {

        System.out.println("Start Test case: testCase03");
        driver.get("https://leetcode.com/problemset/");
        Thread.sleep(4000);

        WebElement twoSums = driver.findElement(By.xpath("(//div[@class='truncate']//a)[2]"));
        twoSums.click();
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.contains("two-sum")) {
            System.err.println("URL does not contain 'two-sum'! Test failed.");

        } else {
            System.out.println("The URL of the problem contains \"two-sum\"");

        }
    }

    public void testCase04_Verify_Register_Or_SignIn() throws InterruptedException {

        System.out.println("Start Test case: testCase04");
        driver.get("https://leetcode.com/problems/two-sum/description/");

        Thread.sleep(3000);
        WebElement dynamicLayout = driver.findElement(By.xpath("(//button[normalize-space()='Enable Dynamic Layout'])[1]"));
        dynamicLayout.click();
        WebElement skip = driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[43]"));
        skip.click();
        Thread.sleep(3000);
        WebElement submission = driver.findElement(By.xpath("(//div[text()='Submissions']/following-sibling::div)[1]"));
        submission.click();
        Thread.sleep(3000);

        WebElement registerOrSignInMessage = driver.findElement(By.xpath("//a[contains(text(),'Register or Sign In')]"));
        if (!registerOrSignInMessage.getText().contains("Register or Sign In")) {
            System.out.println("Test failed: 'Register or Sign In' message is not displayed.");
        } else {
            System.out.println("Test passed: 'The message Register or Sign In is displayed when you click on the submissions tab.");

        }
    }


}






import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver wd;
    public Properties properties= new Properties();

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    @BeforeSuite
    public void setUp() throws IOException {

        System.setProperty("webdriver.chrome.driver", "src/chromedriver/chromedriver.exe");

        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    @BeforeTest
    public void goToMainPage(){

        wd.get(getProperty("web.base"));
        wd.manage().window().maximize();

    }


    @AfterTest
    public void close() {
        wd.close();
    }
}

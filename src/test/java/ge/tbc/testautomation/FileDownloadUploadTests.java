package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileDownloadUploadTests {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeClass
    public void setUp(){
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setArgs(Arrays.asList("--disable-gpu", "--disable-extensions", "--start-maximized"));
        launchOptions.setHeadless(false);
        launchOptions.setSlowMo(1000);
        browser = playwright.chromium().launch(launchOptions);
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterClass
    public void tearDown(){
        browserContext.close();
        browser.close();
        playwright.close();
    }


    @Test
    public void testDownload() {
        page.navigate("https://the-internet.herokuapp.com/download");
        Locator downloadLink = page.getByText("test_upload_regression.txt");

        Download download = page.waitForDownload(downloadLink::click);
        download.saveAs(Paths.get(System.getProperty("user.dir"), "build/downloads", download.suggestedFilename()));
    }

    @Test
    public void testUpload() {
        page.navigate("https://the-internet.herokuapp.com/upload");
        Locator uploadInput = page.locator("#file-upload");

        // Initiating the file chooser
        FileChooser fileChooser = page.waitForFileChooser(uploadInput::click);

        // Checking if it can take more than 1 file
        if (fileChooser.isMultiple()) {
            fileChooser.setFiles(new Path[]{
                            // Uploading 2 ronaldo knee slide pictures
                            Paths.get(System.getProperty("user.dir"), "/src/main/resources/ronaldokneeslide.jpg"),
                            Paths.get(System.getProperty("user.dir"), "/src/main/resources/ronaldokneeslide.jpg")
                    }
            );
        }
        else {
            // Uploading 1 ronaldo knee slide picture
            fileChooser.setFiles(Paths.get(System.getProperty("user.dir"), "/src/main/resources/ronaldokneeslide.jpg"));
        }
    }
}

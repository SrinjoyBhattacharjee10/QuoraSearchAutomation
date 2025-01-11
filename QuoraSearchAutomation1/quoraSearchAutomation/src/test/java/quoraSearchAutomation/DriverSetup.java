package quoraSearchAutomation;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.google.common.io.Files;
public class DriverSetup {
	static WebDriver driver;
	static Thread thread = new Thread();
	static FileInputStream file;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static String baseurl = " https://www.quora.com/profile/Quora";
	public static WebDriver getDriver(String browserName) throws IOException {
        if (driver == null) {
        	//Setting the WebDriver
            switch (browserName.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.get(baseurl);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    //Navigating to Quora Website
                    driver.get(baseurl);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    File launch=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            		Files.copy(launch,new File("C:\\Users\\2370657\\eclipse-workspace\\quoraSearchAutomation\\ScreenShots\\websitelaunch.jpg"));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser name: " + browserName);
            }
        }
        return driver;
    }
	 	public static void SignInButton() throws IOException {
	    	WebElement buttonElement = driver.findElement(By.xpath("//button[@class='q-click-wrapper b17i7nxr b1l8alrs bobc9nh b1cg7ppz c1nud10e qu-active--textDecoration--none qu-focus--textDecoration--none qu-ml--medium qu-borderRadius--pill qu-alignItems--center qu-justifyContent--center qu-whiteSpace--nowrap qu-userSelect--none qu-display--inline-flex qu-bg--red qu-tapHighlight--white qu-textAlign--center qu-cursor--pointer qu-hover--textDecoration--none']"));
	    	buttonElement.click();
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    	File launch=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		Files.copy(launch,new File("C:\\Users\\2370657\\eclipse-workspace\\quoraSearchAutomation\\ScreenShots\\signin.jpg"));
	    }
	 	public static void SignUpButton() throws IOException {
	    	WebElement SignUpElement = driver.findElement(By.xpath("//div[contains(text(),'with email')]"));
	    	SignUpElement.click();
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    	File launch=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		Files.copy(launch,new File("C:\\Users\\2370657\\eclipse-workspace\\quoraSearchAutomation\\ScreenShots\\signup.jpg"));
	    }
	    public static void VerifySignUpButtonDisabled() throws IOException {
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    	WebElement signUpButton = driver.findElement(By.xpath("//div[@class='q-flex qu-justifyContent--flex-end qu-alignItems--center']//button"));
	    	boolean isDisabled = signUpButton.isEnabled();
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    	File launch=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		Files.copy(launch,new File("C:\\Users\\2370657\\eclipse-workspace\\quoraSearchAutomation\\ScreenShots\\buttonstatus.jpg"));
	    	System.out.println(isDisabled);
	    }
	    public static void signUpEmail() throws IOException {
	    	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    	WebElement signUpEmailField = driver.findElement(By.xpath("//input[@id='email']"));
	    	file = new FileInputStream("C:\\Users\\2370657\\eclipse-workspace\\quoeraSerchAutomation\\testdata\\data.xlsx");
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet("Sheet1");
			row = sheet.getRow(2);
			String search=row.getCell(0).getStringCellValue();
			//entering input
			signUpEmailField.sendKeys(search);
			String errorMessage = driver.findElement(By.xpath("//div[@class='q-text qu-dynamicFontSize--small qu-color--red_error']")).getText();
			System.out.println(errorMessage);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			File launch=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		Files.copy(launch,new File("C:\\Users\\2370657\\eclipse-workspace\\quoraSearchAutomation\\ScreenShots\\signupDetails.jpg"));
	    }
	    public static void returnLogin() throws IOException {
	    	driver.findElement(By.xpath("//button[@aria-label='Dismiss']//span[@class='c8lvhxo c1qi64n3 c13dhsxm']//*[name()='svg']")).click();
	    	File launch=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		Files.copy(launch,new File("C:\\Users\\2370657\\eclipse-workspace\\quoraSearchAutomation\\ScreenShots\\login.jpg"));
	    }
	    public static void LoginActions() throws IOException, InterruptedException {
		WebElement LoginClick =  driver.findElement(By.xpath("//div[normalize-space()='Login']"));
		LoginClick.click();
		//thread.wait(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement userField = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement passField = driver.findElement(By.xpath("//input[@id='password']"));
		file = new FileInputStream("C:\\Users\\2370657\\eclipse-workspace\\quoeraSerchAutomation\\testdata\\data.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet("Sheet1");
		row = sheet.getRow(1);
		String userName=row.getCell(0).getStringCellValue();
		String pass=row.getCell(1).getStringCellValue();
		userField.sendKeys(userName);
		passField.sendKeys(pass);
		driver.findElement(By.xpath("//div[contains(text(),'Login')]")).click();
		File launch=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(launch,new File("C:\\Users\\2370657\\eclipse-workspace\\quoraSearchAutomation\\ScreenShots\\loginaction.jpg"));
	}
	public static void enterSearchValue() throws IOException{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement searchBox = driver.findElement(By.cssSelector("input.q-input.puppeteer_test_selector_input"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		file = new FileInputStream("C:\\Users\\2370657\\eclipse-workspace\\quoeraSerchAutomation\\testdata\\data.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet("Sheet2");
		row = sheet.getRow(1);
		String search=row.getCell(0).getStringCellValue();
		//entering input
		searchBox.sendKeys(search);
		File launch=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(launch,new File("C:\\Users\\2370657\\eclipse-workspace\\quoraSearchAutomation\\ScreenShots\\searchValue.jpg"));
		driver.findElement(By.xpath("//div[@id='selector-option-0']//div//div[@class='q-text qu-dynamicFontSize--regular']")).click();
		Boolean searchResult = driver.findElement(By.xpath("//span[@class='q-text qu-bold'][normalize-space()='Testing']")).isDisplayed();
		System.out.print(searchResult);
		//taking screenshot
		File box=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(box,new File("C:\\Users\\2370657\\eclipse-workspace\\quoraSearchAutomation\\ScreenShotsSearchBoxResults.jpg"));
	}
	public static void quitDriver()  {
            driver.quit();
		}
}

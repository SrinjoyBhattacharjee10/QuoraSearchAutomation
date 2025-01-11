package quoraSearchAutomation;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import java.io.IOException;
	public class main {
		@BeforeTest
		@Parameters("browser")
		public  void LaunchApplication(String br) throws IOException, InterruptedException{
			//Launch the Application
			DriverSetup.getDriver(br);
		}
		
		@Test(priority=1)
		public  void clickSignin() throws IOException{
			//Launch the Application
			DriverSetup.SignInButton();		
		}
		@Test(priority=2)
		public void clickSignupEmail() throws IOException {
			DriverSetup.SignUpButton();
		}
		@Test(priority=3)
		public void signUpButtonStatus() throws IOException {
			DriverSetup.VerifySignUpButtonDisabled();
		}
		@Test(priority=4)
		public void emailVerification() throws IOException {
			DriverSetup.signUpEmail();
		}
		@Test(priority = 5)
		public void returnToLogin() throws IOException {
			DriverSetup.returnLogin();
		}
		
		@Test(priority=6)
		public  void Login() throws IOException, InterruptedException{
			//Launch the Application
			DriverSetup.LoginActions();		
		}
		
		@Test(priority=7)
	    public void EnteringSearchValue()throws IOException {
			//Enter the search value into the searchbox
			DriverSetup.enterSearchValue();
		}
		@AfterTest
		public void CloseApplication() throws IOException{
			//Close the Application
			DriverSetup.quitDriver();
		}
		
	}
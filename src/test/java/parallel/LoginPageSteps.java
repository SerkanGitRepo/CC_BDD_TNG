package parallel;

import org.junit.Assert;

import com.aventstack.extentreports.GherkinKeyword;
import com.pages.LoginPage;

import io.cucumber.java.en.*;
import utilities.DriverFactory;
//import utilities.baseUtil;

public class LoginPageSteps {
	
	private LoginPage loginPage=new LoginPage(DriverFactory.getDriver());
	private String title;
//	private baseUtil base;
	
//	public LoginPageSteps(baseUtil base) {
//		this.base=base;
//	}
	
	@Given("user is on login page")
	public void user_is_on_login_page() throws Throwable {
//		scenarioDef.createNode(new GherkinKeyword("Given"), "user is on login page");
		DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
	}

	@When("user gets title of the page")
	public void user_gets_title_of_the_page() throws Throwable{
//		scenarioDef.createNode(new GherkinKeyword("When"), "user gets title of the page");
		title=loginPage.getLoginPageTitle();
		System.out.println("page title is: " + title);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) throws Throwable{
//		scenarioDef.createNode(new GherkinKeyword("Then"), "page title should be ...");
		Assert.assertTrue(title.contains(expectedTitleName));
	}

	@Then("forgot your password link should be displayed")
	public void forgot_your_password_link_should_be_displayed() throws Throwable{
//		scenarioDef.createNode(new GherkinKeyword("Then"), "forgot your password link should be displayed");
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());

	}

	@When("user enters username {string}")
	public void user_enters_username(String username) throws Throwable{
//		scenarioDef.createNode(new GherkinKeyword("When"), "user enters username");
		loginPage.enterUserName(username);
	}

	@When("user enters password {string}")
	public void user_enters_password(String password) throws Throwable{
//		scenarioDef.createNode(new GherkinKeyword("When"), "user enters password");
		loginPage.enterPassword(password);
	}

	@When("user click on login button")
	public void user_click_on_login_button() throws Throwable{
//		scenarioDef.createNode(new GherkinKeyword("When"), "user click on login button");
		loginPage.clickOnLogin();
	}

	
}

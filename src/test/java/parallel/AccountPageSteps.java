package parallel;

import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.GherkinKeyword;
import com.pages.AccountPage;
import com.pages.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import utilities.DriverFactory;
//import utilities.baseUtil;

import org.junit.Assert;


public class AccountPageSteps {
	
	private LoginPage loginPage=new LoginPage(DriverFactory.getDriver());
	private AccountPage accountPage; 
//	private baseUtil base;
	
//	public AccountPageSteps(baseUtil base) {
//		this.base=base;
//	}
//	@Before
	

    
	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable dataTable) throws InterruptedException, Throwable {
//		scenarioDef.createNode(new GherkinKeyword("Given"), "user has already logged in to application");
		List<Map<String,String>> credList=dataTable.asMaps();
		String userName=credList.get(0).get("username");
		String password=credList.get(0).get("password");
				
		DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		Thread.sleep(1000);
		accountPage=loginPage.doLogin(userName, password);
	}

	@Given("user is on Account page")
	public void user_is_on_account_page() throws Throwable{
//		scenarioDef.createNode(new GherkinKeyword("Given"), "user is on Account page");
		String title = accountPage.getsAccountPageTitle();
		System.out.println("Account page title is: " + title);
		System.out.println("Thread Id: " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
	}

	@Then("user gets account section")
	public void user_gets_account_section(DataTable sectionsTable) throws Throwable{
//		scenarioDef.createNode(new GherkinKeyword("Then"), "user gets account section");
		List<String> expAccountSectionslist=sectionsTable.asList();
		System.out.println("Expected account section lists: " + expAccountSectionslist);
		
		List<String> actAccountSectionslist=accountPage.getAccountSectionsList();
		System.out.println("Actual account section lists: " + actAccountSectionslist);
		Assert.assertTrue(expAccountSectionslist.containsAll(actAccountSectionslist));
		
	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer expectedSectionCount) throws Throwable{
//		scenarioDef.createNode(new GherkinKeyword("Then"), "accounts section count should be 6");
		Assert.assertTrue(accountPage.getAccountsSectionCount()==expectedSectionCount);
	}

}

package parallel; 

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


//import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
 
import io.cucumber.testng.CucumberOptions; 

//Created a custom plugin
@CucumberOptions(
		plugin = {"cucumberHooks.customReportListener"},
		monochrome=true,
		glue = {"parallel","cucumberHooks"},
		features= {"src\\test\\resources\\parallel"}
		)

public class ParallelRun extends AbstractTestNGCucumberTests {
	
	@DataProvider(parallel=true)
	@Override
	public Object[][] scenarios(){
		return super.scenarios();
	}
	
}

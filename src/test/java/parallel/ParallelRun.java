package parallel;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;


@RunWith(CucumberWithSerenity.class)
//@CucumberOptions(
//		plugin = {"pretty"},
//		monochrome=true,
//		glue = {"parallel"},
//		features= {"src/test/resources/parallel"}
//		)

public class ParallelRun extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
	}

}

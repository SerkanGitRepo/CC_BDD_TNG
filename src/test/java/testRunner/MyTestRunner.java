package testRunner;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features= {"src/test/resources/parallel"},
		glue = {"parallel"},
		plugin = {"pretty"}
		)
public class MyTestRunner {

}
//,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
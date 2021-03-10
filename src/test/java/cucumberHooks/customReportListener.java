package cucumberHooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestSourceRead;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;
import utilities.DriverFactory;
import utilities.ExtentReportUtil;
import utilities.JiraServiceProvider;
import io.cucumber.plugin.event.HookTestStep;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class customReportListener implements EventListener{

	private ExtentSparkReporter spark;
    private ExtentReports extent;
    WebDriver driver;

    Map<String, ExtentTest> feature = new HashMap<String, ExtentTest>();
    ExtentTest scenario;
    ExtentTest step;
    
    public customReportListener() {
    };
    
    
    
	@Override
	public void setEventPublisher(EventPublisher publisher) {
		// TODO Auto-generated method stub
		 /*
         * :: is method reference , so this::collecTag means collectTags method in
         * 'this' instance. Here we says runStarted method accepts or listens to
         * TestRunStarted event type
         */
        publisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
        publisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
        publisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
        publisher.registerHandlerFor(TestCaseStarted.class, this::ScenarioStarted);
        publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, event -> {
			try {
				stepFinished(event);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		
	};
	
	/*
     * Here we set argument type as TestRunStarted if you set anything else then the
     * corresponding register shows error as it doesn't have a listner method that
     * accepts the type specified in TestRunStarted.class
     */


    // Here we create the reporter
    private void runStarted(TestRunStarted event) {
    	String path = "reports/index.html";
        spark = new ExtentSparkReporter(path);
        extent = new ExtentReports();
        spark.config().setTheme(Theme.DARK);
        // Create extent report instance with spark reporter
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Automation");
    };


    // TestRunFinished event is triggered when all feature file executions are
    // completed
    private void runFinished(TestRunFinished event) {
        extent.flush();
    };


    // This event is triggered when feature file is read
    // here we create the feature node
    private void featureRead(TestSourceRead event) {
        String featureSource = event.getUri().toString();
        String featureName = featureSource.split(".*/")[1];


        if (feature.get(featureSource) == null) {


            feature.putIfAbsent(featureSource, extent.createTest(featureName));
        }
    };


    // This event is triggered when Test Case is started
    // here we create the scenario node
    private void ScenarioStarted(TestCaseStarted event) {
        String featureName = event.getTestCase().getUri().toString();

        
        String featureNameHTML = "<b style='font-size:20px;color:#ffcc66'>" + event.getTestCase().getName() + "</b>";
		
		scenario = feature.get(featureName).createNode(featureNameHTML);
        //scenario = feature.get(featureName).createNode(event.getTestCase().getName());
    };


    // step started event
    // here we creates the test node
    private void stepStarted(TestStepStarted event) {


        String stepName = " ";
        String keyword = " ";
//        String keyword = "Triggered the hook :";


        // We checks whether the event is from a hook or step
        if (event.getTestStep() instanceof PickleStepTestStep) {
            // TestStepStarted event implements PickleStepTestStep interface
            // WHich have additional methods to interact with the event object
            // So we have to cast TestCase object to get those methods
            PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
            stepName = steps.getStep().getText();
            keyword = steps.getStep().getKeyword();
            
            step = scenario.createNode(Given.class, keyword + " " + stepName);

        } 
        else {
            // Same with HoojTestStep
            HookTestStep hoo = (HookTestStep) event.getTestStep();
            stepName = hoo.getHookType().name();
            step = scenario.info("");
        }

//        step = scenario.createNode(Given.class, keyword + " " + stepName);
        
    };


    // This is triggered when TestStep is finished
    private void stepFinished(TestStepFinished event) throws ParseException {


        if (event.getResult().getStatus().toString() == "PASSED") {
            step.log(Status.PASS, "This passed");


        } else if (event.getResult().getStatus().toString() == "SKIPPED")

        {


            step.log(Status.SKIP, "This step was skipped ");
        } else {
            step.log(Status.FAIL, event.getResult().getError());
            	JiraServiceProvider jiraSp = new JiraServiceProvider();
            	String issueSummary = "Test adiminda Hata olustu";
            	String issueDescription = event.getResult().getError().toString();
            	
            	jiraSp.createJiraTicket("Bug - Hata", issueSummary, issueDescription,jiraSp.uName);
        }
    };

}

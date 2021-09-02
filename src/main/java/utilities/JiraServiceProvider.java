package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {

	public JiraClient jira;
	public String project;
	public String uName;
	private int _Day,_Month, _Year;
	private Date dtOrg;
	private String _stringDate;
	private ConfigReader configReader;
	Properties prop;
	
	public JiraServiceProvider() {
		configReader = new ConfigReader();
		prop=configReader.init_prop();
		
		String jiraUrl = prop.getProperty("jiraURL");
		String userName = prop.getProperty("jiraUser");
		String password = prop.getProperty("jiraUserPass");
		String project = prop.getProperty("jiraProject");
		
		BasicCredentials creds = new BasicCredentials(userName,password);
		
		jira = new JiraClient(jiraUrl,creds);
		this.project = 	project;
		this.uName = userName;
	}
	
	public void createJiraTicket(String issueType, String summary, String description, String reporterName) throws ParseException {
		
		try {
			FluentCreate fluentCreate = jira.createIssue(project, issueType);
			fluentCreate.field(Field.SUMMARY, summary);
			
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 3);
			dt = c.getTime();
			
			_Day= dt.getDay();
			_Month=	dt.getMonth();
			_Year= dt.getYear();
			
			_stringDate = String.valueOf(_Day) + "/" + String.valueOf(_Month) + "/" + String.valueOf(_Year);
					
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(_stringDate);
			
			fluentCreate.field(Field.DUE_DATE, date1);
			
			fluentCreate.field(Field.DESCRIPTION, description);
			fluentCreate.field(Field.REPORTER, reporterName);
			fluentCreate.field(Field.ISSUE_TYPE, issueType);
			Issue newIssue = fluentCreate.execute();
		}catch(JiraException e) {
			e.printStackTrace();
		}

			
		
	}
}

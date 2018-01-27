package net.olga.bugifyRest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class RestTestsSkippingLogic extends TestBase{

    @BeforeMethod
    public void skip() throws IOException {
        skipIfNotFixed(629);
    }
    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test Issue").withDescription("New test Issue");
        int issueId = createIssues(newIssue);
        oldIssues.add(newIssue.withId(issueId));
        Set<Issue> newIssues = getIssues();

        assertEquals(oldIssues, newIssues);
    }
}

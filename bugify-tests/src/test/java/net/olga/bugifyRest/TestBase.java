package net.olga.bugifyRest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {
    public boolean isIssueOpen(int issueId) throws IOException {
        String status = getIssueStatus(issueId);
        return !(status.equals("Closed") | status.equals("Resolved") | status.equals("Deleted"));
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
    public String getIssueStatus(int id) throws IOException {
        String json = getExecutor().execute(Request.Get(String
                .format("http://demo.bugify.com/api/issues/%s.json", id)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        String issue_status = parsed.getAsJsonObject()
                .get("issues").getAsJsonArray().get(0).getAsJsonObject()
                .get("state_name").getAsString();
        return issue_status;
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json?limit=1000"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }
    public int createIssues(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        System.out.println(parsed);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();

    }
    private Executor getExecutor() {
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");

    }

}

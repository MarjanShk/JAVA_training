package rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Admin on 18.05.2016.
 */
public class CreateIssue {
    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue addedIssue = new Issue().withDescription("newDescription_X").withSubject("newSubject_X");
        int id = createIssue(addedIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(addedIssue.withId(id));
        Assert.assertEquals(oldIssues, newIssues);
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==","");
    }

    private Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json?page=1&limit=20")).
                returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private int createIssue(Issue addedIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json").
                bodyForm(new BasicNameValuePair("subject", addedIssue.getSubject()),
                        new BasicNameValuePair("description", addedIssue.getDescription()))).returnContent().asString();
        return new JsonParser().parse(json).getAsJsonObject().get("issue_id").getAsInt();

    }
}

package mantis.tests;

import mantis.models.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
//        app.mail().stop();
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String username = app.getProperty("web.adminLogin");
//        String username = "Olga_test";
        String password = app.getProperty("web.adminPassword");

        app.newSession().login(username, password);

        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String link = findConfirmationLink(mailMessages, email);

        assertTrue(app.newSession().login(username, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}

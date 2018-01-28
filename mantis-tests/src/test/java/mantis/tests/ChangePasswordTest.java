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
        app.mail().stop();
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException, InterruptedException {
        long now = System.currentTimeMillis();
        String username = app.getProperty("web.adminLogin");
        String password = app.getProperty("web.adminPassword");
        String newPassword = "newpass" + now;
        String userResetPassw = "Olga_test";

        app.baseHelper().login(username, password);
        String email = app.baseHelper().initUserPassChange(userResetPassw);

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String link = findConfirmationLink(mailMessages, email);
        System.out.println(link);
        app.baseHelper().finishPasswordChange(link, newPassword);

        assertTrue(app.newSession().login(userResetPassw, newPassword));
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

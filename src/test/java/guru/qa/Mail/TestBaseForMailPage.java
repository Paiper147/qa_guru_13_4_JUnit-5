package guru.qa.Mail;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseForMailPage {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1519x800";
    }
}

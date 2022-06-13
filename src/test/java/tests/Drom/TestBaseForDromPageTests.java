package tests.Drom;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseForDromPageTests {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1519x800";
    }
}

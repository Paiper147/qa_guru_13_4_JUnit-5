package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MailPage {

    MailPageTests mailPageTests = new MailPageTests();

    String searchInput_1 = "Selenide";
    String searchInput_2 = "guru";

    @DisplayName("Not ParameterizedTest. Search validation of \"Selenide\"")
    @Test    
    void test1(){
        mailPageTests.openPage()
                .searchInput(searchInput_1)
                .searchClick()
                .checkResult(searchInput_1);
    }
}

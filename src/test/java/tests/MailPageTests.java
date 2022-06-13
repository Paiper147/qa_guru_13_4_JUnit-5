package tests;

import com.codeborne.selenide.Condition;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MailPageTests {

    public MailPageTests openPage(){
        open("https://mail.ru/");
        return this;
    }

    public MailPageTests searchInput(String searchData){
        $("#q").setValue(searchData);
        return this;
    }

    public MailPageTests searchClick(){
        $("#search:submit").click();
        return this;
    }
    public MailPageTests checkResult(String searchData){
        $("#js-result_1").shouldHave(Condition.text(searchData));
        return this;
    }
}

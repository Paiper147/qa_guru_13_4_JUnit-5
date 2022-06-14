package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class MailPage {

    public MailPage openPage(){
        open("https://mail.ru/");
        return this;
    }

    public MailPage searchInput(String searchData){
        $("#q").setValue(searchData);
        return this;
    }

    public MailPage searchClick(){
        $("[data-testid=search-button]").click();
        return this;
    }
    public MailPage checkResult(String searchData){
        $("#js-result").shouldHave(Condition.text(searchData));
        return this;
    }

    public List<String> returnTitlesSearchResultsInListOfStrings(){
        ElementsCollection inputSelenideCollection = $$(".SnippetResultTitle-title.result__title");
        List<String> outputTitlesListStrings = inputSelenideCollection.texts();
        return outputTitlesListStrings;
    }
}

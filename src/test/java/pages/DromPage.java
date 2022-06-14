package pages;

import com.codeborne.selenide.CollectionCondition;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DromPage {

    public DromPage openPage(){
        open("https://auto.drom.ru/");
        return this;
    }

    public DromPage brandChoice(String brand){
        $("input[placeholder=Марка]").scrollTo().click();
        $("[data-ftid=component_select_dropdown]").$(withText(brand)).click();
        return this;
    }

    public DromPage modelChoice(String model){
        $("input[placeholder=Модель]").scrollTo().click();
        $("input[placeholder=Модель]").scrollTo().sendKeys(model);
//        $("input[placeholder=Модель]").scrollTo().setValue(model).pressEnter();

        $("input[placeholder=Модель]").pressEnter();
//        $("input[value=" + model + "][placeholder=Модель]").pressEnter();
        return this;
    }

    public DromPage submitClick(){
        $("[data-ftid=sales__filter_submit-button]").scrollTo().click();
        return this;
    }

    public DromPage checkResult(String brand){
//        $("[data-ftid=bull_title]").scrollTo().shouldHave(Condition.text(brand));
        $$("[data-ftid=bull_title]").shouldHave(CollectionCondition.texts(brand));
        return this;
    }

}

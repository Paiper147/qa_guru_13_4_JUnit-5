package tests.Drom;

import com.codeborne.selenide.CollectionCondition;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DromPageTests {

    public DromPageTests openPage(){
        open("https://auto.drom.ru/");
        return this;
    }

    public DromPageTests brandChoice(String brand){
        $("input[placeholder=Марка]").scrollTo().click();
        $("[data-ftid=component_select_dropdown]").$(withText(brand)).click();
        return this;
    }

    public DromPageTests modelChoice(String model){
        $("input[placeholder=Модель]").scrollTo().click();
        $("input[placeholder=Модель]").scrollTo().sendKeys(model);
//        $("input[placeholder=Модель]").scrollTo().setValue(model).pressEnter();

        $("input[placeholder=Модель]").pressEnter();
//        $("input[value=" + model + "][placeholder=Модель]").pressEnter();
        return this;
    }

    public DromPageTests submitClick(){
        $("[data-ftid=sales__filter_submit-button]").scrollTo().click();
        return this;
    }

    public DromPageTests checkResult(String brand){
//        $("[data-ftid=bull_title]").scrollTo().shouldHave(Condition.text(brand));
        $$("[data-ftid=bull_title]").shouldHave(CollectionCondition.texts(brand));
        return this;
    }

}

package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {

    private static final SelenideElement FIRST_NAME_INPUT =  $("#userName");
    private static final SelenideElement EMAIL_INPUT =  $("#userEmail");
    private static final SelenideElement CURRENT_ADDRESS_INPUT =  $("#currentAddress");
    private static final SelenideElement SUBMIT_BUTTON =  $("#submit");
    private static final SelenideElement FIRST_NAME_RESULT =  $("p[id=name]");
    private static final SelenideElement EMAIL_RESULT =  $("p[id=email]");
    private static final SelenideElement CURRENT_ADDRESS_RESULT =  $("p[id=currentAddress]");

    public TextBoxPage openPage(){
        open("/text-box");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#close-fixedban').remove()");
        return this;
    }

    public TextBoxPage inputName(String name){
        FIRST_NAME_INPUT.setValue(name);
        SUBMIT_BUTTON.click();
        return this;
    }
    public TextBoxPage inputIncorrectEmail(String email){
        EMAIL_INPUT.setValue(email);
        SUBMIT_BUTTON.click();
        return this;
    }

    public TextBoxPage inputCurrentAddress(String address){
        CURRENT_ADDRESS_INPUT.setValue(address);
        SUBMIT_BUTTON.click();
        return this;
    }

    public TextBoxPage checkInputName(String name){
        FIRST_NAME_RESULT.shouldHave(Condition.text(name));
        return this;
    }
    public TextBoxPage checkInputIncorrectEmail(){
       $(".field-error").shouldBe(Condition.visible);
        return this;
    }
    public TextBoxPage checkInputCurrentAddress(String address){
        CURRENT_ADDRESS_RESULT.shouldHave(Condition.text(address));
        return this;
    }
}

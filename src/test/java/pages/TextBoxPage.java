package pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.TestBaseForTextBoxTests;
import tests.TextBoxTests;

public class TextBoxPage extends TestBaseForTextBoxTests {

    TextBoxTests textBoxTests = new TextBoxTests();

    @DisplayName("НЕ параметризованный тест - Name")
    @Test
    void inputName(){
        textBoxTests.openPage()
                .inputName("Иван")
                .checkInputName("Иван");
    }

    @DisplayName("НЕ параметризованный тест - Email")
    @Test
    void first(){
        textBoxTests.openPage()
                .inputIncorrectEmail("fe")
                .checkInputIncorrectEmail();
    }

    @DisplayName("НЕ параметризованный тест - CurrentAddress")
    @Test
    void inputCurrentAddress(){
        textBoxTests.openPage()
                .inputCurrentAddress("Какой-то адрес")
                .checkInputCurrentAddress("Какой-то адрес");
    }

    @ValueSource(strings = {"1", "12345", "1234567890"})
    @ParameterizedTest(name = "Разная длина имени. Вводим {0} - получаем {0}")
    void differentNamesThrothValueSource(String testData){
        textBoxTests.openPage()
                .inputName(testData)
                .checkInputName(testData);
    }

    @ValueSource(strings = {"1", "1234567890", "1234567890 1234567890"})
    @ParameterizedTest(name = "Разная длина адреса. Вводим {0} - получаем {0}")
    void differentAddressesValueSource(String testData){
        textBoxTests.openPage()
                .inputCurrentAddress(testData)
                .checkInputCurrentAddress(testData);
    }

    @CsvSource(value = {
            "КороткоеИмя, КороткоеИмя",
            "Имя с пробелами, Имя с пробелами",
            "ИмяСоСпецСимволами!\"№;%:?*()_-+=, ИмяСоСпецСимволами!\"№;%:?*()_-+=",
    })
    @ParameterizedTest(name = "Разные виды имени CsvSource. Вводим {0} - получаем {1}")
    void complexNameTestCsvSource(String inputData, String outputData){
        textBoxTests.openPage()
                .inputName(inputData)
                .checkInputName(outputData);
    }

    @CsvFileSource(resources = "/testData/forTextBoxParamsTests.csv")
    @ParameterizedTest(name = "Разные виды имени CsvFileSource. Вводим {0} - получаем {1}")
    void complexNameTestCsvFileSource(String inputData, String outputData){
        textBoxTests.openPage()
                .inputName(inputData)
                .checkInputName(outputData);
    }





}

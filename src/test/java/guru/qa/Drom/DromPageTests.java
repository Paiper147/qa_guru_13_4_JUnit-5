package guru.qa.Drom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.DromPage;

public class DromPageTests extends TestBaseForDromPageTests {

    DromPage dromPagePage = new DromPage();

    @DisplayName("Not ParameterizedTest. Search validation of \"Лада\"")
    @Test
    void test1(){
        dromPagePage.openPage()
                .brandChoice(TestDataForDromPage.BRAND_1)
                .submitClick()
                .checkResult(TestDataForDromPage.BRAND_1);
    }

    @DisplayName("Not ParameterizedTest. Search validation of \"Kia\"")
    @Test
    void test2(){
        dromPagePage.openPage()
                .brandChoice(TestDataForDromPage.BRAND_2)
                .submitClick()
                .checkResult(TestDataForDromPage.BRAND_2);
    }

    @ValueSource(strings = {TestDataForDromPage.BRAND_1, TestDataForDromPage.BRAND_2})
    @ParameterizedTest(name = "ParameterizedTest. ValueSource. Search validation of {0}")
    void valueSourceTestBrands(String testData){
        dromPagePage.openPage()
                .brandChoice(testData)
                .submitClick()
                .checkResult(testData);
    }

    @CsvSource(value = {
            "Лада, Гранта",
            "Kia, Rio"
    })
    @ParameterizedTest(name = "ParameterizedTest. CsvSource. Input {0}, OutputCheck {1}")
    void csvSourceTestBrands(String brand, String model){
        dromPagePage.openPage()
                .brandChoice(brand)
                .modelChoice(model)
                .submitClick()
                .checkResult(model);
    }




}

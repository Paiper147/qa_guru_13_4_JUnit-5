package pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.Drom.DromPageTests;
import tests.Drom.TestBaseForDromPageTests;
import tests.Drom.TestDataForDromPage;

public class DromPage extends TestBaseForDromPageTests {

    DromPageTests dromPageTests = new DromPageTests();

    @DisplayName("Not ParameterizedTest. Search validation of \"Лада\"")
    @Test
    void test1(){
        dromPageTests.openPage()
                .brandChoice(TestDataForDromPage.BRAND_1)
                .submitClick()
                .checkResult(TestDataForDromPage.BRAND_1);
    }

    @DisplayName("Not ParameterizedTest. Search validation of \"Kia\"")
    @Test
    void test2(){
        dromPageTests.openPage()
                .brandChoice(TestDataForDromPage.BRAND_2)
                .submitClick()
                .checkResult(TestDataForDromPage.BRAND_2);
    }

    @ValueSource(strings = {TestDataForDromPage.BRAND_1, TestDataForDromPage.BRAND_2})
    @ParameterizedTest(name = "ParameterizedTest. ValueSource. Search validation of {0}")
    void valueSourceTestBrands(String testData){
        dromPageTests.openPage()
                .brandChoice(testData)
                .submitClick()
                .checkResult(testData);
    }

    @CsvSource(value = {
            "Лада, Гранта",
            "Kia, Rio"
    })
    @ParameterizedTest(name = "ParameterizedTest. ValueSource. Search validation of {0}")
    void csvSourceTestBrands(String brand, String model){
        dromPageTests.openPage()
                .brandChoice(brand)
                .modelChoice(model)
                .submitClick()
                .checkResult(model);
    }




}

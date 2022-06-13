package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ParamsTest {

    @Test
    void yaTestJUnit5(){
        Selenide.open("https://ya.ru");
        $("#text").setValue("JUnit 5");
        $("button[type='submit']").click();
//        $(".serp-item").shouldHave(text("JUnit 5"));
        $$("li.serp-item").find(text("JUnit 5")).shouldBe(visible);
    }

    @Test
    void yaTestTestNG(){
        Selenide.open("https://ya.ru");
        $("#text").setValue("TestNG");
        $("button[type='submit']").click();
        $$("li.serp-item").find(text("TestNG")).shouldBe(visible);
    }

    @ValueSource(strings = {"JUnit 5", "TestNG"})
//    @DisplayName("") - НЕ используется в параметризованных тестах
    @ParameterizedTest(name = "При поиске в Яндексе по запросу {0} в результатах отображается текст {0}")
    void yaTestCommon(String testData){
        Selenide.open("https://ya.ru");
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").find(text(testData)).shouldBe(visible);
    }

    //в csv файле по умолчанию разделителем является запятая ","
    @CsvSource(value = {
            "JUnit 5, team’s statement on the war in Ukraine",
            "TestNG, is a testing framework designed to simplify a broad range of testing needs"
    })
    @ParameterizedTest(name = "При поиске в Яндексе по запросу {0} в результатах отображается текст {1}")
    void yaTestComplexCsvSource(String searchData, String expectedResult){
        Selenide.open("https://ya.ru");
        $("#text").setValue(searchData);
        $("button[type='submit']").click();
        $$("li.serp-item").find(text(expectedResult)).shouldBe(visible);
    }

    @CsvFileSource(resources = "/testData/forParamsTest.csv")
    @ParameterizedTest(name = "При поиске в Яндексе по запросу {0} в результатах отображается текст {1}")
    void yaTestComplexCsvFileSource(String searchData, String expectedResult){
        Selenide.open("https://ya.ru");
        $("#text").setValue(searchData);
        $("button[type='submit']").click();
        $$("li.serp-item").find(text(expectedResult)).shouldBe(visible);
    }

//    Если нужны разные типы данных и при этом это НЕ примитивные типы (int, char, boolean...),
//    То аннотации выше НЕ подойдут

//    @MethodSource означает, что у нас должен быть специальный метод,
//    который предоставляет аргументы
//    Этот метод:
//    1. Должен быть статический - static
//    2. Должен возвращать Stream<Arguments>
//    3. Называться может также как тест ИЛИ иначе (если иначе, то @MethodSource(value = "название метода данных")
    static Stream<Arguments> yaTestVeryComplexDataProvider(){
        return Stream.of(
                Arguments.of("JUnit 5", List.of("JUnit 5", "framework")),
                Arguments.of("TestNG", List.of("JUnit 5", "framework"))
        );
    }

    @MethodSource(value = "yaTestVeryComplexDataProvider")
    @ParameterizedTest(name = "При поиске в Яндексе по запросу {0} в результатах отображается текст {1}")
    void yaTestVeryComplex(String searchData, List<String> expectedResult){
        Selenide.open("https://ya.ru");
        $("#text").setValue(searchData);
        $("button[type='submit']").click();
        $$("li.serp-item").shouldHave(CollectionCondition.texts(expectedResult));
    }

    //EnumSource
    @EnumSource(Sex.class)
    @ParameterizedTest(name = "enum поиск {0}")
    void enumTest(Sex sex){
        Selenide.open("https://ya.ru");
        $("#text").setValue(sex.descript);
        $("button[type='submit']").click();
        $$("li.serp-item").find(text(sex.descript)).shouldBe(visible);
    }
}

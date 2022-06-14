package guru.qa.Mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.MailPage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MailPageTests {

    MailPage mailPagePage = new MailPage();

    public static final String SEARCH_INPUT_1 = "Selenide";
    public static final String SEARCH_INPUT_2 = "qa";


    @DisplayName("Not ParameterizedTest. Search validation of \"" + SEARCH_INPUT_1 + "\"")
    @Test
    void test1(){
        mailPagePage.openPage()
                .searchInput(SEARCH_INPUT_1)
                .searchClick()
                .checkResult(SEARCH_INPUT_1);
    }
    @DisplayName("Not ParameterizedTest. Search validation of \"" + SEARCH_INPUT_2 + "\"")
    @Test
    void test2(){
        mailPagePage.openPage()
                .searchInput(SEARCH_INPUT_2)
                .searchClick()
                .checkResult(SEARCH_INPUT_2);
    }

    @ValueSource(strings = {SEARCH_INPUT_1, SEARCH_INPUT_2})
    @ParameterizedTest(name = "ParameterizedTest. ValueSource. Search validation of {0}")
    void valueSourceParameterizedTest(String testData){
        mailPagePage.openPage()
                .searchInput(testData)
                .searchClick()
                .checkResult(testData);
    }

    @CsvSource(value = {
            SEARCH_INPUT_1 + "," + SEARCH_INPUT_1,
            SEARCH_INPUT_2 + "," + SEARCH_INPUT_2,
            "Kia, Rio"
    })
    @ParameterizedTest(name = "ParameterizedTest. CsvSource.  Input - {0}, OutputCheck - {1}")
    void csvSourceParameterizedTest(String input, String output){
        mailPagePage.openPage()
                .searchInput(input)
                .searchClick()
                .checkResult(output);
    }
    @CsvFileSource(resources = "/testData/forMailPage.csv")
    @ParameterizedTest(name = "ParameterizedTest. CsvSource.  Input - {0}, OutputCheck - {1}")
    void csvFileSourceParameterizedTest(String input, String output){
        mailPagePage.openPage()
                .searchInput(input)
                .searchClick()
                .checkResult(output);
    }


//    We use two methods isAllExpectedListIncludedInActualList and isAllExpectedStringsIncludedInActualString
//    to check that All titles include All test data
    static Stream<Arguments> methodSourceParameterizedTestDataProvider(){
        return Stream.of(
                Arguments.of(SEARCH_INPUT_1, List.of("Selenide", "Java")),
                Arguments.of(SEARCH_INPUT_2, List.of("qa", "тестировщик"))
        );
    }
    @MethodSource(value = "methodSourceParameterizedTestDataProvider")
    @ParameterizedTest(name = "ParameterizedTest. MethodSource. ")
    void methodSourceParameterizedTest(String searchData, List<String> expectedDataListOfStrings){
        mailPagePage.openPage()
                .searchInput(searchData)
                .searchClick();
        List<String> actualListOfStrings = mailPagePage.returnTitlesSearchResultsInListOfStrings();
        boolean isInclude = isAllExpectedListIncludedInActualList(actualListOfStrings, expectedDataListOfStrings);
        Assertions.assertTrue(isInclude);
    }

    private boolean isAllExpectedListIncludedInActualList(List<String> actualList, List<String> expectedList){
        boolean isAllExpectedListIncludedInActualList = true;
        for(String actualString : actualList){
            boolean isInclude = isAllExpectedStringsIncludedInActualString(actualString, expectedList);
            if(!isInclude){
                isAllExpectedListIncludedInActualList = false;
                break;
            }
        }
        return isAllExpectedListIncludedInActualList;
    }
    private boolean isAllExpectedStringsIncludedInActualString(String actualString, List<String> expectedList) {
        boolean isAllExpectedStringsIncludedInActualString = true;
        for(String oneOfExpectedResult : expectedList){
            if(!actualString.contains(oneOfExpectedResult)){
                isAllExpectedStringsIncludedInActualString = false;
                break;
            }
        }
        return isAllExpectedStringsIncludedInActualString;
    }

//    We use Stream and isAllExpectedStringsIncludedInActualString
//    to check that All titles include AT LEAST ONE test data
    static Stream<Arguments> methodSourceParameterizedTestWithStreamDataProvider(){
        return Stream.of(
                Arguments.of(SEARCH_INPUT_1, List.of("Selenide", "Java")),
                Arguments.of(SEARCH_INPUT_2, List.of("qa", "тестировщик"))
        );
    }
    @MethodSource(value = "methodSourceParameterizedTestWithStreamDataProvider")
    @ParameterizedTest(name = "ParameterizedTest. MethodSource. ")
    void methodSourceParameterizedTestWithStream(String searchData, List<String> expectedDataListOfStrings){
        mailPagePage.openPage()
                .searchInput(searchData)
                .searchClick();
        List<String> actualListOfStrings = mailPagePage.returnTitlesSearchResultsInListOfStrings();

        int resultSize = actualListOfStrings.stream()
                .filter(oneOfActualString -> isAllExpectedStringsIncludedInActualString(oneOfActualString, expectedDataListOfStrings))
                .collect(Collectors.toList())
                .size();
        Assertions.assertTrue(resultSize > 0);
    }

}

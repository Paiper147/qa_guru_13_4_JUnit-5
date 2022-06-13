package guru.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Учебный тест")
public class SimpleTests {

    @DisplayName("Проверяем, что 3 > 2")
    @Disabled("причина исключения метода тестирования из набора тестов ИЛИ ссылка на таск в Jira")
    @Test
    void simpleTest1(){
        Assertions.assertTrue(3 > 2);
    }

    @DisplayName("Проверяем, что 3 <= 2")
    @Test
    void simpleTest(){
        Assertions.assertFalse(3 > 2);
    }

}

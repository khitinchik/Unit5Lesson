package guru.qa;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestWeb {
    @BeforeEach
    void openBrowser() {
        Configuration.browserSize = "1960x1024";
        open("https://www.ikea.com/ru/ru/");
    }

    @AfterEach
    void After() {
        closeWebDriver();
    }

    @DisplayName("Поиск в Икее")
    //Параметризованная аннотация с использованием Value
    @ValueSource(strings = {
            "BLÅHAJ БЛОХЭЙ",
            "CACTACEAE КАКТУС"
    })

    @ParameterizedTest(name = "Проверка поиска ikea по поиску {0}")
    void searchTest(String TestData) {

        //Предусловия
        // void searchTestValue(String TestData) {
        // closeWebDriver();
        //  Configuration.browserSize = "1960x1024";
        //open("https://www.ikea.com/ru/ru/");

        // Шаги
        $(".search-field__input").setValue(TestData).pressEnter();
        $("#onetrust-accept-btn-handler").click();
        $("#search-results").scrollTo();

        //Ожидаемый результат
        $$(".pip-product-compact__bottom-wrapper")
                .find(text(TestData))
                .click();
    }


    @CsvSource(value = {
            "BLÅHAJ БЛОХЭЙ|BLÅHAJ БЛОХЭЙ|Мягкая игрушка, акула",
            "CACTACEAE КАКТУС|CACTACEAE КАКТУС|Растение в горшке"
    },
            delimiter = '|')
    @ParameterizedTest(name = "Поиск на сайте Икея {0}, ожидает описание: {1}")
    void searchLine(String object,String objectClick,String description) {
        open("https://www.ikea.com/ru/ru/");
        $(".search-field__input").setValue(object).pressEnter();
        $("#onetrust-accept-btn-handler").click();
        $("#search-results").scrollTo();
        $$(".pip-product-compact__bottom-wrapper")
                .find(text(objectClick))
                .click();
        $$(".pip-header-section__description-text")
                .find(text(description))
                .shouldBe(visible);

    }
}





























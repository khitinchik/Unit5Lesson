package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Поиск фильмов на zetflix")
public class TestWithParametersTwo {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://hd.zetfix.online";

    }

    @ValueSource(strings = {
            "Бэтмен",
            "Голяк"
    })
    @ParameterizedTest(name = "Поиск {0} на zetflix")
    void searchTest(String testData) {
        open("/search.html?do=search");
        $("#searchinput").setValue(testData);
        $("#dosearch").click();
        $$(".sres-text")
                .find(text(testData))
                .shouldBe(visible);
    }


    @DisplayName("Тест с использованием CSV")
    @CsvSource(value = {
            "Бэтмен|Перед нами знаменитый мегаполис, который имеет название Готэм",
            "Голяк|Многосерийные криминального характера приключения Дилана"
    },
            delimiter = '|')
    @ParameterizedTest(name = "Поиск на zetflix {0}, ожидает описание: {1}")
    void searchLine(String movie, String description) {
        open("/search.html?do=search");
        $("#searchinput").setValue(movie);
        $("#dosearch").click();
        $$(".sres-desc")
                .find(Condition.text(description))
                .shouldBe(Condition.visible);

    }

    static Stream<Arguments> methodSourceExampleTest() {
        return Stream.of(
                Arguments.of("/film/"),
                Arguments.of("/serials/"),
                Arguments.of("/cartoons/")
        );
    }

    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest(name = "Поиск типа {0} на zetflix")
    void methodSourceExampleTest(String showType) {
        open(showType);
    }

        @EnumSource(Zetflix.class)
        @ParameterizedTest(name = "Поиск категории")
        void searchCategory (Zetflix Category) {
                open("https://hd.zetfix.online");
        $$(".submenu")
                .find(text("Фильмы")).hover();

        $$(".submenu .hidden-ul > li")
                        .find(Condition.text(Category.rusName))
                        .click();


        }
    }
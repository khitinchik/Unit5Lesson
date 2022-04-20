package guru.qa;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Найдем акулу в Икее")
public class TestWeb {

   // Предусловия:
   @BeforeEach
   void beforeEach() {
      Configuration.browserSize = "1960x1024";
      open("https://www.ikea.com/ru/ru/");
   }


   @Test
   @DisplayName("Обычный тест")
   void searchTest() {
      // Шаги
      $(".search-field__input").setValue("акула").pressEnter();

     $("#onetrust-accept-btn-handler").click();
     sleep(1000);
     $("#search-results").scrollTo();
     $(".pip-product-compact__bottom-wrapper").$(byText("BLÅHAJ БЛОХЭЙ")).click();

   }
}


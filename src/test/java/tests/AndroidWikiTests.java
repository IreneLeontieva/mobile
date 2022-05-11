package tests;

import com.codeborne.selenide.Condition;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class AndroidWikiTests extends TestBase {

    @Owner("ileonteva")
    @DisplayName("Search in wiki")
    @Tag("selenide")
    @Test
    void searchTest() {
        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("Dickens");
        });
        step("Verify content found", () ->
                $$(byClassName("android.widget.TextView")).shouldHave(sizeGreaterThan(0)));

    }

    @Owner("ileonteva")
    @DisplayName("Verify wiki")
    @Tag("selenide")
    @Test
    void verifyCustomizeBlock() {
        step("Verify customize block", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/view_announcement_text"))
                    .shouldHave(Condition.text("Customize your Explore feed"));
        });
    }

    @Owner("ileonteva")
    @DisplayName("Verify saved block")
    @Tag("selenide")
    @Test
    void verifySavedBlock() {
        step("Verify saved block", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/navigation_bar_item_small_label_view")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/messageTitleView"))
                    .shouldHave(Condition.text("Sync reading lists"));
        });
    }
}

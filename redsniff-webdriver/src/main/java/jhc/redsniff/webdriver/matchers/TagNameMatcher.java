package jhc.redsniff.webdriver.matchers;

import static jhc.redsniff.internal.matchers.MatcherUtil.matchAndDiagnose;
import static jhc.redsniff.internal.matchers.StringMatcher.isString;
import jhc.redsniff.internal.locators.MatcherLocator;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class TagNameMatcher extends MatcherByLocator {

    private final String tagName;

    public TagNameMatcher(String tagName) {
        super(tagName);
        this.tagName = tagName;
    }

    @Override
    public String nameOfAttributeUsed() {
        return "tagName";
    }

    @Override
    public By getByLocator(String literalTagname) {
        return By.tagName(literalTagname);
    }

    @Override
    public Matcher<WebElement> getWrappedMatcher(Matcher<String> stringMatcher) {
        return new TypeSafeDiagnosingMatcher<WebElement>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("has tagname \"" + tagName + "\"");
            }

            @Override
            protected boolean matchesSafely(WebElement actualItem, Description mismatchDescription) {
                return matchAndDiagnose(isString(tagName),
                        actualItem.getTagName(),
                        mismatchDescription,
                        "tagName ");
            }
        };
    }

    @Override
    public void describeLocatorTo(Description description) {
        if (usedLiteralArgument())
            description.appendText(tagName);
        else
            super.describeLocatorTo(description);
    }

    @Override
    public int specifity() {
    	return 90;
    }
    
    @Factory
    public static MatcherLocator<WebElement, SearchContext> hasTagName(String value) {
        return new TagNameMatcher(value);
    }

}
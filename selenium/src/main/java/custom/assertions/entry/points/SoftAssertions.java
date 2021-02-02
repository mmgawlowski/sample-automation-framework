package custom.assertions.entry.points;

import org.assertj.core.util.CheckReturnValue;
import org.openqa.selenium.WebElement;
import custom.assertions.WebElementAssert;

import javax.annotation.Generated;

/**
 * Entry point for soft custom.assertions of different data types.
 */
@Generated(value="assertj-custom.assertions-generator")
public class SoftAssertions extends org.assertj.core.api.SoftAssertions {

  /**
   * Creates a new "soft" instance of <code>{@link WebElementAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @CheckReturnValue
  public WebElementAssert assertThat(WebElement actual) {
    return proxy(WebElementAssert.class, WebElement.class, actual);
  }

}

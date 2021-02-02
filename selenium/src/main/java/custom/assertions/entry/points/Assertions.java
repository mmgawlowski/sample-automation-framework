package custom.assertions.entry.points;

import org.assertj.core.util.CheckReturnValue;
import org.openqa.selenium.WebElement;
import custom.assertions.WebElementAssert;

import javax.annotation.Generated;

/**
 * Entry point for custom.assertions of different data types. Each method in this class is a static factory for the
 * type-specific assertion page.objects.
 */
@Generated(value="assertj-custom.assertions-generator")
public class Assertions {

  /**
   * Creates a new instance of <code>{@link WebElementAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @CheckReturnValue
  public static WebElementAssert assertThat(WebElement actual) {
    return new WebElementAssert(actual);
  }

  /**
   * Creates a new <code>{@link Assertions}</code>.
   */
  protected Assertions() {
    // empty
  }
}

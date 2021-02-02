package custom.assertions;

import java.lang.reflect.InvocationTargetException;

/**
 * {@code FluentAssertions} is an interface that, while writing tests,
 * allows to move from page.objects action classes to page.objects assertion classes
 * and back again at any time without breaking the fluent interface chain.
 * <p>
 * This way, there is no need to assume in advance
 * which methods in the given action class should return a new instance of the given assertion class or vice versa.
 */
public interface FluentAssertions {

    /**
     * Creates and returns a new instance of the specified class.
     * It is intended to be used when a test proceeds to an assertion block.
     *
     * @param clazz the given class.
     * @param <T> the type of an object of the given class.
     * @return a new instance of the given class.
     * @throws IllegalStateException if a new instance cannot be created.
     */
    default <T> T startAssertion(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(String.format("Couldn't create a new instance of %s. Cause: %s", clazz.getSimpleName(), e.getCause()));
        }
    }

    /**
     * Creates and returns a new instance of the specified class.
     * It is intended to be used when a test completes an assertion block and proceeds to the next action block.
     *
     * @param clazz the given class.
     * @param <T> the type of an object of the given class.
     * @return a new instance of the given class.
     * @throws IllegalStateException if a new instance cannot be created.
     */
    default <T> T endAssertion(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(String.format("Couldn't create a new instance of %s. Cause: %s", clazz.getSimpleName(), e.getCause()));
        }
    }

}

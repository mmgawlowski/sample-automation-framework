package utils.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * The purpose of this class and its method is to modify the annotation of all test methods
 * so that they use {@link RetryAnalyzer}.
 * <p>
 * This way you can set the test to repeat in case of failure for all test methods at once
 * without direct changes to the code.
 *
 * @see <a href="https://testng.org/doc/documentation-main.html#annotationtransformers">IAnnotationTransformer</a>
 * @see <a href="https://www.javadoc.io/doc/org.testng/testng/latest/org/testng/IAnnotationTransformer.html">IAnnotationTransformer - JavaDoc</a>
 */
public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}

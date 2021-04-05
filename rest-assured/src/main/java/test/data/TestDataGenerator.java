package test.data;

import com.github.javafaker.Faker;

/**
 * This class is only responsible for providing an instance of the Faker class.
 * <p>
 * Java Faker is a fake data generator, very useful for creating tests.
 *
 * @see <a href="https://github.com/DiUS/java-faker">Java Faker</a>
 */
public class TestDataGenerator {

    public Faker faker() {
        return Faker.instance();
    }

}

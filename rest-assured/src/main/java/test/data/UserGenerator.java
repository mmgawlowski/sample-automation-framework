package test.data;

import pojo.reqres.user.UserCreateOrUpdate;

/**
 * This class implements Java Faker library to create fake user data.
 */
public class UserGenerator extends TestDataGenerator {

    /**
     * Returns a POJO class object filled with fake but realistic data,
     * which can then be serialised and used as a request body.
     *
     * @return an object of the class {@link UserCreateOrUpdate}
     *         that can be used as a request body to create or update a user.
     */
    public UserCreateOrUpdate generateUser() {
        UserCreateOrUpdate user = new UserCreateOrUpdate();
        user.setFirstName(faker().name().firstName());
        user.setLastName(faker().name().lastName());
        user.setJob(faker().job().position());
        user.setEmail(faker().internet().emailAddress());
        user.setAvatar(faker().internet().avatar());

        return user;
    }
}

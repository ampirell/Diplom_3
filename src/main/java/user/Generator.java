package user;
import com.github.javafaker.Faker;

public class Generator {

    static Faker faker = new Faker();

    public static User generateUser() {
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(6,7);
        return new User(name, email, password);
    }
    public static User generateUserWithWrongPass() {
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(5,5);
        return new User(name, email, password);
    }
}

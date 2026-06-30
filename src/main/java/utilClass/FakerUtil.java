package utilClass;

import com.github.javafaker.Faker;

public class FakerUtil {

    private static final Faker faker = new Faker();

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static String randomLastName() {
        return faker.name().lastName();
    }

    public static String randomFullName() {
        return faker.name().fullName();
    }

    public static String randomUsername() {
        return "user" + faker.number().digits(5);
    }

    public static String randomPhone() {
        return faker.number().digits(10);
    }

    public static String randomDigits(int count) {
        return faker.number().digits(count);
    }

    public static String randomString(String key) {
        switch (key) {
            case "randomEmail": return randomEmail();
            case "randomFirstName": return randomFirstName();
            case "randomLastName": return randomLastName();
            case "randomFullName": return randomFullName();
            case "randomUsername": return randomUsername();
            case "randomPhone": return randomPhone();
            default:
                if (key.startsWith("randomDigits")) {
                    int count = Integer.parseInt(key.replace("randomDigits", ""));
                    return randomDigits(count);
                }
                return key;
        }
    }
}

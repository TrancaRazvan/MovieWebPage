package MovieApp.ProiectFinal.registration;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RegistrationValidator {

    public static boolean isUsernameValid(String username) {
        int usernameLength = username.length();
        return usernameLength >= 4 && usernameLength <= 20;
    }

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

    public static boolean isNameValid(String name) {
        int nameLength = name.length();
        return nameLength >= 2 && nameLength <= 50;
    }

    public static boolean isDateOfBirthValid(String dateOfBirth) {
        try {

            LocalDate parsedDate = LocalDate.parse(dateOfBirth);

            LocalDate currentDate = LocalDate.now();
            return !parsedDate.isAfter(currentDate);
        } catch (DateTimeParseException e) {

            return false;
        }
    }
}


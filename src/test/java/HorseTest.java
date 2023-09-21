import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@DisplayName("HorseTest")
@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    @DisplayName("Horse constructor should throw IllegalArgumentException when name is null")
    void Horse_shouldThrowIllegalArgumentException_whenNameIsNull() {

        String name = null;
        double speed = 10.0;
        double distance = 20.0;
        String expectedMessage = "Name cannot be null.";

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, speed, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "' ', 10.0, 20.0",
            "'    ', 10.0, 20.0",
            "'      ', 10.0, 20.0"})
    @DisplayName("Horse constructor should throw IllegalArgumentException when name is blank")
    void Horse_shouldThrowIllegalArgumentException_whenNameIsBlank(String name, double speed, double distance) {

        String expectedMessage = "Name cannot be blank.";

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, speed, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Horse constructor should throw IllegalArgumentException when speed is negative")
    void Horse_shouldThrowIllegalArgumentException_whenSpeedIsNegative() {

        String name = "Horse";
        double negativeSpeed = -10.0;
        double distance = 20.0;
        String expectedMessage = "Speed cannot be negative.";

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, negativeSpeed, distance));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, negativeSpeed, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Horse constructor should throw IllegalArgumentException when distance is negative")
    void Horse_shouldThrowIllegalArgumentException_whenDistanceIsNegative() {

        String name = "Horse";
        double speed = 10.0;
        double negativeDistance = -20.0;
        String expectedMessage = "Distance cannot be negative.";

        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, negativeDistance));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, speed, negativeDistance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("getName method should return Horse name when first Horse constructor parameter is correct")
    void getName_shouldReturnHorseName_whenFirstHorseConstructorParameterIsCorrect() {

        String name = "Horse name";
        double speed = 10.0;
        double distance = 20.0;
        Horse horse = new Horse(name, speed, distance);

        String actualName = horse.getName();

        assertEquals(name, actualName);
    }

    @Test
    @DisplayName("getSpeed method should return Horse speed when second Horse constructor parameter is correct")
    void getSpeed_shouldReturnHorseSpeed_whenSecondHorseConstructorParameterIsCorrect() {

        String name = "Horse name";
        double speed = 10.0;
        double distance = 20.0;
        Horse horse = new Horse(name, speed, distance);

        double actualSpeed = horse.getSpeed();

        assertEquals(speed, actualSpeed);
    }

    @Test
    @DisplayName("getDistance method should return Horse distance when third Horse constructor parameter is correct")
    void getDistance_shouldReturnHorseDistance_whenThirdHorseConstructorParameterIsCorrect() {

        String name = "Horse name";
        double speed = 10.0;
        double distance = 20.0;
        Horse horse = new Horse(name, speed, distance);

        double actualDistance = horse.getDistance();

        assertEquals(distance, actualDistance);
    }

    @Test
    @DisplayName("getDistance method should return null when only two parameters was passed to Horse constructor")
    void getDistance_shouldReturnNull_whenOnlyTwoParametersWasPassedToHorseConstructor() {

        String name = "Horse name";
        double speed = 10.0;
        double distance = 0;
        Horse horse = new Horse(name, speed);

        assertEquals(distance, horse.getDistance());
    }

    @Test
    @DisplayName("move method should invoke getRandomDouble method")
    void move_shouldInvokeGetRandomDoubleMethod() {
        String name = "Test";
        double speed = 10.0;
        Horse horse = new Horse(name, speed);

        try (MockedStatic<Horse> mockStaticHorse = Mockito.mockStatic(Horse.class)) {

            horse.move();

            mockStaticHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.9, 0.2, 0.7})
    @DisplayName("move method should invoke getRandomDouble method")
    void move_shouldReturnCorrectValue_whenEnteredDateIsCorrect(double randomResult) {

        String name = "Test";
        double distance = 10.0;
        double speed = 5.0;
        Horse horse = new Horse(name, speed, distance);
        double expectedDistance = distance + speed * randomResult;

        try (MockedStatic<Horse> mockStaticHorse = Mockito.mockStatic(Horse.class)) {
            mockStaticHorse.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(randomResult);
            horse.move();
        }
        assertEquals(expectedDistance, horse.getDistance());
    }
}

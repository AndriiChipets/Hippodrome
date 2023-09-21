import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@DisplayName("HippodromeTest")
@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    @Test
    @DisplayName("Hippodrome constructor should throw IllegalArgumentException when parameter is null")
    void Hippodrome_shouldThrowIllegalArgumentException_whenParameterIsNull() {

        List<Horse> horses = null;
        String expectedMessage = "Horses cannot be null.";

        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Hippodrome constructor should throw IllegalArgumentException when parameter is empty")
    void Hippodrome_shouldThrowIllegalArgumentException_whenParameterIsEmpty() {

        List<Horse> horses = new ArrayList<>();
        String expectedMessage = "Horses cannot be empty.";

        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("getHorses method should return the same list as a parameter to constructor")
    void getHorses_shouldReturnTheSameHorseList_whenParameterToConstructorTheSameList() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse("Horse" + i, 10 + i);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(30, hippodrome.getHorses().size());
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    @DisplayName("move method should invoke method move Horse class for each Horse from the list")
    void move_shouldInvokeMethodMoveHorseClassForEachHorseFromList() {

        int horseListSize = 50;
        Horse mockHorse = Mockito.mock(Horse.class);
        doNothing().when(mockHorse).move();

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < horseListSize; i++) {
            horses.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        Mockito.verify(mockHorse, times(horseListSize)).move();
    }

    @Test
    @DisplayName("getWinner method should return Horse with biggest distance")
    void getWinner_shouldReturnHorseWithBiggestDistance() {

        List<Horse> horses = new ArrayList<>(List.of(
                new Horse("Horse1", 5.0, 10),
                new Horse("Horse2", 5.0, 20),
                new Horse("Horse3", 5.0, 30)
        ));
        Hippodrome hippodrome = new Hippodrome(horses);
        String expectedWinnerHorseName = "Horse3";
        String actualWinnerHorseName = hippodrome.getWinner().getName();

        assertEquals(expectedWinnerHorseName, actualWinnerHorseName);
    }
}

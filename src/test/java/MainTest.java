import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("MainTest")
@ExtendWith(MockitoExtension.class)
public class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22)
    @DisplayName("main method should be executed less than 22 sec")
    void main_shouldBeExecutedLessThat22Sec() throws Exception {
        Main.main(new String[]{});
    }
}

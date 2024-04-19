package csds233.tln32;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BinaryTest {
    @Test void findMin() {
        assertEquals(-1, Binary.findMin(new int[]{1, 2, 3, 4, -1, 0}));
        assertEquals(-100, Binary.findMin(new int[]{-100, 0, 1, 2, 3, 4}));
        assertEquals(0, Binary.findMin(new int[]{0}));
        assertEquals(-1, Binary.findMin(new int[]{0, 1, 2, 3, 4, 5, -1}));
    }
}

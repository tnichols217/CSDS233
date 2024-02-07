package csds233.tln32;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest 
{
    @Test
    public void testAdd()
    {
        Assertions.assertEquals( App.add(1,2), 3 );
    }
}

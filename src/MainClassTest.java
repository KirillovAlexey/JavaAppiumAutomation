import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    @Test()
    public void testGetClassNumber()
    {
        Assert.assertTrue("Число которое вернул метод getClassNumber() меньше, либо равно 45",
                new MainClass().getClassNumber()>45);
    }
}

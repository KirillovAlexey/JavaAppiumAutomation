import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    @Test()
    public void testGetLocalNumber() {
        Assert.assertEquals("Число которое вернул метод getLocalNumber() не равняется 14",
                14, new MainClass().getLocalNumber());
    }

    @Test()
    public void testGetClassNumber() {
        Assert.assertTrue("Число которое вернул метод getClassNumber() меньше, либо равно 45",
                new MainClass().getClassNumber() > 45);
    }

    @Test()
    public void testGetClassString() {
        Assert.assertTrue("Метод getClassString() вернул строку в которой нет подстроки \"hello\" или \"Hello\"",
                new MainClass().getClassString().contains("hello") ||
                        new MainClass().getClassString().contains("Hello"));
    }
}

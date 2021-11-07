import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    @Test()
    public void MainClassTest() {
        MainClass mainClass = new MainClass();
        Assert.assertEquals("Число которое вернул метод getLocalNumber() не равняется 14",
                14, mainClass.getLocalNumber());
    }
}

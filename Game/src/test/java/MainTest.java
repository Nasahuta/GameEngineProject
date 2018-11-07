import org.junit.Test;
import org.junit.Assert;
import fi.tamk.tiko.olioohjelmointi.Main;
public class MainTest {
    @Test
    public void mainTest() {
        Assert.assertEquals("X   X\nX   X\nXXXXX\nX   X\nX   X\n", Main.print(5, "X"));
        Assert.assertEquals("X   X\nXXXXX\nX   X\n", Main.print(3, "X"));
    }
}
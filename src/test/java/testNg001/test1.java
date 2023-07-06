package testNg001;

import org.automation001.calculator;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class test1 {

    @Test(enabled = true,groups = "unit")
    @Parameters({"c","d"})
    public static void testAdd(int a, int b) {
        int result = a+b;
        Assert.assertEquals(result, 5);
    }

    @Test(enabled = false)
    public static void testSub() {
        int result = calculator.sub(2, 3);
        Assert.assertEquals(result, -1);
    }

    @Test(enabled = false)
    public static void testmul() {
        int result = calculator.mul(2, 3);
        Assert.assertEquals(result, 6);
    }

    @Test(enabled = true,groups = "unit")
    public static void testdiv() {
        int result = calculator.div(2, 2);
        Assert.assertEquals(result, 1);
    }

    @Test(enabled = true,groups = "unit")
    public static void testMod() {
        int result = calculator.mod(100, 5);
        Assert.assertEquals(result, 0);
    }
}

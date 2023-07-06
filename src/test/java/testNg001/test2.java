package testNg001;

import org.automation001.calculator;
import org.automation001.homework;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class test2 {


    @Test(groups = "unit")
    public static void hw(){
        boolean result = homework.ih(2,120);
        Assert.assertEquals(result,false);
    }

    @Test(groups = {"unit","sanity"},enabled = false)
    public static void end(){
        String result = homework.end("hi");
        Assert.assertEquals(result,"HI");
    }

    @Test(groups = "sanity")
    public static void makeEnds(){
        int[] nums1 = {1,2,3};
        int[] expected = {1,3};
        int[] result = homework.makeEnds(nums1);
        Assert.assertEquals(result,expected);
    }

    @DataProvider(name = "testData")
    public Object[][] provideTestData(){
        return new Object[][]{
                {2,3,5},
                {4,6,10},
                {0,0,0}
        };
    }

    @Test(dataProvider = "testData",groups = "sanity")
    public void testAdd(int a, int b, int expectedSum){
        int result = calculator.add(a,b);
        Assert.assertEquals(result,expectedSum);
    }

}

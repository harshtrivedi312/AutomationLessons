package ExtentReports_TestNG;

public class SquirrelParty {

    public static boolean isPartySuccessful(int cigars,boolean isWeekend){
        if (isWeekend){
            return cigars >=40;
        }else{
            return cigars >=40 && cigars <=60;
        }
    }
}

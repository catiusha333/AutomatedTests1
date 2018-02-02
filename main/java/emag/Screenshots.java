package emag;

import java.time.LocalDate;
import java.util.Calendar;

public class Screenshots {

    public static Screenshots sc = new Screenshots();

//    public static void captureScreenShot(String obj) {
//        File screenshotFile=((TakesScreenshot)Environment.getInstance()).getScreenshotAs(OutputType.FILE);
//        try {
//            Files.copy(screenshotFile, new File("Screenshots\\" + obj + "" + getTimeStampValue() + ".png"));
//        }
//        catch (IOException e){
//            System.out.println(e.getStackTrace());
//        }
//    }

    public static  String getTimeStampValue(){
        Calendar cal = Calendar.getInstance();
        LocalDate time=LocalDate.now();
        String timestamp=time.toString();
        System.out.println(timestamp);
        String systime=timestamp.replace(":", "-");
        System.out.println(systime);
        return systime;
    }
}

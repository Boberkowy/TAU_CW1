package pl.edu.pjwstk.lab3;

/**
 * Created by Boberkowy on 14.03.2017.
 */
public class Time implements ITime{

    private String hour;
    private String minute;
    private String day;
    private String month;
    private String year;



    public String getTime(){
        return  (year + month + day + hour + minute );
    }

    public void setTime(String year, String month, String day, String hour, String minute){

        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }





}

package pl.edu.pjwstk.lab3;

/**
 * Created by Boberkowy on 14.03.2017.
 */
public interface IAlarmClock {

     boolean shouldRing();
     void addAlarmTime(Time time);
     void clearAlarmTime(Time time);
}

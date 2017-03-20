package pl.edu.pjwstk.lab3;


import java.util.ArrayList;
import java.util.List;

public class AlarmClock implements IAlarmClock {

   private List<Time> hours = new ArrayList<>();
   private boolean shouldRing;
   private boolean count;
   private Time time;



    public boolean shouldRing(){

        String actualTime = time.getTime();
        shouldRing = false;
        for(Time t : hours){
        if(t.getTime().equals(actualTime)) {
            if(!count) {
                shouldRing = true;
                count = true;
            }else{
                shouldRing = false;
            }
        }else{
            shouldRing = false;
            count = false;
        }
        }
        return shouldRing;
    }

    public void addAlarmTime(Time time){
        hours.add(time);
    }

    public void clearAlarmTime(Time time){
        hours.remove(time);
    }
}

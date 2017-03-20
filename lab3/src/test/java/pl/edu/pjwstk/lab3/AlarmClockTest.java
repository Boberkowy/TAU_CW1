package pl.edu.pjwstk.lab3;

/**
 * Created by Boberkowy on 19.03.2017.
 */

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class AlarmClockTest {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @TestSubject
    public AlarmClock alarmClock = new AlarmClock();

    @Mock
    public Time time;

    @Test
    public void shouldRingTest(){
        Time ring = new Time();
        ring.setTime("2017","20","03","13","37");
        alarmClock.addAlarmTime(ring);
            expect(time.getTime())
                    .andReturn("201720031337")
                    .andReturn("201720031337")
                    .andReturn("201120031337")
                    .andReturn("201720031337")
                    .andReturn("201720031337");
            replay(time);
            assertEquals(true,alarmClock.shouldRing());
            assertEquals(false,alarmClock.shouldRing());
            assertEquals(false,alarmClock.shouldRing());
            assertEquals(true,alarmClock.shouldRing());
            assertEquals(false,alarmClock.shouldRing());
        verify(time);
    }

    @Test
    public void clearAlarmTimeTest(){
        Time ring = new Time();
        ring.setTime("2017","20","03","13","37");
        alarmClock.addAlarmTime(ring);
        expect(time.getTime()).andReturn("201720031337").anyTimes();
        replay(time);
        assertEquals(true, alarmClock.shouldRing());
        alarmClock.clearAlarmTime(ring);
        assertEquals(false, alarmClock.shouldRing());
        verify(time);
    }

}

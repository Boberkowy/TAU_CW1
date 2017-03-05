import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Boberkowy on 05.03.2017.
 */

@RunWith(JUnit4.class)
public class test {

    Vector Vector1 = new VectorImpl();
    Vector Vector2 = new VectorImpl();
    Vector VectorSum = new VectorImpl();
    Vector vector = new VectorImpl();
    @Test
    public void addVectorToExistingVectorMustBeEqual(){

        Vector1.setValue(1.1,2.2);

        Vector2.setValue(3.3,4.4);
        VectorSum.setValue(4.4,6.6);
        assertEquals(VectorSum,Vector1.add(Vector2));

        Vector2.setValue(8.9,7.8);
        VectorSum.setValue(10,10);
        assertEquals(VectorSum, Vector1.add(Vector2));

        Vector2.setValue(20.2,41);
        VectorSum.setValue(21.3,43.2);
        assertEquals(VectorSum,Vector1.add(Vector2));
    }

    @Test
    public void addingTwoVectorsMustBeEqual(){
        Vector1.setValue(123.1, 321.3);
        Vector2.setValue(133.7, 733.1);
        VectorSum.setValue(256.8, 1054.4);
        assertEquals(VectorSum,vector.add(Vector1,Vector2));

        Vector1.setValue(12.1, 33.3);
        Vector2.setValue(23.2, -30.3);
        VectorSum.setValue(35.3,3);
        assertEquals(VectorSum,vector.add(Vector1,Vector2));


        Vector1.setValue(-15.3, 13.2);
        Vector2.setValue(18.3,  15.1);
        VectorSum.setValue(3, 28.3);
        assertEquals(VectorSum,vector.add(Vector1,Vector2));

    }

    @Test
    public void addVectorToExistingVectorCantBeEqual(){
        Vector1.setValue(1.1,2.2);

        Vector2.setValue(14.3,2.4);
        VectorSum.setValue(13.4,5.6);
        assertNotEquals(VectorSum,Vector1.add(Vector2));

        Vector2.setValue(33.9,72.8);
        VectorSum.setValue(32.1,101.2);
        assertNotEquals(VectorSum, Vector1.add(Vector2));

        Vector2.setValue(40.2,150.3);
        VectorSum.setValue(51.3,13.2);
        assertNotEquals(VectorSum,Vector1.add(Vector2));
    }


    @Test
    public void addingTwoVectorsCantBeEqual(){
        Vector1.setValue(321.1, 121.3);
        Vector2.setValue(13.7, 73.1);
        VectorSum.setValue(213.8, 531.3);
        assertNotEquals(VectorSum,vector.add(Vector1,Vector2));

        Vector1.setValue(123.1, 337.3);
        Vector2.setValue(223.2, -303.3);
        VectorSum.setValue(305.5,30.3);
        assertNotEquals(VectorSum,vector.add(Vector1,Vector2));


        Vector1.setValue(-151.3, 3.2);
        Vector2.setValue(138.3,  155.1);
        VectorSum.setValue(3, 28.3);
        assertNotEquals(VectorSum,vector.add(Vector1,Vector2));

    }


}

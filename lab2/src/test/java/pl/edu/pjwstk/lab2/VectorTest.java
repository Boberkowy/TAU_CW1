package pl.edu.pjwstk.lab2;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)

public class VectorTest {


        @Test
        public void addingTwoVectorsMustBeEqual() throws ErrorException {

            List<Double> vector1 = new ArrayList<>();
            List<Double> vector2 = new ArrayList<>();
            List<Double> sum     = new ArrayList<>();
            VectorImpl vector = new VectorImpl();


            vector1.add(321.3);
            vector1.add(132.0);
            vector1.add(232.0);

            vector2.add(733.1);
            vector2.add(32.3);
            vector2.add(-36.6);

            sum.add(1054.4);
            sum.add(164.3);
            sum.add(195.4);
            assertEquals(sum,vector.add(vector1,vector2));
        }


        @Test
        public void subTwoVectorsMustBeEqual() throws ErrorException{

            List<Double> vector1 = new ArrayList<>();
            List<Double> vector2 = new ArrayList<>();
            List<Double> sub = new ArrayList<>();

            VectorImpl vector = new VectorImpl();

            vector1.add(123.3);
            vector1.add(321.0);
            vector1.add(213.3);

            vector2.add(13.3);
            vector2.add(23.3);
            vector2.add(53.3);

            sub.add(110.0);
            sub.add(297.7);
            sub.add(160.0);
            assertEquals(sub, vector.sub(vector1,vector2));
        }


        @Test(expected = ErrorException.class)
        public void subTwoVectorsThrowException() throws ErrorException{

        List<Double> vector1 = new ArrayList<>();
        List<Double> vector2 = new ArrayList<>();
        List<Double> sub = new ArrayList<>();

        VectorImpl vector = new VectorImpl();

        vector1.add(123.3);
        vector1.add(321.0);
        vector1.add(213.3);

        sub.add(110.0);
        sub.add(297.7);
        sub.add(160.0);

        assertEquals(sub, vector.sub(vector1,vector2));
    }
}
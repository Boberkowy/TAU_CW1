import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class test {


    @Test
    public void addVectorToExistingVectorMustBeEqual(){

        List<Double> vector1 = new ArrayList<>();
        List<Double> vector2 = new ArrayList<>();
        List<Double> sum     = new ArrayList<>();
        VectorImpl vector = new VectorImpl();


        vector2.add(4.4);
        sum.add(6.6);
//        assertEquals(sum, vector.add(vector2));

    }

    @Test
    public void addingTwoVectorsMustBeEqual(){

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
        try{
            assertEquals(sum,vector.add(vector1,vector2));
        }catch (ErrorException e){
            e.printStackTrace();
        }

    }


}

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boberkowy on 06.03.2017.
 */
public class Main {

    public static void main(String[] args){
        Vector vector = new VectorImpl();

        List<Double> vector1 = new ArrayList<>();
        List<Double> vector2  = new ArrayList<>();
        List<Double> sum = new ArrayList<>();

        vector1.add(2.2);
        vector1.add(1.1);
        vector2.add(125.3);

        sum.add(21.3);
        sum.add(32.1);
            try{
                vector.add(vector1,vector2);
            }catch (ErrorException e){
                e.printStackTrace();
            }

    }
}

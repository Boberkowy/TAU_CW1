import java.util.List;

/**
 * Created by Boberkowy on 05.03.2017.
 */
public interface Vector {
    List<Double> add( List<Double> vector) throws ErrorException;
    List<Double> add( List<Double> vector1, List<Double> vector2 ) throws ErrorException;

}

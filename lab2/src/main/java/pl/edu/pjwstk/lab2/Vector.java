package pl.edu.pjwstk.lab2;

import java.util.List;


public interface Vector {
    List<Double> add(List<Double> vector) throws ErrorException;
    List<Double> add( List<Double> vector1, List<Double> vector2) throws ErrorException;
    List<Double> sub(List<Double> vector1, List<Double> vector2) throws ErrorException;

}
package pl.edu.pjwstk.lab2;

import java.util.ArrayList;
import java.util.List;

public class VectorImpl implements Vector{

    List<Double> sum = new ArrayList<>();

    public void setSum(List<Double> sum) {
        sum = sum;
    }

    public List<Double> getSum() {
        return sum;
    }

    public VectorImpl(){
        this.sum = new ArrayList<>();
    }


    @Override
    public List<Double> add(List<Double> vector) throws ErrorException {

        List<Double> tempList = new ArrayList<>();
        tempList.add(2.2);

        for (Double i : vector){
            tempList.add(i + vector.get(tempList.indexOf(i)));
        }
        sum.addAll(tempList);
        return sum;
    }

    @Override
    public List<Double> add(List<Double> vector1, List<Double> vector2) throws ErrorException{

        assert (vector1.size() == vector2.size()) : "Wektory muszą mieć tą samą długość";
        if(vector1.isEmpty() || vector2.isEmpty()) throw new ErrorException("Wektory nie mogą być puste");
        for(Double i : vector1){
            sum.add(i + vector2.get(vector1.indexOf(i)));
        }
        return  sum;
    }

    @Override
    public List<Double> sub(List<Double> vector1, List<Double> vector2) throws ErrorException {

        if(vector1.isEmpty() || vector2.isEmpty()) throw new ErrorException("Wektory nie mogą być puste");
        assert (vector1.size() == vector2.size()) : " Wektory muszą mieć tą samą długość";
        for(Double i : vector1){
                sum.add(i -  vector2.get(vector1.indexOf(i)));
            }
        return  sum;
    }
}

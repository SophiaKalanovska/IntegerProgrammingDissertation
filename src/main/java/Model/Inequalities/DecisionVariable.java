package Model.Inequalities;

public class DecisionVariable {
    private double weight;
    private String sign;
    private String variable;
    private double lowerBound;
    private double upperBound;


    public DecisionVariable(){
        weight = 1 ;
        variable = null;
        sign = "+";
        lowerBound = 0;
        upperBound = Double.POSITIVE_INFINITY;
    }

    public void changeSignVariable(){
        if (sign.equals("+")){
            setSign("-");
        }else{
            setSign("+");
        }
    }

    public double getWeight() {
        if (sign.equals("+")){
            return weight;
        }else{
            return -weight;
        }

    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String toString(){
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        if (lowerBound > this.lowerBound){
            this.lowerBound = lowerBound;
        }
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(double upperBound) {
        if (upperBound < this.upperBound){
            this.upperBound = upperBound;
        }

    }
//    public String getVariable() {
//        return variable;
//    }
}

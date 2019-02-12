package model;

public class DecisionVariable {
    private int weight;
    private String sign;
    private String variable;
    private int lowerBound;
    private int upperBound;


    public DecisionVariable(){
        weight = 1 ;
        variable = null;
        sign = "+";
    }

    public void changeSignVariable(){
        if (sign.equals("+")){
            setSign("-");
        }else{
            setSign("+");
        }
    }

    public int getWeight() {
        if (sign.equals("+")){
            return weight;
        }else{
            return -weight;
        }

    }

    public void setWeight(int weight) {
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

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
//    public String getVariable() {
//        return variable;
//    }
}

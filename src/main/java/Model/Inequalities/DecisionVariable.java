package Model.Inequalities;

public class DecisionVariable {
    private double weight;
    private String sign;
    private String variable;
    private double lowerBound;
    private double upperBound;

    /**
     * DecisionVariable object with default assignments to fields
     */
    public DecisionVariable(){
        weight = 1 ;
        variable = null;
        sign = "+";
        lowerBound = 0;
        upperBound = Double.POSITIVE_INFINITY;
    }

    /**
     * Changes sign
     */
    public void changeSignVariable(){
        if (sign.equals("+")){
            setSign("-");
        }else{
            setSign("+");
        }
    }

    /**
     * Returns the weight with the sign
     */
    public double getWeight() {
        if (sign.equals("+")){
            return weight;
        }else{
            return -weight;
        }

    }

    /**
     * Sets the weight of the sign
     */
    public void setWeight(double weight) {
        if (weight < 0 ){
            setSign("-");
        }else{
            this.weight = weight;
        }

    }


    /**
     * Sets the lower bound to the more restrictive value
     */
    public void setLowerBound(double lowerBound) {
        if (lowerBound > this.lowerBound){
            this.lowerBound = lowerBound;
        }
    }



    /**
     * Sets the upper bound to the more restrictive value
     */
    public void setUpperBound(double upperBound) {
        if (upperBound < this.upperBound){
            this.upperBound = upperBound;
        }
    }

    /**
     * getters and setters of the fields
     */
    public double getUpperBound() {
        return upperBound;
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
}

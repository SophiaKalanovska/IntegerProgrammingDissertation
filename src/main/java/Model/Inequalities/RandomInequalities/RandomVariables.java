package Model.Inequalities.RandomInequalities;
import org.apache.commons.lang3.RandomStringUtils;


class RandomVariables {

    private static int lowerUpper;
    private int threshold ;
    private int currentSize;

    public RandomVariables() {
        lowerUpper = 0;
        threshold = 338;
        currentSize = 2;
    }


    public String generate() {
        if (lowerUpper <= threshold) {
            String random = RandomStringUtils.randomAlphabetic(currentSize);
            lowerUpper ++;
            return random;
        } else {
            threshold = threshold*26;
            currentSize ++;
            return generate();
        }
    }
}





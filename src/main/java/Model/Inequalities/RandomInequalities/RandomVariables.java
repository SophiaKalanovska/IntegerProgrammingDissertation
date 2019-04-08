package Model.Inequalities.RandomInequalities;
import org.apache.commons.lang3.RandomStringUtils;


class RandomVariables {

    private static int lowerUpper;
    private int threshold ;
    private int currentSize;

    /**
     * RandomVariables object for random string generation
     * sets the initial string length to 2
     */
    public RandomVariables() {
        lowerUpper = 0;
        threshold = 338;
        currentSize = 2;
    }

    /**
     * Generate method generates a string if the threshold is higher
     * than the amount of generated strings so far, if not it increases the threshold
     * and the length of the string that must be generated
     */
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





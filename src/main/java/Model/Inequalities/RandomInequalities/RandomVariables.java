package Model.Inequalities.RandomInequalities;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;

public class RandomVariables {

    private static ArrayList<String> lowerUpper;
    private int threshold ;
    private int currentSize;

    public RandomVariables() {
        lowerUpper = new ArrayList<>();
        threshold = 26*13;
        currentSize = 2;
    }


    public String generate() {
        if (lowerUpper.size() <= threshold) {
            String random = RandomStringUtils.randomAlphabetic(currentSize);
//            while (lowerUpper.contains(random)) {
//                random = generate();
//            }
            lowerUpper.add(random);
            return random;
        } else {
            threshold = threshold*26;
            currentSize ++;
            return generate();
        }
    }
}





package model;

import java.awt.*;
import java.util.Random;

public class ColorGenerator {
    private Random rand;

    public ColorGenerator(){
        rand = new Random();
    }

    public Color createColor(){
        final float hue = rand.nextFloat();
        final float saturation = (rand.nextInt(2000) + 1000) / 10000f;
        final float luminance = 0.9f;
        return Color.getHSBColor(hue, saturation, luminance);
    }
}

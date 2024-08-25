package br.paulocalderan.samambaia;

import java.util.logging.Logger;

public class FernFlower {

    private static final Logger logger = Logger.getLogger(FernFlower.class.getName());

    public static final String RICH = "RICO";
    public static final String DEAD = "MORTO";

    public static String attemptToDraw(int hunterRadius, int hunterX, int hunterY, int flowerRadius, int flowerX, int flowerY) {
        double distanceBetweenCenters = calculateDistance(hunterX, hunterY, flowerX, flowerY);

        if (isHunterSuccessful(hunterRadius, distanceBetweenCenters, flowerRadius)) {
            return RICH;
        } else {
            return DEAD;
        }
    }

    private static double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private static boolean isHunterSuccessful(int hunterRadius, double distanceBetweenCenters, int flowerRadius) {
        return distanceBetweenCenters + flowerRadius <= hunterRadius;
    }

    public static void main(String[] args) {
        int hunterRadius = 5;
        int hunterX = 0;
        int hunterY = 0;
        int flowerRadius = 3;
        int flowerX = 1;
        int flowerY = 1;

        String result = attemptToDraw(hunterRadius, hunterX, hunterY, flowerRadius, flowerX, flowerY);
        logger.info(result);
    }
}

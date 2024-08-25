package br.paulocalderan.resistors;

import java.util.Scanner;
import java.util.logging.Logger;

import static br.paulocalderan.resistors.Constants.*;

public class Resistor {

    private static final Logger logger = Logger.getLogger(Resistor.class.getName());
    public static final String OHMS = "ohms";
    public static final String M = "M";
    public static final String K = "k";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            logger.info(WRITE_VALUE);
            input = scanner.nextLine().trim();

            if (input.isBlank()) {
                logger.severe(NOT_BLANK);
                continue;
            }

            if (!input.toLowerCase().contains(OHMS)) {
                logger.severe(INPUT_INVALID);
                continue;
            }

            try {
                String result = getResistorColors(input);
                logger.info(RESISTOR_RESULT + result);
                break;
            } catch (IllegalArgumentException e) {
                logger.severe(ERROR + e.getMessage());
            }
        }
    }

    private static String getResistorColors(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2 || !parts[1].equalsIgnoreCase(OHMS)) {
            throw new IllegalArgumentException(INVALID_FORMAT);
        }

        double ohmsValue = parseOhmsValue(parts[0]);
        int firstDigit;
        int secondDigit;
        int multiplier;

        if (ohmsValue < 10) {
            firstDigit = (int) ohmsValue;
            secondDigit = 0;
            multiplier = 0;
        } else if (ohmsValue < 100) {
            firstDigit = (int) (ohmsValue / 10);
            secondDigit = (int) (ohmsValue % 10);
            multiplier = 0;
        } else if (ohmsValue < 1000) {
            firstDigit = (int) (ohmsValue / 100);
            secondDigit = (int) ((ohmsValue % 100) / 10);
            multiplier = (int) (ohmsValue % 10);
        } else {
            String[] splitValue = String.valueOf(ohmsValue).split("\\.");
            firstDigit = Character.getNumericValue(splitValue[0].charAt(0));
            secondDigit = Character.getNumericValue(splitValue[0].charAt(1));
            multiplier = splitValue.length > 1 ? splitValue[1].length() : 0;
        }

        return Color.values()[firstDigit].name().toLowerCase() + " " +
                Color.values()[secondDigit].name().toLowerCase() + " " +
                Color.values()[multiplier].name().toLowerCase() + " " +
                Color.GOLD.name().toLowerCase();
    }


    private static double parseOhmsValue(String input) {
        if (input.endsWith(K)) {
            return Double.parseDouble(input.replace(K, "")) * 1000;
        } else if (input.endsWith(M)) {
            return Double.parseDouble(input.replace(M, "")) * 1_000_000;
        } else {
            return Double.parseDouble(input);
        }
    }
}

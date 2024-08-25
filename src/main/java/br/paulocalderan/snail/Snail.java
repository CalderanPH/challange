package br.paulocalderan.snail;

import java.util.ArrayList;
import java.util.List;

public class Snail {
    public static void main(String[] args) {
        Integer[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Integer[][] matrix2 = {
                {33, 2, 43, 14, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 0},
                {14, 2, 22, 21, 8},
                {13, 12, 1, 10, 9}
        };

        Integer[][] partialMatrix = {
                {1, 2, null},
                {null, 4, 6},
                {7, null, 9}
        };

        System.out.println("Matriz 3x3 em formato caracol:");
        System.out.println(snailSort(matrix1));

        System.out.println("\nMatriz 5x5 em formato caracol:");
        System.out.println(snailSort(matrix2));

        System.out.println("\nMatriz parcialmente preenchida em formato caracol:");
        System.out.println(snailSort(partialMatrix));
    }

    public static List<Integer> snailSort(Integer[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                if (matrix[top][i] != null) {
                    result.add(matrix[top][i]);
                }
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                if (matrix[i][right] != null) {
                    result.add(matrix[i][right]);
                }
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    if (matrix[bottom][i] != null) {
                        result.add(matrix[bottom][i]);
                    }
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    if (matrix[i][left] != null) {
                        result.add(matrix[i][left]);
                    }
                }
                left++;
            }
        }

        return result;
    }
}

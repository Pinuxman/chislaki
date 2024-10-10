package org.example;

public class Matrix {
    private double[][] matrix;
    private int rows;
    private int cols;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }
    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%10.5f", matrix[i][j]);
            }
            System.out.println();
        }
    }
    private void swapRows(int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }
    public boolean isInvertible() {
        gaussianElimination();
        for (int i = 0; i < rows; i++) {
            if (matrix[i][i] == 0) {
                return false;
            }
        }
        return true;
    }
    public void gaussianElimination() {
        for (int i = 0; i < rows; i++) {
            if (matrix[i][i] == 0) {
                for (int j = i + 1; j < rows; j++) {
                    if (matrix[j][i] != 0) {
                        swapRows(i, j);
                        break;
                    }
                }
            }
            for (int j = i + 1; j < rows; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = i; k < cols; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }
    }
    public void backSubstitution() {
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = cols - 1; k >= i; k--) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
            double divisor = matrix[i][i];
            for (int k = i; k < cols; k++) {
                matrix[i][k] /= divisor;
            }
        }
    }
    public Matrix inverse() {
        if (rows != cols) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }

        double[][] augmentedMatrix = new double[rows][cols * 2];

        // Создаем расширенную матрицу (исходная + единичная матрица)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
            }
            augmentedMatrix[i][i + cols] = 1.0;
        }

        Matrix augmented = new Matrix(augmentedMatrix);
        augmented.gaussianElimination();
        augmented.backSubstitution();

        double[][] inverseMatrix = new double[rows][cols];

        // Извлекаем обратную матрицу из расширенной матрицы
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                inverseMatrix[i][j] = augmentedMatrix[i][j + cols];
            }
        }

        return new Matrix(inverseMatrix);
    }
}

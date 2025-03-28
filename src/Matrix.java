public class Matrix {
    public static float[][] transposeMultiply(float[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        float[][] result = new float[cols][cols];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < cols; j++) {
                float[] columnI = getColumn(matrix, i);
                float[] columnJ = getColumn(matrix, j);
                result[i][j] = DiscreteMaths.sumXY(columnI, columnJ);
            }
        }
        return result;
    }

    public static float[] transposeMultiplyY(float[][] matrix, float[] y) {
        int cols = matrix[0].length;
        float[] result = new float[cols];

        for (int i = 0; i < cols; i++) {
            float[] column = getColumn(matrix, i);
            result[i] = DiscreteMaths.sumXY(column, y);
        }
        return result;
    }

    private static float[] getColumn(float[][] matrix, int col) {
        float[] column = new float[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][col];
        }
        return column;
    }

    // Inversión de matriz (eliminación gaussiana)
    public static float[][] invert(float[][] a) {
        int n = a.length;
        float[][] x = new float[n][n];
        float[][] b = new float[n][n];
        int[] index = new int[n];

        for (int i = 0; i < n; ++i) {
            b[i][i] = 1;
        }

        gaussian(a, index);

        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            x[n-1][i] = b[index[n-1]][i] / a[index[n-1]][n-1];
            for (int j = n-2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j+1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    private static void gaussian(float[][] a, int[] index) {
        int n = index.length;
        float[] c = new float[n];

        for (int i = 0; i < n; ++i) {
            index[i] = i;
        }

        for (int i = 0; i < n; ++i) {
            float c1 = 0;
            for (int j = 0; j < n; ++j) {
                float c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        int k = 0;
        for (int j = 0; j < n-1; ++j) {
            float pi1 = 0;
            for (int i = j; i < n; ++i) {
                float pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            int temp = index[j];
            index[j] = index[k];
            index[k] = temp;

            for (int i = j+1; i < n; ++i) {
                float pj = a[index[i]][j] / a[index[j]][j];
                a[index[i]][j] = pj;

                for (int l = j+1; l < n; ++l) {
                    a[index[i]][l] -= pj * a[index[j]][l];
                }
            }
        }
    }

    public static float[] matrixVectorMultiply(float[][] matrix, float[] vector) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        float[] result = new float[rows];

        for (int i = 0; i < rows; ++i) {
            float sum = 0;
            for (int j = 0; j < cols; ++j) {
                sum += matrix[i][j] * vector[j];
            }
            result[i] = sum;
        }
        return result;
    }
}
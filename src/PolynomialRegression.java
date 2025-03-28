public class PolynomialRegression {
    private final DataSet data;
    public float[] coefficients;

    public PolynomialRegression(DataSet data) {
        this.data = data;
    }

    public static float[] calculateCoefficients(DataSet data, int degree) {
        float[][] X = buildDesignMatrix(data.getX(), degree);
        float[][] XtX = Matrix.transposeMultiply(X);
        float[] XtY = Matrix.transposeMultiplyY(X, data.getY());
        float[][] XtXInv = Matrix.invert(XtX);
        return Matrix.matrixVectorMultiply(XtXInv, XtY);
    }

    private static float[][] buildDesignMatrix(float[] x, int degree) {
        float[][] matrix = new float[x.length][degree + 1];
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j <= degree; j++)
                matrix[i][j] = (float) Math.pow(x[i], j);
        return matrix;
    }

    public void calculate(int degree) {
        this.coefficients = calculateCoefficients(this.data, degree);
    }

    public float predict(float x) {
        if (this.coefficients == null) {
            throw new IllegalStateException("Polynomial regression model not calculated yet. Call 'calculate()' first.");
        }
        float prediction = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            prediction += this.coefficients[i] * Math.pow(x, i);
        }
        return prediction;
    }

    public float determination() {
        if (this.coefficients == null) {
            throw new IllegalStateException("Polynomial regression model not calculated yet. Call 'calculate()' first.");
        }
        float[] yActual = this.data.getY();
        float meanY = DiscreteMaths.mean(yActual);
        float ssTotal = DiscreteMaths.sumSquaredDifferences(yActual, meanY);
        float ssResidual = 0;
        for (int i = 0; i < yActual.length; i++) {
            ssResidual += Math.pow(yActual[i] - predict(this.data.getX()[i]), 2);
        }
        return 1 - (ssResidual / ssTotal);
    }
}
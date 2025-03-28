
public class SLR {
    public final DataSet data;
    public float beta0, beta1;

    public SLR(DataSet data) {
        this.data = data;
    }

    public static float[] calculateCoefficients(DataSet data) {
        int n = data.getX().length;
        float sumX = DiscreteMaths.sum(data.getX());
        float sumY = DiscreteMaths.sum(data.getY());
        float sumXY = DiscreteMaths.sumXY(data.getX(), data.getY());
        float sumX2 = DiscreteMaths.sumXY(data.getX(), data.getX());

        float beta1 = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        float beta0 = DiscreteMaths.mean(data.getY()) - beta1 * DiscreteMaths.mean(data.getX());

        return new float[]{beta0, beta1};
    }

    public void calculate() {
        float[] coeffs = calculateCoefficients(this.data);
        this.beta0 = coeffs[0];
        this.beta1 = coeffs[1];
    }

    public float predict(float x) {
        if (!Float.isNaN(this.beta0) && !Float.isNaN(this.beta1)) {
            return this.beta0 + this.beta1 * x;
        } else {
            throw new IllegalStateException("SLR model not calculated yet. Call 'calculate()' first.");
        }
    }

    public float correlation() {
        float meanX = DiscreteMaths.mean(data.getX());
        float meanY = DiscreteMaths.mean(data.getY());
        float cov = DiscreteMaths.covariance(data.getX(), data.getY());
        float stdDevX = (float)Math.sqrt(DiscreteMaths.sumSquaredDifferences(data.getX(), meanX));
        float stdDevY = (float)Math.sqrt(DiscreteMaths.sumSquaredDifferences(data.getY(), meanY));
        return cov / (stdDevX * stdDevY);
    }

    public float determination() {
        if (Float.isNaN(this.beta0) || Float.isNaN(this.beta1)) {
            throw new IllegalStateException("SLR model not calculated yet. Call 'calculate()' first.");
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

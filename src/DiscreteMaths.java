public class DiscreteMaths {
    public static float sum(float[] data) {
        float total = 0;
        for (float num : data) total += num;
        return total;
    }

    public static float sumXY(float[] x, float[] y) {
        if (x.length != y.length)
            throw new IllegalArgumentException("Arrays must have same length");
        float total = 0;
        for (int i = 0; i < x.length; i++) total += x[i] * y[i];
        return total;
    }

    public static float mean(float[] data) {
        return sum(data) / data.length;
    }

    public static float sumSquaredDifferences(float[] data, float mean) {
        float sum = 0;
        for (float num : data) sum += Math.pow(num - mean, 2);
        return sum;
    }

    public static float covariance(float[] x, float[] y) {
        if (x.length != y.length)
            throw new IllegalArgumentException("Arrays must have same length");
        float meanX = mean(x), meanY = mean(y);
        float sum = 0;
        for (int i = 0; i < x.length; i++)
            sum += (x[i] - meanX) * (y[i] - meanY);
        return sum / (x.length - 1); // Sample covariance
    }
}
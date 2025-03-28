import java.util.Arrays;

public class DataSet {
    private float[] x;
    private float[] y;

    public DataSet() {
        this.x = new float[]{23, 26, 30, 34, 43, 48, 52, 57, 58};
        this.y = new float[]{651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};
    }


    public DataSet(float[] x, float[] y) {
        this.x = x;
        this.y = y;
    }

    public float[] getX() { return x.clone(); }
    public float[] getY() { return y.clone(); }


    public DataSet split(double splitRatio) {
        int splitIndex = (int) (x.length * splitRatio);
        return new DataSet(
                Arrays.copyOfRange(x, 0, splitIndex),
                Arrays.copyOfRange(y, 0, splitIndex)
        );
    }
}
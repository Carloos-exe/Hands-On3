import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DataSet originalData = new DataSet();

        printResults(originalData);

        System.out.println("\n=== Split ===");
        DataSet splitData = originalData.split(0.7);
        printResults(splitData);
    }

    private static void printResults(DataSet data) {
        SLR slr = new SLR(data);
        slr.calculate();

        System.out.println("\n--- Regresión Lineal ---");
        System.out.println("Beta (b0, b1): " + Arrays.toString(SLR.calculateCoefficients(data)));
        System.out.println("Correlación: " + slr.correlation());
        System.out.println("Determinación R²: " + slr.determination());
        System.out.println("Predicción para x = 35: " + slr.predict(35));

        System.out.println("\n--- Regresión Polinomial Grado 2 ---");
        PolynomialRegression poly2 = new PolynomialRegression(data);
        poly2.calculate(2);
        System.out.println("Coeficientes: " + Arrays.toString(poly2.coefficients));
        System.out.println("Predicción para x = 35: " + poly2.predict(35));
        System.out.println("Determinación R²: " + poly2.determination());

        System.out.println("\n--- Regresión Polinomial Grado 3 ---");
        PolynomialRegression poly3 = new PolynomialRegression(data);
        poly3.calculate(3);
        System.out.println("Coeficientes: " + Arrays.toString(poly3.coefficients));
        System.out.println("Predicción para x = 35: " + poly3.predict(35));
        System.out.println("Determinación R²: " + poly3.determination());
    }
}
package task7_0;

public class Matrix {

    public int width;
    public double[][] components;
    public int height;
    public Matrix(double[][] components) {
        this.components = components;
        this.height = components.length;
        this.width = components[0].length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] component : components) {
            for (double v : component) {
                sb.append(v).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

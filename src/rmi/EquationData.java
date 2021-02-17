package rmi;

import java.io.Serializable;

public class EquationData implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final double x, secondArgument;
    private double y;
    
    public EquationData(double x, double secondArgument) {
        this.x = x;
        this.secondArgument = secondArgument;
    }

    public double getX() {
        return x;
    }

    public double getSecondArgument() {
        return secondArgument;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "EquationData{" + "x=" + x + ", secondArgument=" + secondArgument + ", y=" + y + '}';
    }
}

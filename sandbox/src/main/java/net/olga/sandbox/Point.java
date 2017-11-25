package net.olga.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point other) {
        double diffX = this.x - other.x;
        double diffY = this.y - other.y;
        return Math.sqrt(diffX*diffX+diffY*diffY);
    }
}

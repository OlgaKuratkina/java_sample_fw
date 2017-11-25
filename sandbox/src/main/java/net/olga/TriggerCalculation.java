package net.olga;

public class TriggerCalculation {

    public static void main(String[] Args) {
        Point p1 = new Point(2, 4);
        Point p2 = new Point(5, 7);
        System.out.println("Calling static function");
        System.out.println(distance(p1, p2));

        System.out.println("Calling method");
        System.out.println(p1.distance(p2));
    }

    public static double distance(Point p1, Point p2) {
        double diffX = p1.x - p2.x;
        double diffY = p1.y - p2.y;
        return Math.sqrt(diffX*diffX + diffY*diffY);
    }
}

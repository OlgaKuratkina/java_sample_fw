package net.olga;

public class MyFirstProg {

	public static void main(String[] Args) {
		hello("Olga");
		hello("World");
        System.out.println(area(6));
        System.out.println(area(5,7));
    }

    private static void hello(String name) {
        System.out.println("Hello, " + name + "!");
    }
    private static double area(double a, double b) {
	    return a * b;
    }
    private static double area(double a) {
        return a * a;
    }
}
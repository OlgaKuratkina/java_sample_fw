package net.olga.sanbox;

import net.olga.sandbox.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p = new Point(90, 6);
        Point s = new Point(10, 5);
        System.out.println(p.distance(s));
        assert Math.round(p.distance(s)) == 80;
        Assert.assertEquals(Math.round(p.distance(s)), 80);
    }

    @Test
    public void testDistanceSmall() {
        Point p = new Point(8, 7);
        Point s = new Point(10, 5);
        System.out.println(p.distance(s));
        Assert.assertEquals(p.distance(s), 2.8284271247461903);
    }

    @Test
    public void testDistanceZero() {
        Point p = new Point(9, 7);
        Point s = new Point(9, 7);
        System.out.println(p.distance(s));
        Assert.assertEquals(p.distance(s), 0.0);
    }
}

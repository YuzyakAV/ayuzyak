package ru.job4j;

/**
 * Vector point class.
 * @author ayuzyak.
 * @version 1.
 * @since 12.12.2016.
*/
public class Point {
    /**
     * Point x.
     */
    public double x;
    /**
     * Point y.
     */
    public double y;

    /**
     * Point constructor.
     * @param x - x axis coordinate
     * @param y - y axis coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance to the point.
     * @param point - provided point
     * @return distance
     */
    public double distanceTo(Point point) {
        return Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y - this.y, 2));
    }
}
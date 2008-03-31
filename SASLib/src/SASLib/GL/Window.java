// Copyright © 2005 William Bogg Cecil. All rights reserved. Use is
// subject to license terms.
// 
// This program is free software; you can redistribute it and/or modify
// it under the terms of the Lesser GNU General Public License as
// published by the Free Software Foundation; either version 2 of the
// License, or (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
// USA

// This code was originally written and compiled on the personal computer
// owned and operated by William Bogg Cecil and no other party may claim 
// ownership of the original code without written consent of William Bogg 
// Cecil. 
package SASLib.GL;

import SASLib.Geom.CollisionHandler;
import SASLib.Geom.Line;
import SASLib.Geom.Point;
import SASLib.Geom.Quadrilateral;
import java.awt.geom.Rectangle2D;

/**
 * Holds the max/min x and y and a <code>Rectangle2D</code> to render something within.
 * @author Wil Cecil
 */
public class Window {

    double minX, minY, maxX, maxY;
    double xScale, yScale;
    Rectangle2D bounds;
    Point center;

    /**
     * This is the Default constructor
     * @param minX in window lower X bound
     * @param minY in window lower Y bound
     * @param maxX in window upper X bound
     * @param maxY in window upper Y bound
     * @param bounds this is the rectagle to draw within, in normal rectangular cordinates
     */
    public Window(double minX, double minY, double maxX, double maxY, Rectangle2D bounds) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.bounds = bounds;

        xScale = (bounds.getWidth()) / (Math.abs(maxX) + Math.abs(minX));
        yScale = (bounds.getHeight()) / (Math.abs(maxY) + Math.abs(minY));

        center = new Point(Math.abs(minX) * xScale + bounds.getX(), Math.abs(minY) * yScale + bounds.getY());

        if (SASLib.Header._DEBUG) {
            System.out.println("Window Info");
            System.out.println("\tx : " + new Point(minX, maxX).toStringShort() +
                    "| " + bounds.getWidth() +
                    " / " + Math.abs(maxX) + "+" + Math.abs(minX));
            System.out.println("\ty : " + new Point(minY, maxY).toStringShort() +
                    "| " + bounds.getHeight() +
                    " / " + Math.abs(maxY) + "+" + Math.abs(minY));
            System.out.println("\t" + xScale + " " + yScale + " " + center.toStringShort());
        }
    }

    /**
     * Returns the Origin point in standard grid cordinates
     * @return Point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the bounding box in regular cords
     * @return Rectangle2D
     */
    public Rectangle2D getBounds() {
        return bounds;
    }

    /**
     * Upper X bound
     * @return double
     */
    public double getMaxX() {
        return maxX;
    }

    
    /**
     * Upper Y bound
     * @return double
     */
    public double getMaxY() {
        return maxY;
    }

    
    /**
     * Lower X bound
     * @return double
     */
    public double getMinX() {
        return minX;
    }

    
    /**
     * Lower Y bound
     * @return double
     */
    public double getMinY() {
        return minY;
    }

    /**
     * this finds the point in terms of the outside world from x, y in scale w/ the window.
     * @param x
     * @param y
     * @return point in terms of the outside world from x, y in scale w/ the window.
     */
    public Point calculate(double x, double y) {
        Point p = new Point(x, y);
        p.setX(p.getX() * xScale);
        p.setY(p.getY() * yScale);
        p = p.translate(center);
        return p;
    }

    /**
     * this finds the point in terms of the outside world from x, y in scale w/ the window.
     * @param p
     * @return Point
     */
    public Point calculate(Point p) {
        return calculate(p.getX(), p.getY());
    }

    /**
     * checks if line is valid, if not returns the colision with the edge of the box or if both out returns null
     * @param p1
     * @param p2
     * @return Line
     */
    public Line valid(Point p1, Point p2) {
        boolean p1i = bounds.contains(p1);
        boolean p2i = bounds.contains(p2);
        Line l = new Line(p1, p2);

        if (p1i && p2i) {
            //both in
            return l;
        } else if (p1i ^ p2i) {
            //point 1 xor 2 in

            //Trim line to within the window
            //Rectangle2D r = bounds.createIntersection(l.getBounds());
            //we rotate by .1 to do vert line checking, quick clean and easy fix
            Quadrilateral q = new Quadrilateral(bounds, .1);

            //they are close enough for at least one edge to be touching.
            //lets check line colisions
            Point[] points = q.getPoints();

            //this is the line that point two is on the wrong side of.
            Line overLine;

            //Point of collison
            Point collide;

            //o(n^2) checking, not to shaby
            for (int i = 0; i < points.length; i++) {
                //Look for the point where the two lines colide, this is our new pnt2
                overLine = new Line(points[i], points[(i + 1) % points.length]);
                collide = CollisionHandler.getCollision(overLine, l);
                if (collide != null) {
                    //Switch the correct point
                    if (p1i) {
                        //point one is in, so fix two
                        l.setPnt2(collide);
                    } else {
                        //point two is in, so fix one
                        l.setPnt1(collide);
                    }
                    break;
                }
            }

            //receclare points
            //l = new Line(new Point(r.getX(), r.getY()), new Point(r.getX()+r.getWidth(), r.getY()+r.getHeight()) );
            return l;
        }

        //none in
        return null;
    }
}

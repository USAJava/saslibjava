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
/*
 * Point.java
 * 
 * Created on Jan 7, 2008, 2:02:28 PM
 */

package SASLib.Geom;

import java.awt.geom.Point2D;

/**
 * This is a double precision Point2D
 * @author Wil Cecil
 */
public class Point extends Point2D implements Cloneable{
    public double y;
    public double x;

    public Point(double x, double y) {
        this.y = y;
        this.x = x;
    }

    public Point() {
        this.y = 0;
        this.x = 0;
    }
    
    /**
     * Creates a Clone of this point, Translates that point 
     * by the parameter point then returns the new point.
     * @param trans (amount to move (x,y))
     * @return Point
     */
    public Point translate(Point trans){
        Point p = (Point) this.clone();
        p.x+=trans.getX();
        p.y+=trans.getY();
        return p;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    /**
     * Returns (int) Math.round(x);
     * @return int
     */
    public int getBigX() {
        return (int) Math.round(x);
    }

    /**
     * Returns (int) Math.round(y);
     * @return int
     */
    public int getBigY() {
        return (int) Math.round(y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * throurough description of point
     * @return super.toString()+" ["+x+", "+y+"] hash: "+hashCode()
     */
    @Override
    public String toString() {
        return super.toString()+" ["+x+", "+y+"] hash: "+hashCode();
    }
    
    /**
     * brief description of point
     * @return " ["+x+", "+y+"] "
     */
    public String toStringShort() {
        return " ["+((int)(x*10000)/10000.0)+", "+((int)(y*10000)/10000.0)+"] ";
    }

    /**
     * Returns this point as a vector
     * @return Vector
     */
    public Vector asVector(){
        return new Vector(x,y);
    }
}

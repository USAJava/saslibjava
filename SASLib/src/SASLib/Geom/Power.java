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
package SASLib.Geom;

/**
 * This is an implementation of SimpleFunction for the form<br>
 * y = ((xScale * x - xShift)^power + yShift) / yScale
 * @author Wil Cecil
 */
public class Power implements SimpleFunction{
    double xShift, xScale, yShift, yScale, power;
    
    
    /**
     * y = ((xScale * x - xShift)^power + yShift) / yScale
     * @param power 
     * @param xShift
     * @param xScale
     * @param yShift
     * @param yScale
     */
    public Power(double power, double xShift, double xScale, double yShift, double yScale){
        this.power = power;
        this.xScale = xScale;
        this.xShift = xShift;
        this.yScale = yScale;
        this.yShift = yShift;
    }
    
    public Double evaluate(double dependent) {
        return (Math.pow(xScale * dependent - xShift , power) + yShift) / yScale;
    }

    /**
     * Always Returns False
     * @param f
     * @return
     */
    public boolean isSimplifiable(SimpleFunction f) {
        return false;
    }

    /**
     * Always Throws
     * @param f
     * @return never.
     * @throws java.lang.UnsupportedOperationException
     */
    public SimpleFunction simplify(SimpleFunction f) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

}

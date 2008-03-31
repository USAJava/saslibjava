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
 * This function handles a single dependent variable.
 * 
 * @author Wil Cecil
 */
public interface SimpleFunction extends Function{
    /**
     * this evaluates the function at the dependent variable
     * @param dependent
     * @return Double as a result of this function
     */
    public Double evaluate(double dependent);
    
    /**
     * Determains if this function and another are simplifiable together. 
     * @param f the second function
     * @return <tt>true</tt> if this function and another are able to be joined
     *      to create a simpler function.
     */
    public boolean isSimplifiable(SimpleFunction f);
    
    /**
     * Combines two funtions into a leaner and more efficient function.
     * @param f
     * @return SimpleFunction the result of this call.
     * @throws java.lang.UnsupportedOperationException when the two functions 
     *      are not able to be simplified, this behavior is not guarenteed. The 
     *      Implementation may simply return null. Developers Preference.
     */
    public SimpleFunction simplify(SimpleFunction f) throws UnsupportedOperationException;
    
}

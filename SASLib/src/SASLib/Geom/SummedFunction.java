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

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This Function adds the sum of multiple functions.<br>
 * ie:<br>
 * f(x) = g(x) + h(x) + ...
 * @author Wil Cecil
 */
public class SummedFunction implements SimpleFunction {

    List<SimpleFunction> funcs;

    /**
     * Create summation function from list of SimpleFunction
     * @param functs
     */
    public SummedFunction(SimpleFunction... functs) {
        funcs = Arrays.asList(functs);
    }

    /**
     * Create summation function from List of SimpleFunction
     * @param functs
     */
    private SummedFunction(List<SimpleFunction> funcs) {
        //Create as Linked List
        funcs = new LinkedList<SimpleFunction>();
        
        //Add all to the list
        funcs.addAll(funcs);
    }

    /**
     * Adds a function to the list to be summed
     * @param funct
     * @return <tt>true</tt> if this function changed as a result of the call.
     */
    public boolean addFunction(SimpleFunction funct) {
        return funcs.add(funct);
    }
    
    /**
     * Adds several functions to the list to be summed
     * @param functs 
     * @return <tt>true</tt> if this function changed as a result of the call.
     */
    public boolean addFunction(SimpleFunction ... functs) {
        return funcs.addAll(Arrays.asList(functs));
    }

    /**
     * finds the value of all sub functions and sums them, then returns the result.
     * @param dependent
     * @return Double Equal to g(x) + h(x) + ...
     */
    public Double evaluate(double dependent) {
        //Declare variable
        double result = 0.0;

        //create iterator
        Iterator<SimpleFunction> iter = funcs.iterator();

        //iterate through all functions and evalutate each at dependent and add
        //result to sum variable
        while (iter.hasNext()) {
            result += iter.next().evaluate(dependent);
        }

        //return the result
        return result;
    }

    /**
     * Returns the List of functions within the summation
     * @return List of SimpleFunction
     */
    public List<SimpleFunction> getFunctions() {
        return funcs;
    }
    
    

    /**
     * Will combine this function with any other summedfucntion else returns false.
     * @param f
     * @return <tt>true</tt> if f is an instance of SummedFunction.
     */
    public boolean isSimplifiable(SimpleFunction f) {
        if(f instanceof SummedFunction){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Joins two summed functions as one
     * @param f
     * @return the joined summed functions.
     * @throws java.lang.UnsupportedOperationException
     */
    public SimpleFunction simplify(SimpleFunction f) throws UnsupportedOperationException {
        if(f instanceof SummedFunction){
            SummedFunction simple = new SummedFunction(funcs);
            
            simple.addFunction(f);
            
            return simple;
        }else{
            //if not simplifiable throw exception
            throw new UnsupportedOperationException(
                "Not Supported for this class combination: "+
                getClass().toString()+" and "+f.getClass().toString());
        }
    }
}

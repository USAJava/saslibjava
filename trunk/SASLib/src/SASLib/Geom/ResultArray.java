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
 * Simple Double Precision Static Size Implementation
 * @author Wil Cecil
 */
public class ResultArray implements Result{
    double[] res;
    
    /**
     * Creates the results from a list of results to use
     * @param results
     */
    public ResultArray(Double ... results){
        //make primitive array
        res = new double[results.length];
        
        //copy values to primitive array
        for(int i = 0; i<results.length; i++){
            res[i]=results[i];
        }
    }
    
    /**
     * Returns the Count of the number of results, for a one to one function 
     * this will be one, for a parametric it will be two.
     * @return int Count of results
     */
    public int numberOfValues(){
        return res.length;
    }
    
    /**
     * gets the (num)th result.
     * @param num
     * @return double
     * @throws IndexOutOfBoundsException this occurs when num is less than zero 
     * or greater than numberOfValues()-1
     */
    public double getResult(int num) throws IndexOutOfBoundsException{
        return res[num];
    }

}

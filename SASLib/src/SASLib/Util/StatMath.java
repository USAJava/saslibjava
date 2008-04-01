package SASLib.Util;
// This is the TerminalService class, its purpose is to get input in 
// a command line enviroment.
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

/**
 * Contains Simple Statistic Math Functions,
 * For now just combinations.
 * @author Wil Cecil
 */
public class StatMath {

    /**
     * <p>
     * Calculates the nCr or (n,r) = n! / ( r! ( n - r )! ) such that ! denotes 
     * factorial. 
     * 
     * <p>
     * n ( n - 1 ) ( n - 2 ) . . . ( n - r + 1 )<br>
     * -----------------------------------------<br>
     * r!
     * 
     * @param n
     * @param r
     * @return nCr
     */
    public static double combination(int n, int r){
        double value = 1;
        for (int k = 0; k < r; k++){
            value *= (double)( n - k ) / ( r - k );
            
            //System.out.println( ( n - k ) + " / " + ( r - k ) +" x ");
        }
        return value;
    }
    
//Tester Method    
//    public static void main(String [] args){
//        System.out.println(combination(3,0));
//        System.out.println(combination(3,1));
//        System.out.println(combination(3,2));
//        System.out.println(combination(3,3));
//        System.out.println(combination(3,4));
//    }
}

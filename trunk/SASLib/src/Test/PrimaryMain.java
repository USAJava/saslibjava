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

// Code maintained by SAS. Students Against Segregation.  
/*
 * PrimaryMain.java
 *
 * Created on July 9, 2007, 8:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Test;

import java.util.Arrays;

/**
 * Primary Launching Class.
 * 
 * @author Wil Cecil
 */
public class PrimaryMain {

    public static void main(String[] args) {
        if (args.length == 0) {
            menu(args);
        } else {
            if(args[0].startsWith("-h")){
                help();
            }else if(args[0].startsWith("-a")){
                ASCIITest.main(args);
            }else{
                //Help...
                help();
            }
        }
        System.exit(0);
    }

    /** 
     * Command Line Menu
     */
    public static void menu(String[] args) {
        System.out.println("Welcome to SASLib debug and control");
        System.out.println("Please select your program");
        System.out.println("1> ASCII DEMO");
        System.out.println("2> ASCII FROM FILE");
        //
        //MORE HERE AS IT IS DONE
        //
        System.out.println("End of List");
        System.out.println();
        System.out.println("Make Your Choice : ");
        //Get user input
        String choice = SASLib.Util.TerminalService.readString();

        if (choice.equals("1")) {
            ASCIITest.menu();
        }
        
        if (choice.equals("2")) {
            ASCIIFromFile.main(args);
        }
    }
    
    /**
     * Displays Help for Command Line Options
     */
    public static void help(){
        System.out.println("SASLib Command Line Help");
        System.out.println("parameter one must be one of the following...");
        System.out.println("h - help, this message");
        System.out.println("a - ascii, this activates the PIC -> ASCII Converter");
        System.out.println("if you want to see information on any of the options, \n" +
                "   such as PIC -> ASCII, use the command line options.");
        System.out.println("-a -h");
        System.out.println("Try it out!");
        
        //System.out.println("Command     | Action . . .");
        
    }
}
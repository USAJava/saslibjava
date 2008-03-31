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
 * ASCIITest.java
 *
 * Created on July 9, 2007, 7:57 PM
 * Modified on October 16, 2007 2:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Test;

/**
 * Ascii texting test main.
 * 
 * @author Wil Cecil
 */
public class ASCIITest {

    public static void main(String[] args) {
        if (args.length == 0) {
            menu();
        } else {
            run(args);
        }
    }

    /**
     * Parse Command Line Options
     * @param args 
     */
    public static void run(String[] args) {
        String f = "";
        String fout = "";
        boolean red = true;
        boolean green = true;
        boolean blue = true;
        boolean invert = false;
        int sHeight = 0;
        int sWidth = 0;
        for (int i = 1; i < args.length; i++) {
            //System.out.println("Command : "+args[i]+"|");
            args[i] = args[i].trim().toLowerCase();

            if (args[i].indexOf("-d") == 0) {
                //turn on debug
                SASLib.Header._DEBUG = true;
            } else if (args[i].indexOf("-fi") == 0) {
                //in file
                f = args[i].substring(3).trim();
            } else if (args[i].indexOf("-fo") == 0) {
                //out file
                fout = args[i].substring(3).trim();
            } else if (args[i].indexOf("-null") == 0) {
                //Do nothing
            } else if (args[i].indexOf("-h") == 0) {
                help();
                System.exit(0);
            } else {
                System.out.println("Unknown Command : " + args[i]);
            }
        }
        
        //check to see if in out files declaired...
        if(f.trim().equals("")){
            System.out.println("ERROR : In File not declaired..");
            
            System.exit(SASLib.Header.NO_IN_FILE);
        }
        if(fout.trim().equals("")){
            System.out.println("ERROR : Out File not declaired..");
            
            System.exit(SASLib.Header.NO_OUT_FILE);
        }
         
    }

    /**
     * Comand Line Menu
     */
    public static void menu() {
        //Delcare variables
        boolean red = false;
        boolean green = false;
        boolean blue = false;
        boolean invert = false;
        int width = 0;
        int height = 0;
        int sHeight = 0;
        int sWidth = 0;
        int gray = 0;
        int sampling = 0;

        boolean confirmed = false;

        //Get Data
        while (!confirmed) {
            System.out.println("Select Properties...");
            System.out.println("IMAGE PROPERTIES");
            System.out.println("Image Width");
            width = SASLib.Util.TerminalService.readInt();

            System.out.println("Image Height");
            height = SASLib.Util.TerminalService.readInt();

            System.out.println("\nRENDERING PROPERTIES");

            System.out.println("Red T/F");
            if (SASLib.Util.TerminalService.readString().trim().substring(0, 1).equalsIgnoreCase("t")) {
                red = true;
            }

            System.out.println("Green T/F");
            if (SASLib.Util.TerminalService.readString().trim().substring(0, 1).equalsIgnoreCase("t")) {
                green = true;
            }

            System.out.println("Blue T/F");
            if (SASLib.Util.TerminalService.readString().trim().substring(0, 1).equalsIgnoreCase("t")) {
                blue = true;
            }

            System.out.println("Invert T/F");
            if (SASLib.Util.TerminalService.readString().trim().substring(0, 1).equalsIgnoreCase("t")) {
                invert = true;
            }

            System.out.println("Grayscale Method");
            System.out.println("0> AVG");
            System.out.println("1> MAX");
            gray = SASLib.Util.TerminalService.readInt();

            System.out.println("Sampling Method");
            System.out.println("0> AVG");
            System.out.println("1> MAX");
            sampling = SASLib.Util.TerminalService.readInt();

            System.out.println("Sample Width");
            sWidth = SASLib.Util.TerminalService.readInt();

            System.out.println("Sample Height");
            sHeight = SASLib.Util.TerminalService.readInt();

            //Confirm input
            System.out.println("Is the following correct?");
            {
                System.out.println("IMAGE PROPERTIES");
                System.out.println("Image Width " + width);
                System.out.println("Image Height " + height);
                System.out.println("\nRENDERING PROPERTIES");
                System.out.println("Red " + red);
                System.out.println("Green " + green);
                System.out.println("Blue " + blue);
                System.out.println("Invert " + invert);
                System.out.println("Grayscale Method " + gray);
                System.out.println("Sampling Method " + sampling);
                System.out.println("Sample Width " + sWidth);
                System.out.println("Sample Height " + sHeight);
            }
            System.out.println("Correct T/F");
            if (SASLib.Util.TerminalService.readString().trim().substring(0, 1).equalsIgnoreCase("t")) {
                confirmed = true;
            }
        }
        System.out.println("Generating Test Image");
        int[][][] img = SASLib.Util.ASCII.testImage(width, height);

        System.out.println("Running ASCII Render");
        SASLib.Util.DebugTimer.start();
        System.out.println(SASLib.Util.ASCII.asciiRender(img, invert, red, green, blue, sWidth, sHeight, sampling, gray));
        SASLib.Util.DebugTimer.stop();
    }

    /**
     * Help Message Display
     */
    public static void help() {
        System.out.println("This is SASLib PIC -> ASCII Converter");
        System.out.println("Defualt Converter is a 3x2:1 Sample to Character Converter");
        System.out.println("     this can be turned off with -slob a 1x1:1 converter.");
        System.out.println(" ");
        System.out.println("Command | Action [Default]");
        System.out.println("-d      | Debug Mode");
        System.out.println("-fiAAA  | Specify Input Image [none]");
        System.out.println("-foAAA  | Specify Output Image [none]");
        System.out.println("-r[1|0] | Use Red 1 = Yes, 0 = No (Default 1)");
        System.out.println("-g[1|0] | Use Green 1 = Yes, 0 = No (Default 1)");
        System.out.println("-b[1|0] | Use Blue 1 = Yes, 0 = No (Default 1)");
        System.out.println("-i[1|0] | Invert Image 1 = Yes, 0 = No (Default 0)");
        System.out.println("-x####  | Horizontal Sample Size in Pixels");
        System.out.println("-y####  | Vertical Sample Size in Pixels");
        System.out.println("-slob   | Horible 1x1:1 Sample to Character Converter");
        //  //  //  //  //  //  //  //  //  //  //
        System.out.println("-null   | Does nothing");
        System.out.println("-h      | Display this Message");

        //END RUN
        return;
    }
}
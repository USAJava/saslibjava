/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import SASLib.Util.ASCII;
import SASLib.Util.Grid2d;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Loads an image file and rerenders with ASCII characters
 * @author Wil Cecil
 */
public class ASCIIFromFile {

    public static void main(String[] args) {
        if (args.length != 0) {
            System.out.println("There are no command line arguments");
        }
        
        //Delcare variables
        String fin;
        BufferedImage img = null;
        boolean invert = false;
        int sHeight = 0;
        int sWidth = 0;
        int sampling = 0;

        boolean confirmed = false;

        
        //READ IN WHICH FILE TO CONVERT
        while (img == null) {
            System.out.println("Image to convert?");
            fin = SASLib.Util.TerminalService.readString();
            File f = new File(fin);
            if (f.canRead()) {
                try {
                    img = ImageIO.read(f);
                } catch (IOException ex) {
                    System.out.println("Malformed Input");
                    img = null;
                }
            } else {
                System.out.println("Malformed Input");
                img = null;
            }
        }
        
        //READ IN CONVERSION INFORMATION
        while (!confirmed) {
            System.out.println("\nRENDERING PROPERTIES");

            System.out.println("Invert T/F");
            if (SASLib.Util.TerminalService.readString().trim().substring(0, 1).equalsIgnoreCase("t")) {
                invert = true;
            }

            System.out.println("Sampling Method");
            System.out.println("0> AVG");
            System.out.println("1> MAX");
            sampling = SASLib.Util.TerminalService.readInt();

            System.out.println("Sample 3 x 7 is pretty good.");
            
            System.out.println("Sample Width");
            sWidth = SASLib.Util.TerminalService.readInt();

            System.out.println("Sample Height");
            sHeight = SASLib.Util.TerminalService.readInt();

            //Confirm input
            System.out.println("Is the following correct?");
            {
                System.out.println("\nRENDERING PROPERTIES");
                System.out.println("Invert " + invert);
                System.out.println("Sampling Method " + sampling);
                System.out.println("Sample Width " + sWidth);
                System.out.println("Sample Height " + sHeight);
            }
            System.out.println("Correct T/F");
            if (SASLib.Util.TerminalService.readString().trim().substring(0, 1).equalsIgnoreCase("t")) {
                confirmed = true;
            }
        }
        
        //LETS READ IN IMG TO A GRID
        Grid2d grid = Grid2d.convert(img);
        
        System.out.println("RENDERING IN ASCII");
        
        //LETS DO THE CONVERSION
        String s = ASCII.asciiRender(grid.getGridInt(), invert, sWidth, sHeight, sampling);
        
        System.out.println(s);

    }
}

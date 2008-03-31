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

import SASLib.Geom.Function;
import SASLib.Geom.Line;
import SASLib.Geom.MultiresultFunction;
import SASLib.Geom.Point;
import SASLib.Geom.Result;
import SASLib.Geom.SimpleFunction;
import SASLib.Header;
import javax.media.opengl.GL;

/**
 *
 * @author Wil Cecil
 */
public class FunctionRender {

    /**
     * Renders the function from (start, stop) at every interval step in the 
     * openGL object gl w/ in a Window.
     * @param gl
     * @param x 
     * @param y 
     * @param zoom 
     * @param f
     * @param start
     * @param stop
     * @param step
     * @param w 
     */
    public static synchronized void render(GL gl, double x, double y, double zoom, Function f, double start, double stop, double step, Window w) {

        if (f instanceof SimpleFunction) {
            render(gl, x, y, zoom, (SimpleFunction) f, start, stop, step, w);
            return;
        } else if (f instanceof MultiresultFunction) {
            render(gl, x, y, zoom, (MultiresultFunction) f, start, stop, step, w);
        } else {
            throw new IllegalArgumentException("Render Failed for class " +
                    f.getClass() + ". Class does not implement known renderable type.");
        }

    //for(;start<=stop;start+=step){
    //    Object result = f.evaluate(start);
    //    
    //}
    }

    /**
     * Renders the function from (start, stop) at every interval step in the 
     * openGL object gl w/ in a Window.
     * @param gl
     * @param x 
     * @param y 
     * @param zoom 
     * @param f
     * @param start
     * @param stop
     * @param step
     * @param w 
     */
    public static synchronized void render(GL gl, double x, double y, double zoom, SimpleFunction f, double start, double stop, double step, Window w) {
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Move the "drawing cursor" to another position
        gl.glTranslated(-x, -y, -zoom);

        // line loop
        gl.glBegin(GL.GL_LINES);

        Point result;

        //Calc Initial Value
        Point lastPoint = new Point(start, f.evaluate(start));
        lastPoint = w.calculate(lastPoint);
        start += step;

        //this is the visable line to render
        Line visable;
        //calculate values
        for (; start <= stop; start += step) {
            //calc next point
            result = new Point(start, f.evaluate(start));

            //Debug Function Information
            if (Header._DEBUG) {
                System.out.print(result.toStringShort() + "_");
            }

            //Scale to window
            result = w.calculate(result);

            //Debug Windowed Information
            if (Header._DEBUG) {
                System.out.print(result.toStringShort() + " ");
            }

            //Make Line 
            visable = w.valid(result, lastPoint);

            //Update Last Point
            lastPoint = result;

            if (visable != null) {
                //draw line, if there at all
                gl.glVertex3d(visable.getPnt1().getX(), visable.getPnt1().getY(), 0);
                gl.glVertex3d(visable.getPnt2().getX(), visable.getPnt2().getY(), 0);
            }
        }

        if (Header._DEBUG) {
            System.out.println();
        }
        // Done Drawing The curve
        gl.glEnd();

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    /**
     * Renders the function from (start, stop) at every interval step in the 
     * openGL object gl w/ in a Window.
     * @param gl
     * @param x 
     * @param y 
     * @param zoom 
     * @param f
     * @param start
     * @param stop
     * @param step
     * @param w 
     */
    public static synchronized void render(GL gl, double x, double y, double zoom, MultiresultFunction f, double start, double stop, double step, Window w) {
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Move the "drawing cursor" to another position
        gl.glTranslated(-x, -y, -zoom);

        // line loop
        gl.glBegin(GL.GL_LINES);

        Result result = f.evaluate(start);

        Point resultPoint;


        //Calc Initial Value
        Point lastPoint = null;

        //this is the visable line to render
        Line visable;
        //calculate values
        for (; start <= stop; start += step) {
            //calc next point
            result = f.evaluate(start);

            //Check if has multiple resutls
            if (result.numberOfValues() > 1) {
                resultPoint = new Point(result.getResult(0), result.getResult(1));
            } else {
                resultPoint = new Point(start, result.getResult(0));
            }

            //Debug Function Information
            if (Header._DEBUG) {
                System.out.print(resultPoint.toStringShort() + "_");
            }

            //Scale to window
            resultPoint = w.calculate(resultPoint);

            //Debug Windowed Information
            if (Header._DEBUG) {
                System.out.print(resultPoint.toStringShort() + " ");
            }

            //check if first run through
            if (lastPoint != null) {
                //Make Line 
                visable = w.valid(resultPoint, lastPoint);

                //Update Last Point
                lastPoint = resultPoint;

                if (visable != null) {
                    //draw line, if there at all
                    gl.glVertex3d(visable.getPnt1().getX(), visable.getPnt1().getY(), 0);
                    gl.glVertex3d(visable.getPnt2().getX(), visable.getPnt2().getY(), 0);
                }
            } else {
                //this is first so no drawing

                //Update Last Point
                lastPoint = resultPoint;
            }
        }

        if (Header._DEBUG) {
            System.out.println();
        }




        // Done Drawing The curve
        gl.glEnd();

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }
}

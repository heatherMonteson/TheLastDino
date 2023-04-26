package src;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.Random;

/*
 * Utility: general logic used in multiple places in code
 *
 * Citation:
 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/Draw2DText.htm
 * rendering text in graphics:
 */


public interface Utility {

    /*
     * textRender: used to uncouple the text rendering from other methods
     *
     * @param Graphics2D, Color, String, int (x position), int (y position), int (font size)
     * @return nothing
     */
    static void textRender(Graphics2D graphics, Color color, String text, int x, int y, int size){

        try{
            FontRenderContext frc = graphics.getFontRenderContext();
            Font fontSelection = new Font("Courier", Font.BOLD, size);
            TextLayout textGraphics = new TextLayout(text, fontSelection, frc);
            graphics.setColor(color);
            textGraphics.draw(graphics, x, y);
        }catch (Exception ignored){}

    }

    /*
     * findValue: just rand values in a range
     *
     * @param int (lower bound) int (upper bound)
     * @return nothing
     */
    static int findValue(int lower, int upper){
        Random random=new Random();
        int value = random.nextInt(upper - lower + 1) + lower;
        return value;
    }

}
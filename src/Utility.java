package src;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

//Citation:
//Generating a value from a range https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
//Truncating a double: https://stackoverflow.com/questions/7747469/how-can-i-truncate-a-double-to-only-two-decimal-places-in-java#:~:text=will%20output%203.54-,DecimalFormat(%22%23.,for%20both%20Positive%20%26%20Negative%20values.

//Use for general math/logic reuse code
public interface Utility {

    /*
     * textRender: used to uncouple the text rendering
     *
     * @param Graphics2D, Color, String, int, int, int
     * @return nothing
     */
    static void textRender(Graphics2D graphics, Color color, String text, int x, int y, int size){
        //rendering text in graphics:
        //http://www.java2s.com/Code/Java/2D-Graphics-GUI/Draw2DText.htm
        try{
            FontRenderContext frc = graphics.getFontRenderContext();
            Font fontSelection = new Font("Courier", Font.BOLD, size);
            TextLayout textGraphics = new TextLayout(text, fontSelection, frc);
            graphics.setColor(color);
            textGraphics.draw(graphics, x, y);
        }catch (Exception ignored){}

    }

    //from a lower and upper bound generate a random number in that range
    // returns a double truncated to 2 decimal places
    static double findValue(double lower,double upper){
        Random r = new Random();
        double value = lower + (upper - lower) * r.nextDouble();
        return format(value);
    }

    //from a lower and upper bound generate a random number in that range
    // returns an int
    static int findValue(int lower, int upper){
        Random random=new Random();
        //int value = random.nextInt(lower, upper+1);
        //this fixed the error: This will generate a random integer between 0 and upper - lower,
        // then add lower to the result to shift the range to be between lower and upper.
        int value = random.nextInt(upper - lower + 1) + lower;
        return value;
    }

    static double format(double value){
        DecimalFormat formatting = new DecimalFormat("#.##");
        formatting.setRoundingMode(RoundingMode.DOWN);
        return Double.parseDouble(formatting.format(value));
    }
}
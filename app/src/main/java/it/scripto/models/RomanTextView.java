package it.scripto.models;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * View that create a roman text view.
 * @author pincopallino93 & Andrea Cerra
 * @version 1.0
 */
public class RomanTextView extends TextView {

    public RomanTextView(Context context) {
        super(context);
    }

    public RomanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * This method allow to set an arabic number in a
     * roman form ones in the text view.
     *
     * @param n the number to set.
     */
    public void setArabic(int n) {

        // If is a jolly character
        if (n == -1) {
            this.setText("");  // Set ""
        } else { // Else set the roman number

            try {
                RomanNumeral romanNumber = new RomanNumeral(n);
                this.setText(romanNumber.toString());
            } catch (NumberFormatException numberFormatException) {
                this.setText("NaN");  // In case of error set Not a Number
            }
        }
    }
}

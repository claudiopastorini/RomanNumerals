package it.scripto.models;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * View that create a roman edit text.
 * @author pincopallino93 & Andrea Cerra
 * @version 1.0
 */
public class RomanEditText extends EditText {

    public RomanEditText(Context context) {
        super(context);
        setWatcher();
    }

    public RomanEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWatcher();
    }

    /**
     * This method set the text watcher that allow to reformat
     * the edit text.
     */
    public void setWatcher() {
        this.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (count > 0) {

                    char c = s.charAt(count - 1);

                    if (c == 'I' || c == 'V' || c == 'X' || c == 'L' || c == 'C' || c == 'D' || c == 'M') {
                        removeTextChangedListener(this);
                        reformatRomanNumber(s.toString());
                        addTextChangedListener(this);
                        return;
                    }

                    if (c == 'i' || c == 'v' || c == 'x' || c == 'l' || c == 'c' || c == 'd' || c == 'm') {
                        removeTextChangedListener(this);
                        reformatRomanNumber(s.toString());
                        addTextChangedListener(this);
                        return;
                    }

                    if (s.toString().length() > 1) {
                        removeTextChangedListener(this);
                        reformatRomanNumber(removeLastCharacter(s.toString()));
                        addTextChangedListener(this);
                    }

                }
            }

        });
    }

    /**
     * This method allow to reformat the edit text with
     * the right expression.
     *
     * @param string the string to evaluate.
     */
    private void reformatRomanNumber(String string) {
        try {
            RomanNumeral romanNumber = new RomanNumeral(string);
            this.setText(romanNumber.toString());
            this.setSelection(this.getText().length());
        } catch (NumberFormatException ignored) {
            // Ignored
        }
    }

    /**
     * This method allow the last character from a string.
     *
     * @param string the string to modify.
     * @return the string without last character.
     */
    private String removeLastCharacter(String string){

        if (string.length() > 0) {
            string = string.substring(0, string.length() - 1);
        }

        return string;
    }

    /**
     * This method allow to return the number in arabic form
     * starting from the roman ones.
     *
     * @return the arabic form of the value in the edit text.
     */
    public String getArabic() {
        // Get the string and its length
        String string = this.getText().toString();
        int length = string.length();

        // Convert if > 0
        if (length > 0) {
            try {
                RomanNumeral romanNumber = new RomanNumeral(string);
                return Integer.toString(romanNumber.toInt());
            } catch (NumberFormatException numberFormatException) {  // If is not a correct roman
                return "NaN";                                        // return Not a Number
            }
        } else {  // Return "" otherwise
            return "";
        }
    }
}

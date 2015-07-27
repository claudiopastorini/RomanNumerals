package it.scripto.romannumerals;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import it.scripto.models.RomanEditText;
import it.scripto.models.RomanTextView;
import it.scripto.util.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Toolbar and set as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        // Set title empty
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }

        // Set listener
        setRomanListener();
        setEditTextRomanWatcher();
    }

    public void setRomanListener() {

        final EditText tvFirst = (EditText) findViewById(R.id.arabic_edit_text);
        tvFirst.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                RomanTextView romanTextView = (RomanTextView) findViewById(R.id.roman_text_view);
                try {
                    romanTextView.setArabic(Integer.parseInt(s.toString()));
                } catch (NumberFormatException numberFormatException) {  // Caused from parseInt
                    romanTextView.setArabic(-1);  // Use jolly character
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }

    /**
     *  Set textWatcher, we build the query at runtime
     * */
    public void setEditTextRomanWatcher() {

        final RomanEditText romanEditText = (RomanEditText) findViewById(R.id.roman_edit_text);
        romanEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                TextView arabicTextView = (TextView) findViewById(R.id.arabic_text_view);
                arabicTextView.setText(romanEditText.getArabic());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

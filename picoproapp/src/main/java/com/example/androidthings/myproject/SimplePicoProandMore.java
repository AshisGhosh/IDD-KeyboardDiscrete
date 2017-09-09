package com.example.androidthings.myproject;

import android.app.Activity;
import android.os.SystemClock;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Html;

import com.google.android.things.contrib.driver.adcv2x.Adcv2x;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.I2cDevice;
import com.google.android.things.pio.PeripheralManagerService;
import com.google.android.things.pio.Pwm;
import com.google.android.things.pio.SpiDevice;
import com.google.android.things.pio.UartDevice;

/**
 * Created by Ashis on 9/7/2017.
 */

public abstract class SimplePicoProandMore extends SimplePicoPro{
    private static final String TAG = SimpleBoard.class.getSimpleName();
    private Activity activity;

    public void setActivity2(Activity a) {
        activity = a;
    }

    void printCharacterToScreen2(char c) {
        if (activity == null) {
            Log.e(TAG,"printChar: activity is null");
            return;
        }

        EditText editText;
        editText = (EditText) activity.findViewById(R.id.editText2);

        if(editText != null) {
            editText.getText().append(c);

//            TextView view = (TextView)activity.findViewById(R.id.editText2);
//
//            String formattedText = editText.getText().toString() + "<b>" + c + "</b>";
//
//            view.setText(Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            Log.e(TAG,"printChar: Could not find R.id.editText2");
        }
    }

    void intializeCharSelect(char[][] alphabet){
        int[] temp_index = {0,0,0};
        int temp_row_index = 0;
        renderCharSelect(alphabet, temp_index, temp_row_index);
    }

    void renderCharSelect(char[][] alphabet, int[] index, int row_index){
//        TextView view = (TextView)activity.findViewById(R.id.editText);
        TextView view = null;
        String string, color, size_start, size_end, formatopen, formatclose;

        for (int i=0; i<alphabet.length; i++) {
            switch(i){
                case 0:
                    view = (TextView)activity.findViewById(R.id.editText);
                    break;
                case 1:
                    view = (TextView)activity.findViewById(R.id.editText3);
                    break;
                case 2:
                    view = (TextView)activity.findViewById(R.id.editText4);
                    break;
                default:
                    break;
            }

            if (row_index == i){
                color = "#FF0000";
                size_start = "<big><big><big>";
                size_end = "</big></big></big>";
            }
            else{
                color = "#0000FF";
                size_start = "<big>";
                size_end = "</big>";
            }

            formatopen = "<b><font color='" + color + "'>" + size_start;
            formatclose = size_end + "</font></b>";
            string = new String(alphabet[i]);
            if (index[i] == 0)
                string = formatopen + string.substring(index[i], index[i] + 1) + formatclose + string.substring(index[i] + 1, string.length());
            else if (index[i] == string.length())
                string = string.substring(0, index[i]) + formatopen + string.substring(index[i], index[i] + 1) + formatclose;
            else
                string = string.substring(0, index[i]) + formatopen + string.substring(index[i], index[i] + 1) + formatclose + string.substring(index[i] + 1, string.length());

            if (row_index ==i ){
                string = "<big>" + string + "</big>";
            }
            if (view != null)
                view.setText(Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY));
            else
                println("view is null for keyselect update");
        }
    }
}

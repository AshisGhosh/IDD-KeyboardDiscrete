package com.example.androidthings.myproject;

import android.app.Activity;
import android.os.SystemClock;
import android.text.TextUtils;
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

    void printStringToScreen2(String s) {
        if (activity == null) {
            Log.e(TAG,"printChar: activity is null");
            return;
        }

        EditText editText;
        editText = (EditText) activity.findViewById(R.id.editText2);

        if(editText != null) {
            editText.setCursorVisible(true);
            editText.getText().append(s);

//            TextView view = (TextView)activity.findViewById(R.id.editText2);
//
//            String formattedText = editText.getText().toString() + "<b>" + c + "</b>";
//
//            view.setText(Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            Log.e(TAG,"printStr: Could not find R.id.editText2");
        }
    }

    void intializeCharSelect(String[][] alphabet){
        int[] temp_index = {0,0,0,0};
        int temp_row_index = 0;
        renderCharSelect(alphabet, temp_index, temp_row_index);
    }

    void renderCharSelect(String[][] alphabet, int[] index, int row_index){
//        TextView view = (TextView)activity.findViewById(R.id.editText);
        TextView view = null;
        String string, color, size_start, size_end, formatopen, formatclose;
        string = null;

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
                case 3:
                    view = (TextView)activity.findViewById(R.id.editText5);
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
//            string = new String(alphabet[i]);

            if (i!=3) {
                string = TextUtils.join("", alphabet[i]);
                if (index[i] == 0)
                    string = formatopen + string.substring(index[i], index[i] + 1) + formatclose + string.substring(index[i] + 1, string.length());
                else if (index[i] == string.length())
                    string = string.substring(0, index[i]) + formatopen + string.substring(index[i], index[i] + 1) + formatclose;
                else
                    string = string.substring(0, index[i]) + formatopen + string.substring(index[i], index[i] + 1) + formatclose + string.substring(index[i] + 1, string.length());
            }
            else if(i == 3){
                if (index[i] == 0)
                    string = formatopen + alphabet[i][0] + formatclose + "\t" + alphabet[i][1];
                else if (index[i] ==1)
                    string = alphabet[i][0] + "\t" + formatopen + alphabet[i][1] + formatclose;
            }

            if (row_index ==i ){
                string = "<big>" + string + "</big>";
            }
            if (view != null)
                view.setText(Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY));
            else
                println("view is null for keyselect update");
        }
    }

    void deleteLastCharacter(){
        EditText editText;
        editText = (EditText) activity.findViewById(R.id.editText2);

        if(editText != null) {
            String string = editText.getText().toString();
            string = string.substring(0,string.length()-1);
            TextView view = (TextView)activity.findViewById(R.id.editText2);
            view.setCursorVisible(true);
            view.setText(string);
        } else {
            Log.e(TAG,"printChr: Could not find R.id.editText2");
        }
    }
}

package com.example.androidthings.myproject;

import com.google.android.things.pio.Gpio;

/**
 * Template for IDD Fall 2017 HW2 (text entry device)
 * Created by bjoern on 9/5/17.
 */

public class Hw2TemplateApp extends SimplePicoProandMore {

//    public char[] alphabet = {'A','B', 'C', 'D', 'E', 'F', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

//    public int index = 0;


    public char[][] alphabet = {{'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'}, {'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'}, {'Z', 'X', 'C', 'V', 'B', 'N', 'M', '\u2190'}};
    public int[] index = {0,0,0};
    public int row_index = 0;

    @Override
    public void setup() {
        //set two GPIOs to input
        pinMode(GPIO_128,Gpio.DIRECTION_IN);
        setEdgeTrigger(GPIO_128,Gpio.EDGE_BOTH);

        pinMode(GPIO_39,Gpio.DIRECTION_IN);
        setEdgeTrigger(GPIO_39,Gpio.EDGE_BOTH);

        pinMode(GPIO_37,Gpio.DIRECTION_IN);
        setEdgeTrigger(GPIO_37,Gpio.EDGE_BOTH);

        pinMode(GPIO_35,Gpio.DIRECTION_IN);
        setEdgeTrigger(GPIO_35,Gpio.EDGE_BOTH);

        pinMode(GPIO_34,Gpio.DIRECTION_IN);
        setEdgeTrigger(GPIO_34,Gpio.EDGE_BOTH);

        intializeCharSelect(alphabet);

    }

    @Override
    public void loop() {
        //nothing to do here

    }

    @Override
    void digitalEdgeEvent(Gpio pin, boolean value) {
        println("digitalEdgeEvent"+pin+", "+value);
        // when 128 goes from LOW to HIGH
        // this is on button button release for pull-up resistors
        if(pin==GPIO_128 && value==HIGH) {
//            printCharacterToScreen('a');
//            printStringToScreen("QWERTY");
            if (index[row_index] > 0)
                index[row_index]--;
        }
        //when 39 goes from HIGH to HIGH
        else if (pin==GPIO_39 && value==HIGH) {
//            printCharacterToScreen('b');
//            clearStringOnScreen();
//              printCharacterToScreen2('Q');
            if (index[row_index] < alphabet[row_index].length-1)
                index[row_index]++;
        }

        else if (pin==GPIO_35 && value==HIGH){
            if (row_index>0)
                row_index--;
        }
        else if (pin==GPIO_34 && value==HIGH){
            if (row_index<2)
                row_index++;
        }

        else if (pin==GPIO_37 && value==HIGH){
            printCharacterToScreen2(alphabet[row_index][index[row_index]]);
        }

        renderCharSelect(alphabet, index, row_index);

    }
}

package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Calculator anything = new Calculator();


    public void buttonClick(View view){

        anything.keyPress((char)view.getId());

        if (view.getId()== R.id.C) {
            anything.clear();
        }
        if (view.getId()== R.id.AC) {
            anything.allClear();
        }

        updateView();

    }

    private void updateView(){

        TextView display = findViewById(R.id.display);
        display.setText(anything.getDisplay());
    }

}
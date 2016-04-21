package com.example.accupass.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        //用(TextView)去把較高的級別轉換為較低的級別
        textView.setText("Hello World");
    }


    public void click(View view)
    {

        textView.setText("Android Class");

    }

}

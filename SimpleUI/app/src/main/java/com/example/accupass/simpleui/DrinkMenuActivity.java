package com.example.accupass.simpleui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DrinkMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
        Log.d("debug", "DrinkMenuActivity OnCreate");
    }

    public void add(View view)
    {
        Button button = (Button)view;

        String text = button.getText().toString();

        int count = Integer.parseInt(text);

        count++;

        button.setText(String.valueOf(count));
    }

    public void cancel(View view)
    {
        finish();

    }

    public void done(View view)
    {

        Intent intent = new Intent();
        intent.putExtra("result", getData().toString());

        setResult(RESULT_OK, intent);
        finish();

    }


    public JSONArray getData()
    {

        LinearLayout rootLinearLayout = (LinearLayout) findViewById(R.id.root);

        JSONArray jsonArray = new JSONArray();


        for(int i = 1; i <=3; i++)
        {
            LinearLayout linearLayout   = (LinearLayout) rootLinearLayout.getChildAt(i);

            TextView textView = (TextView)linearLayout.getChildAt(0);
            Button mButton = (Button) linearLayout.getChildAt(1);
            Button lButton = (Button) linearLayout.getChildAt(2);

            String drinkName = textView.getText().toString();

            int m = Integer.parseInt(mButton.getText().toString());
            int l = Integer.parseInt(lButton.getText().toString());

            //json就和字典一樣,後面是key, value


            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", drinkName);
                jsonObject.put("m", m);
                jsonObject.put("l", l);

                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return jsonArray;


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug", "DrinkMenuActivity OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "DrinkMenuActivity OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "DrinkMenuActivity OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "DrinkMenuActivity OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "DrinkMenuActivity OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "DrinkMenuActivity OnRestart");
    }




}

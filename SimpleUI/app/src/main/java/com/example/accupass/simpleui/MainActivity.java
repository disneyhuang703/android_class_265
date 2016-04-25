package com.example.accupass.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    ArrayList<Order> orders;


    String drinkName = "black tea";
    String note = "";
    CheckBox checkBox;
    ListView listView;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        checkBox = (CheckBox)findViewById(R.id.HideCheckBox);
        listView = (ListView)findViewById(R.id.listView);
        spinner = (Spinner)findViewById(R.id.spinner);
        orders = new ArrayList<>();


        //setOnkeyListner是一個interface,呼叫它時，就會自動帶出很多method來
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    click(v);
                    return true;
                }


                return false;
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    click(v);
                    return true;
                }
                return false;
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){

                    RadioButton radioButton = (RadioButton)findViewById(checkedId);
                    drinkName = radioButton.getText().toString();
                }


        });

        setupListView();
        setupSpinner();

    }


    //qau orders裡面的東西render到layout中
    void setupListView()
    {
        OrderAdapter adapter = new OrderAdapter(this, orders);
        listView.setAdapter(adapter);

    }


    void setupSpinner()
    {
        String[] data = getResources().getStringArray(R.array.storeInfo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data);

        spinner.setAdapter(adapter);


    }


    public void click(View view)
    {

        note = editText.getText().toString();
        String text = note;
        textView.setText(text);

        Order order = new Order();
        order.drinkName = drinkName;
        order.note = note;
        order.storeInfo = (String)spinner.getSelectedItem();

        orders.add(order);

        editText.setText("");

        setupListView();

    }





}

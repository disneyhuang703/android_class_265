package com.example.accupass.simpleui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    ArrayList<Order> orders;


    String drinkName;
    String note = "";
    CheckBox checkBox;
    ListView listView;
    Spinner spinner;


    //sp通常是用來存少量的資料（例如user的帳號資料）
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    Realm realm;



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

        sp = getSharedPreferences("setting", Context.MODE_PRIVATE);
        editor = sp.edit();

        // Create a RealmConfiguration which is to locate Realm file in package's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        // Get a Realm instance for this thread
        realm = Realm.getInstance(realmConfig);



        //若找不到key為editText的值，則回傳空字串

        editText.setText(sp.getString("editText",""));

        //setOnkeyListner是一個interface,呼叫它時，就會自動帶出很多method來
        editText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String text = editText.getText().toString();
                editor.putString("editText", text);
                editor.apply();

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


        int checkedId = sp.getInt("radioGroup", R.id.blackTeaRadioButton);
        radioGroup.check(checkedId);

        RadioButton radioButton = (RadioButton)findViewById(checkedId);
        drinkName = radioButton.getText().toString();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){

                editor.putInt("radioGroup", checkedId);
                editor.apply();

                    RadioButton radioButton = (RadioButton)findViewById(checkedId);
                    drinkName = radioButton.getText().toString();
                }


        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = (Order)parent.getAdapter().getItem(position);
                Snackbar.make(view, order.getNote(), Snackbar.LENGTH_LONG).show();


            }
        });


        setupListView();

        setupSpinner();



    }


    //qau orders裡面的東西render到layout中
    void setupListView()
    {
        RealmResults results = realm.allObjects(Order.class);


        OrderAdapter adapter = new OrderAdapter(this, results.subList(0, results.size()));
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
        order.setDrinkName(drinkName);
        order.setNote(note);
        order.setStoreInfo((String)spinner.getSelectedItem());





        // Persist your data easily
        realm.beginTransaction();
        realm.copyToRealm(order);
        realm.commitTransaction();


        editText.setText("");

        setupListView();

    }





}

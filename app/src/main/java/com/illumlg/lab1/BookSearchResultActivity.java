package com.illumlg.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class BookSearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search_result);
//        setSupportActionBar((Toolbar) findViewById(R.id.appbar));
//        Intent intent = getIntent();
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            search(query);
//        }
    }

//    public void button(View view) {
//        Toast.makeText(getApplicationContext(), "button clicked", Toast.LENGTH_SHORT)
//                .show();
//    }
//
//    public void check(View view) {
//        if(((CheckBox)view).isChecked())
//            Toast.makeText(getApplicationContext(), "checkbox checked", Toast.LENGTH_SHORT)
//                    .show();
//        else
//            Toast.makeText(getApplicationContext(), "checkbox unchecked", Toast.LENGTH_SHORT)
//                    .show();
//    }
//
//    public void radio1(View view) {
//        if(((RadioButton)view).isChecked())
//            Toast.makeText(getApplicationContext(), "radio 1 checked", Toast.LENGTH_SHORT)
//                    .show();
//    }
//
//    public void radio2(View view) {
//        if(((RadioButton)view).isChecked())
//            Toast.makeText(getApplicationContext(), "radio 2 checked", Toast.LENGTH_SHORT)
//                    .show();
//    }
//
//    public void toggle(View view) {
//        if(((ToggleButton)view).isChecked())
//            Toast.makeText(getApplicationContext(), "toggle on", Toast.LENGTH_SHORT)
//                    .show();
//        else
//            Toast.makeText(getApplicationContext(), "toggle off", Toast.LENGTH_SHORT)
//                    .show();
//    }
//
//    public void switchClick(View view) {
//        if(((Switch)view).isChecked())
//            Toast.makeText(getApplicationContext(), "switch on", Toast.LENGTH_SHORT)
//                    .show();
//        else
//            Toast.makeText(getApplicationContext(), "switch off", Toast.LENGTH_SHORT)
//                    .show();
//    }
//
//    private void search(String query) {
//
//    }
}
package com.illumlg.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

//    public static final String EXTRA_USER_NAME = "com.example.myfirstapp.USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavController navController
                = Navigation.findNavController(this, R.id.navHostFragment);
        AppBarConfiguration appBarConfiguration
                = new AppBarConfiguration.Builder(navController.getGraph()).build();
        Toolbar appbar = findViewById(R.id.appbar);
        setSupportActionBar(appbar);
        NavigationUI.setupWithNavController(appbar, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.search_book).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setSubmitButtonEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

//    public void greetUser(View view) {
//        Intent intent = new Intent(this, GreetActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editTextName);
//        String user_name = editText.getText().toString();
//        intent.putExtra(EXTRA_USER_NAME, user_name);
//        startActivity(intent);
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
}
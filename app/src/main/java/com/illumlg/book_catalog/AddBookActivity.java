package com.illumlg.book_catalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.illumlg.book_catalog.R;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppThemeLight);
        }
        setContentView(R.layout.activity_add_book);

        final Button button = findViewById(R.id.button5);
        EditText name = findViewById(R.id.bookName);
        EditText author = findViewById(R.id.bookAuthor);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(author.getText())) {
                setResult(Activity.RESULT_CANCELED, replyIntent);
            } else {
                String n = name.getText().toString();
                String a = author.getText().toString();
                replyIntent.putExtra("book name", n);
                replyIntent.putExtra("book author", a);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
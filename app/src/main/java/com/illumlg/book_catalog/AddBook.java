package com.illumlg.book_catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.illumlg.book_catalog.R;

public class AddBook extends AppCompatActivity {

    public static String EXTRA_REPLY_NAME;
    public static String EXTRA_REPLY_AUTHOR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                replyIntent.putExtra(EXTRA_REPLY_NAME, n);
                replyIntent.putExtra(EXTRA_REPLY_AUTHOR, a);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
package com.example.newsappv3;
// This is the Search Activity that allows the user search
// for articles based on keywords

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Search extends AppCompatActivity {

    Button submitButton;
    EditText et;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setTitle("Search");

        // Search Bar that takes user input
        submitButton = findViewById(R.id.submit);
        et = findViewById(R.id.editText);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.onEditorAction(EditorInfo.IME_ACTION_DONE);
                Intent i = new Intent(Search.this , SearchArticlePreview.class);
                str = et.getText().toString().trim();

                // Validate the user input
                if ( isValidInput(et.getText().toString()) ) {
                    str = et.getText().toString().trim();
                    i.putExtra("keyWord" , str);
                    startActivity(i);
                }
                else {
                    Toast errorMsg= Toast.makeText(getApplicationContext(),"Invalid Input",Toast. LENGTH_SHORT);
                    errorMsg.setGravity(Gravity.CENTER, 0, 0);
                    errorMsg.show();
                }
            }
        });

        // Set the bottom nav bar view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Search to be the selected activity
        bottomNavigationView.setSelectedItemId(R.id.search);

        //Listen for bottom nav bar activity
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        return true;
                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext(), Save.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
    
    // Validate the user input
    public boolean isValidInput(String str)
    {
        boolean result = true;
        if(str.trim().isEmpty())
        {
            result = false;
        }
        return result;
    }
}

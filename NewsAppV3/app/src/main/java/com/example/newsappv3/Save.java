package com.example.newsappv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

//This will display all the saved articles Mary "Abby" Strebel
public class Save extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        setTitle("Save");

        ListView listSaved = findViewById(R.id.listSaved);
        ProgressBar load = findViewById(R.id.loadSaved);
        listSaved.setEmptyView(load);

        checkPermissions();

        // Listener for Article clicks
        listSaved.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(Save.this, DisplayFullArticle.class);
                //i.putExtra("url",);//a.getUrl()); // Pass the Full article URL
                        //todo this
                startActivity(i);
            }
        });
        navBar(); //Handles navbar
    }

    private void navBar(){
        //set up the bottom nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set saved to be the selected item
        bottomNavigationView.setSelectedItemId(R.id.saved);

        //Listen for bottom navigation activity
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
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.saved:
                        return true;
                }
                return false;
            }
        });
    }
    private void checkPermissions(){
        //Checks if permissions are granted, and asks for them if needed
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(Save.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(Save.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }

        };
        //check all needed permissions together
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();

    }
}
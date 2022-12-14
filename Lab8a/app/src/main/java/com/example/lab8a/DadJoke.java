package com.example.lab8a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class DadJoke extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout mDrawer;
    ActionBarDrawerToggle mDrawerT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dad_joke);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        mDrawer = findViewById(R.id.drawer_layout);
        mDrawerT = new ActionBarDrawerToggle(this,mDrawer,toolbar,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mDrawerT);
        mDrawerT.syncState();
        NavigationView navigationView = findViewById(R.id.view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case
                    R.id.gohome:
                Intent intent = new Intent(DadJoke.this, MainActivity.class);
                DadJoke.this.startActivity(intent);
                break;

            case
                    R.id.exit:
                this.finishAffinity();
                break;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case
                    R.id.choice_1:
                Toast.makeText(this, "You clicked on item 1", Toast.LENGTH_SHORT).show();
                break;
            case
                    R.id.choice_2:
                Toast.makeText(this, "You clicked on item 2", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
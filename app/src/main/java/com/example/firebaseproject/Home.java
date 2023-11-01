 package com.example.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

 public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
CardView abosu;
     boolean doubleTapToExit = false; final int duration = 2000; View view; ListView listView;
     DrawerLayout drawerLayout; NavigationView navigationView; Toolbar toolbar;
     ArrayAdapter<String> arrayAdapter;
     CardView tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Dialog dialog;
        dialog=new Dialog(this);
       // abosu= (CardView) findViewById(R.id.abosu);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navi_view);
        toolbar = findViewById(R.id.toolbar);

        tool= (CardView) findViewById(R.id.tool);

        tool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,MainActivity.class);
                startActivity(intent);
            }
        });
//    abosu.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent=new Intent(Home.this,MainActivity.class);
//            startActivity(intent);
//        }
//    });
        setSupportActionBar(toolbar);

        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

     @Override
     public void onBackPressed() {
         if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
             drawerLayout.closeDrawer(GravityCompat.START);
         }
         else {
             super.onBackPressed();
         }

     }

     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem item) {

         int id = item.getItemId();

         if (id == R.id.Home) {

             Intent intent = new Intent(getApplicationContext(),Home.class);
             startActivity(intent);

         } else if (id == R.id.exit) {
             Toast.makeText(Home.this, "Exit selected", Toast.LENGTH_SHORT).show();
             moveTaskToBack(true);
             android.os.Process.killProcess(android.os.Process.myPid());
             System.exit(1);

         }

          else if (id == R.id.share) {
             Intent intent = new Intent(Intent.ACTION_SEND);
             intent.setType("text/plain");
             intent.putExtra(Intent.EXTRA_SUBJECT,"checkout this cool application");
             intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
             startActivity(Intent.createChooser(intent,"Share Via"));

         } else {

             Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
             Intent intent = new Intent(Intent.ACTION_VIEW,uri);
             startActivity(intent);
             try {
                 startActivity(intent);
             }
             catch (Exception e)
             {
                 Toast.makeText(Home.this, "Unable to open\n"+e.getMessage(), Toast.LENGTH_LONG).show();
             }
         }
         return true;

    }
}
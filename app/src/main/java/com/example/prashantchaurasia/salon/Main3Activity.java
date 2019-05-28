package com.example.prashantchaurasia.salon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences loginPreferences;
    TextView UsernameNavHeader ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //NAVHEADER

        loginPreferences = this.getSharedPreferences("loginprefs",MODE_PRIVATE);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        UsernameNavHeader = headerView.findViewById(R.id.user);
        String name = loginPreferences.getString("username"," ") ;
        Log.d("break", "onCreate: "+name);
        UsernameNavHeader.setText(name);
        Log.d("NavName","NavHeaderNAme" + UsernameNavHeader);


        String EmailHeader = loginPreferences.getString("email"," ");
        TextView UserEmailNavHeader = headerView.findViewById(R.id.UserEmailNavHeader);
        Log.d("mail", "onCreate: "+EmailHeader);
        UserEmailNavHeader.setText(EmailHeader);

        // SHAREPEREFERENCE SAVE VALUES
        String LVisit = loginPreferences.getString("last_login"," ");
        TextView LastVisitCard = findViewById(R.id.lastVisitCard);
        LastVisitCard.setText(LVisit);

        String ActDate = loginPreferences.getString("actdate"," ");
        TextView ActivateDate = findViewById(R.id.ActivateDate);
        ActivateDate.setText(ActDate);

        String ExDate = loginPreferences.getString("expdate"," ");
        TextView  ExpiryDate= findViewById(R.id.ExpiryDate);
        ExpiryDate.setText(ExDate);
        //ExpiryDate.setText(ExDate);
        loginPreferences = this.getSharedPreferences("loginprefs",MODE_PRIVATE);
        String BussinessName = loginPreferences.getString("business_name","");
        TextView BussinessName1 = findViewById(R.id.businessName);
        BussinessName1.setText(BussinessName);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //cardViewQuickBill
        CardView cardViewQuickBill = (CardView) findViewById(R.id.cardViewQuickBill);
        CardView cardAttendance = (CardView) findViewById(R.id.cardAttendance);
        CardView cardAddServices = (CardView) findViewById(R.id.CardAddServices);

        //Quick Bill
        cardViewQuickBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main3Activity.this, QuickBill.class);
                startActivity(intent);

            }
        });


        //Attendance
        cardAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main3Activity.this, Attendance.class);
                startActivity(intent);

            }
        });

        cardAddServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ServicesIntent = new Intent(Main3Activity.this,Services.class);
                startActivity(ServicesIntent);
            }
        });




    }



    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

        return;
    }





  /*  @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            SharedPreferences preferences = getSharedPreferences("loginprefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("saveLogin",false);
            editor.apply();
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            // Handle the camera action
            startActivity(new Intent(Main3Activity.this,Main3Activity.class));


        }
        else if (id == R.id.nav_Appointments)
        {
            Intent intent = new Intent(Main3Activity.this, BillForSale.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_Billing)
        {
            Intent intent = new Intent(Main3Activity.this, QuickBill.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_Customer)
        {
        }
        else if (id == R.id.nav_Staff)
        {
            Intent intent = new Intent(Main3Activity.this, Staff.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_send)
        {

        }
        else if (id == R.id.nav_Services){

            Intent intent = new Intent(Main3Activity.this,Services.class);
            startActivity(intent);
        }



                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        }



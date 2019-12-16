package com.rabi.simplecontacts.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;
import com.rabi.simplecontacts.R;

import java.util.Random;

import static com.rabi.simplecontacts.fragment.ContactFragment.CONTACT_Name;
import static com.rabi.simplecontacts.fragment.ContactFragment.CONTACT_PhoneNumber;

public class ContactDetailActivity extends AppCompatActivity {

    public static final String CONTACT_OTP = "contactDetailOTP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        final String mName = getIntent().getStringExtra(CONTACT_Name);
        final String mPhone = getIntent().getStringExtra(CONTACT_PhoneNumber);
        TextView pic = (TextView)findViewById(R.id.pic);
        TextView name = (TextView)findViewById(R.id.name);
        TextView phoneNumber = (TextView)findViewById(R.id.phoneNumber);
        pic.setText(mName.substring(0,1));
        pic.getBackground().setColorFilter(Color.parseColor("#"+generateOTP()), PorterDuff.Mode.SRC_ATOP);
        name.setText(mName);
        phoneNumber.setText(mPhone);
        Button actionBtn = (Button)findViewById(R.id.action_btn);
        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),SendActivity.class);
                startIntent.putExtra(CONTACT_Name, mName);
                startIntent.putExtra(CONTACT_PhoneNumber, mPhone);
                startIntent.putExtra(CONTACT_OTP, generateOTP());
                startActivity(startIntent);
            }
        });

    }

    public static String generateOTP() {
        Random ran = new Random();
        int num = ran.nextInt(900000) + 100000;
        return String.valueOf(num);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_license) {
            Intent intent = new Intent(this, OssLicensesMenuActivity.class);
            intent.putExtra("title", "Open Library license");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

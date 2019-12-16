package com.rabi.simplecontacts.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;
import com.rabi.simplecontacts.R;
import com.rabi.simplecontacts.db.dbHelper;
import com.rabi.simplecontacts.model.Contact;
import com.rabi.simplecontacts.model.Message;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

import static com.rabi.simplecontacts.activity.ContactDetailActivity.CONTACT_OTP;
import static com.rabi.simplecontacts.activity.ContactDetailActivity.generateOTP;
import static com.rabi.simplecontacts.fragment.ContactFragment.CONTACT_Name;
import static com.rabi.simplecontacts.fragment.ContactFragment.CONTACT_PhoneNumber;

public class SendActivity extends AppCompatActivity {

    private String TO_NUMBER = "";
    private String MSG = "Hi. Your OTP is: ";
    public static final String OTPCODE = "OTPCode";

    private String mName;
    private String mPhone;
    private String mOTP;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);



        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final TextView toolbarTitle = (TextView)findViewById(R.id.toolbarTitle);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mName = getIntent().getStringExtra(CONTACT_Name);
        mPhone = getIntent().getStringExtra(CONTACT_PhoneNumber);
        mOTP = getIntent().getStringExtra(CONTACT_OTP);

        TextView pic = (TextView)findViewById(R.id.pic);
        TextView name = (TextView)findViewById(R.id.name);
        TextView phoneNumber = (TextView)findViewById(R.id.phoneNumber);
        final TextView otpMsg = (TextView)findViewById(R.id.otpMsg);
        final EditText editTextOTP = (EditText) findViewById(R.id.editTextOTP);
        final LinearLayout verifyMsgBox =(LinearLayout)findViewById(R.id.verifyMsgBox);
        final TextView verifyMsg = (TextView)findViewById(R.id.verifyMsg);
        final ProgressBar VerifyProgressbar = (ProgressBar) findViewById(R.id.VerifyProgressBar);
        final ImageView Success = (ImageView)findViewById(R.id.success);
        final Button actionBtn = (Button)findViewById(R.id.action_btn);

        verifyMsgBox.setVisibility(View.INVISIBLE);
        editTextOTP.setVisibility(View.INVISIBLE);
        actionBtn.setText("Send");
        final boolean[] isVerifyAction = {false};

        TO_NUMBER = mPhone.substring(3,mPhone.length());
        //build OTP string
        MSG = MSG+mOTP+".";


        pic.setText(mName.substring(0,1));
        pic.getBackground().setColorFilter(Color.parseColor("#"+generateOTP()), PorterDuff.Mode.SRC_ATOP);
        name.setText(mName);
        phoneNumber.setText(mPhone);
        otpMsg.setText(MSG);

        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isInternetConnected(getApplicationContext(),true)){

                    if(isVerifyAction[0]){
                        isVerifyAction[0] = false;
                        if(((editTextOTP.getText().toString().length() > 0) && (editTextOTP.getText().toString().length() < 7))
                                && editTextOTP.getText().toString().equals(mOTP)){

                            verifyMsg.setTextColor(Color.DKGRAY);
                            verifyMsg.setText("OTP is Match Successfully!");
                            Success.setVisibility(View.VISIBLE);


                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    onBackPressed();
                                }
                            }, 2000);

                        } else {

                            verifyMsg.setTextColor(Color.RED);
                            verifyMsg.setText("Wong OTP!");
                        }

                    }else {
                        isVerifyAction[0] = true;
                        sendSmsOTP();
                        editTextOTP.setVisibility(View.VISIBLE);
                        verifyMsgBox.setVisibility(View.VISIBLE);
                        actionBtn.setText("Verify");
                        toolbarTitle.setText("Verify OTP");
                        otpMsg.setVisibility(View.INVISIBLE);
                        saveMessage();
                    }
                }
            }
        });
    }

    private void saveMessage() {

        dbHelper db = new dbHelper(getApplicationContext());
        Message message = new Message();

        Contact contact = new Contact();
        contact.setmFullName(mName);
        contact.setmNumber(mPhone);

        message.setContact(contact);
        message.setOTP(mOTP);
        message.setTimestamp(DateFormat.getDateTimeInstance().format(new Date()));

        db.setMessage(message);
    }


    private void sendSmsOTP() {
        Thread thread = new Thread(new Runnable() {
            URL url;
            HttpURLConnection urlConnection = null;

            @Override
            public void run() {
                try  {

                    url = new URL("https://smsphpserverx.000webhostapp.com/?msg="+MSG+"&numbers="+TO_NUMBER);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    int data = isw.read();

                    while (data != -1) {
                        data = isw.read();
                    }
                } catch (Exception e) {
                    //
                }finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }
        });
        thread.start();
    }


    public static  boolean isInternetConnected(Context context, boolean msg){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean connected = networkInfo != null && networkInfo.isConnected();
        if(msg && !connected){
            Toast.makeText(context,"Your are now offline!", Toast.LENGTH_SHORT).show();
        }
        return connected;

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

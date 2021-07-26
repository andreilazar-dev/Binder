package com.binder;
import  com.binder.activity.admin.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

//import android.util.Log;


public class AdminPage extends AppCompatActivity {

    public Button logout;
    public Button adduser;
    public Button removeuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        logout = findViewById(R.id.btn_logout);
        adduser = findViewById(R.id.btn_creautenti);
        removeuser = findViewById(R.id.btn_eliminautenti);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openadduser();
            }
        });

        removeuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openremoveuser();
            }
        });

    }
    private void openLogin (){
        Intent intent = new Intent(this,LoginPage.class);
        startActivity(intent);

    }
    private void openadduser(){
        Intent intent = new Intent(this,admin_adduser.class);
        startActivity(intent);
    }

    private void openremoveuser(){
        Intent intent = new Intent(this,admin_removeuser.class);
        startActivity(intent);
    }
}
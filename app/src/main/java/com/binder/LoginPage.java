package com.binder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.binder.database.DatabaseAccess;
import com.binder.database.datamodel.User;
import com.google.android.material.textfield.TextInputLayout;

public class LoginPage extends AppCompatActivity {

    public EditText user;
    public EditText password;
    public TextInputLayout userwrap;
    public TextInputLayout passwordwrap;
    public Button login_button;
    public User info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        final Context context = this;

// Inflate the layout
        user = (EditText) findViewById(R.id.et_user);
        userwrap = (TextInputLayout) findViewById(R.id.et_usewrapped);
        password = (EditText) findViewById(R.id.et_password);
        passwordwrap = (TextInputLayout) findViewById(R.id.et_passwordwrapped);
        login_button = findViewById(R.id.btn_login);

        // now setting onclicklistener to login

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
                databaseAccess.createDatabase();
                databaseAccess.open();

                //Getting full creditial if is correct
                info = databaseAccess.getLogin(user.getText().toString(), password.getText().toString());

                if (info == null) {
                    userwrap.setError(" ");
                    passwordwrap.setError("Username o Password Errati");
                }
                // Se il login funziona
                else if (user.getText().toString().equals("Admin")) {
                    Toast.makeText(context, user.getText().toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, info.toString(), Toast.LENGTH_SHORT).show();
                    openAdminPage();
                }
                Toast.makeText(context, user.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void openAdminPage (){
        Intent intent = new Intent(this,AdminPage.class);
        startActivity(intent);

    }
}
package com.fpacini.bookstore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fpacini.bookstore.R;
import com.fpacini.bookstore.utils.EncryptionHelper;
import com.fpacini.bookstore.utils.PersistenceManager;
import com.fpacini.bookstore.model.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity
{
    private EditText emailEt;
    private EditText passwordEt;
    private Button accedi;
    private Button registrati;
    private String email;
    private String password;
    private int userId;
    private PersistenceManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pm = PersistenceManager.getInstance();

        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        accedi = findViewById(R.id.accedi);
        registrati = findViewById(R.id.registrati);

        accedi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                email = emailEt.getText().toString().trim();
                password = passwordEt.getText().toString().trim();

                if (validateUser(email, password)) {
                    getUserId(email);
                    Intent i = new Intent(LoginActivity.this, CatalogoActivity.class);
                    i.putExtra("id", userId);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Email o password non corretta",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        registrati.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });
    }

    //metodo che verifica se l'utente è registrato
    public boolean validateUser(String email, String password) {
        ArrayList<User> users = pm.getUsers();
        String encodedPassword = EncryptionHelper.scramble(password);
        Log.d("LOGIN ENCODED PSW", encodedPassword);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(encodedPassword)) {
                return true;
            }
        }
        return false;
    }
    //metodo che restituisce l' idUtente,data la sua email
    public int getUserId(String email){
        for (User u : pm.getUsers()) {
            if (u.getEmail().equals(email)) {
                userId = u.getId();
                break;
            }
        }
        return userId;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //risetto i campi testo a null
        emailEt.setText(null);
        passwordEt.setText(null);
    }
}


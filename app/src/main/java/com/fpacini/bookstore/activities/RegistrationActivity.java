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

public class RegistrationActivity extends AppCompatActivity
{
    private EditText nomeEt;
    private EditText cognomeEt;
    private EditText emailEt;
    private EditText passwordEt;
    private EditText confermaPasswordEt;
    private Button registrati;
    private PersistenceManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        pm = PersistenceManager.getInstance();

        nomeEt = findViewById(R.id.nome_et);
        cognomeEt = findViewById(R.id.cognome_et);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        confermaPasswordEt = findViewById(R.id.conferma_pass_et);
        registrati = findViewById(R.id.registrati);

        registrati.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String nome = nomeEt.getText().toString().trim();
                String cognome = cognomeEt.getText().toString().trim();
                String email = emailEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
                String confermaPassword = confermaPasswordEt.getText().toString().trim();

                if (isUserAlreadyRegistered(email)) {
                    Toast.makeText(RegistrationActivity.this, "Risulta un utente già registrato con questa email",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    if (comparePasswords(password, confermaPassword)) {
                        String encodedPassword = EncryptionHelper.scramble(password);
                        User user = new User(pm.getNewId(), nome, cognome, email, encodedPassword);
                        Log.d("USER ID", "id: " + user.getId());
                        Log.d("ENCODED PASSWORD", encodedPassword);
                        pm.addUser(user);
                        Toast.makeText(RegistrationActivity.this, "Registrazione effettuata con successo!",
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(RegistrationActivity.this, "I campi password e conferma password non coincidono",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    //metodo che controlla se l'utente è già registrato con la mail inserita
    public boolean isUserAlreadyRegistered(String email) {
        for (User user : pm.getUsers()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    //metodo che controlla se i campi 'password' e 'confermaPass' inseriti sono identici
    public boolean comparePasswords(String password, String confermaPassword) {
         if (password.equals(confermaPassword)) {
            return true;
        }
        return false;
    }


}

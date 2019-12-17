package com.fpacini.bookstore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fpacini.bookstore.R;
import com.fpacini.bookstore.utils.PersistenceManager;

public class MainActivity extends AppCompatActivity
{
    private PersistenceManager pm;
    private Button accedi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pm = PersistenceManager.getInstance();
        pm.init();

        accedi = findViewById(R.id.accedi);
        accedi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
}
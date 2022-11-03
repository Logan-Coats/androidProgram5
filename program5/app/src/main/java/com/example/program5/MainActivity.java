package com.example.program5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public MainActivityViewModel mainActivityViewModel = null;

    TextView maxTV;
    TextView minTV;
    TextView currentTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.reset();
        mainActivityViewModel.getMaxText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                maxTV = findViewById(R.id.maxBottlesTV);
                maxTV.setText(s);
            }
        });
        mainActivityViewModel.getMinText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                minTV = findViewById(R.id.minBottlesTV);
                minTV.setText(s);
            }
        });
        mainActivityViewModel.getCurrentText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                currentTV = findViewById(R.id.currentBottlesTV);
                currentTV.setText(s);
            }
        });
        BottleCounterFragment bottleCounter = new BottleCounterFragment();
        SettingsFragment settings = new SettingsFragment();
        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction tr = fragMan.beginTransaction();
        tr.add(R.id.fragmentContainerView,settings,"SETTINGS");
        tr.add(R.id.fragmentContainerView,bottleCounter,"COUNTER");

        tr.show(bottleCounter);
        tr.hide(settings);
        tr.commit();
        Button swapBtn = findViewById(R.id.swapBtn);

        swapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();

                SettingsFragment setFrag = (SettingsFragment) fm.findFragmentByTag("SETTINGS");
                BottleCounterFragment bottleFrag = (BottleCounterFragment) fm.findFragmentByTag("COUNTER");

                FragmentTransaction tr = fm.beginTransaction();

                if( setFrag.isVisible()) {
                    tr.show(bottleFrag);
                    tr.hide(setFrag);

                } else {
                    tr.hide(bottleFrag);
                    tr.show(setFrag);
                }
                tr.commit();
            }
        });

    }
    // on add  remove bottle, if false, display toast message saying minimum/maximum capacity reached.

}
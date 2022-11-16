package com.maximustask.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.maximustask.R;
import com.maximustask.databinding.ActivityMainBinding;
import com.maximustask.model.FactModel;
import com.maximustask.viewmodel.MyViewModel;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyViewModel model;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();


        model = new ViewModelProvider(this).get(MyViewModel.class);


        model.factModel.observe(MainActivity.this, new Observer<FactModel>() {
            @Override
            public void onChanged(FactModel factModel) {
                progressDialog.dismiss();
                binding.factTextView.setText(factModel.getFact());
            }
        });


        model.loadFact();


        binding.loadfact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                model.loadFact();
            }
        });


    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }
}


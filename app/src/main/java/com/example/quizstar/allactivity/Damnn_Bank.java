package com.example.quizstar.allactivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.quizstar.Model.Balance;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Damnn_Bank extends AppCompatActivity {

    private ArrayList<String> demolist;
    ImageView back;
    LinearLayout selectR, selectA, withdraw, TopUp;
    private Dialog alertDialog;
    TextView spinner, spinnerA;
    private SharedPrefrences shPrfs;
    String auth;
    TextView tv_balance_with, tv_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damnn__bank);
        // getLayoutInflater().inflate(R.layout.activity_damnn__bank, frameLayout);
        TopUp = findViewById(R.id.TopUp);
        tv_balance_with = findViewById(R.id.tv_balance_with);
        back = findViewById(R.id.back);
        withdraw = findViewById(R.id.withdraw);
        selectR = findViewById(R.id.selectR);
        selectA = findViewById(R.id.selectA);
        spinner = findViewById(R.id.spinner);
        spinnerA = findViewById(R.id.spinnerA);
        tv_balance = findViewById(R.id.tv_balance);
        demolist = new ArrayList<>();
        demolist.add("America");
        demolist.add("Australia");
        demolist.add("United Kingdom");
        try {
            shPrfs = SharedPrefrences.getsharedprefInstance(Damnn_Bank.this);
            auth = shPrfs.getUserAuth().toString();
            balanceApi();
        } catch (Exception e) {

        }


        selectA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Damnn_Bank.this, Api_Payment.class);
                startActivity(intent);

            }
        });


        TopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Damnn_Bank.this, Top_Up_Activity.class);
                startActivity(intent);

            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Damnn_Bank.this);
                builder.setCancelable(false)
                        .setTitle("Alert !")
                        .setMessage("Do  you Want withdraw")
                        .setPositiveButton("yes ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

            }
        });


        // getLayoutInflater().inflate(R.layout.activity_damnn__bank, frameLayout);

    }

    public void balanceApi() {
        final ProgressDialog progressDialog = new ProgressDialog(Damnn_Bank.this);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        //Call<Delete> call = apiInterface.callDelete("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMzY3ZWI3ZjBjYmRkM2YwYzJmY2MwYTdkNzcyOTZkNTYxNmEyZGI3YzIxYzkyODE2ZjE5Y2YyNTAyZTQ1ZjQ2MGU5ZDAzOGFiMTkyNjcyZWMiLCJpYXQiOjE1OTU4NjY2NDYsIm5iZiI6MTU5NTg2NjY0NiwiZXhwIjoxNjI3NDAyNjQ2LCJzdWIiOiIzIiwic2NvcGVzIjpbImFkbWluIl19.Ek5pq6rK_5r-tCfgCTQzwpP16KHKaBfuCpirSkCXJIcOadDqLtPrwo96_f-QcrmTe4VEmgROPpI7ZIER504R0bucUno77T67y_BwBZZcmE9bcVpo-G6kiCg8V51NWScATKCKG2CtvkyqRTVXaZS0IXJwS7rN_OTzMHw700wfhIthAdanTCjOnycinlf2S84M_GRKOH6D6sQB7EUrQwJ_6mqku8BYLAsJupx2C1uczFnznnQxsp-lUDjgxphGtbAcq6gDRasQCJBAdIRBIe1o8CAyKCVQ9jKpAImZxLxZloU9w-OmfezEIJeBnX1NAbejbh2h_wNW3dlOz8opkknoy6G7rSsizvia2hJoPC-6fFzMwEdV01j9joEVhzZR1ByB8j6bj-1dZoKdLQPaEcEmSBVh4GoIUXgPX-w-JLpyVvNew6nLZTN0pXgmTwSQooGDWlanUTj2cvhn7Mv11uHGzfM_fK5Je0L0DM9RDh_qydDWPxrWPwEHoLv2MD9moeUSFTuxdmd8uIDn4iNRfueXKJo6OlSdfB4rrQYXkbbD2hVfK7-9ybLPQ8n-udDH6x_2TfTTPwvGXql61Ga9ykHv8BEBIaf6gKCCOX9lG9iUhX5Hk6bSh2C4RnqS7RolzF95DHufuDHUCkKtCj-pkNlBHkfUInVUjscjC66GJc-u6oI");
        Call<Balance> call = apiInterface.callbalance(auth);

        call.enqueue(new Callback<Balance>() {
            @Override
            public void onResponse(Call<Balance> call, Response<Balance> response) {
                progressDialog.dismiss();
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        Balance res = response.body();
                        Balance data = res.getData();
                        if (data.getKels() != null && data.getBalance() != null) {
                            int v = Integer.parseInt(data.getBalance());
                            int balance = v / 100;
                            tv_balance_with.setText("$ " + balance + "/K " + data.getKels());
                            tv_balance.setText("$ " + balance + "/K " + data.getKels());

                        }
                        // Toast.makeText(getActivity(), "" + data.getKels(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Damnn_Bank.this, "Data not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Balance> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(Damnn_Bank.this, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
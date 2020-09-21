package com.example.quizstar.allactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizstar.Model.User;
import com.example.quizstar.R;
import com.example.quizstar.Model.AllPojo.GetRegionPojo.GetRegionResponse;
import com.example.quizstar.Model.AllPojo.RegisterPojo.RegisterResponse;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.RetrofitForApiCalling.RetrofitInitialization;
import com.example.quizstar.sharedpreferences.SharedPrefrences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayList<List<String>> regionList = new ArrayList<java.util.List<String>>();
    ArrayList<String> demolist = new ArrayList<>();
    String regionData;
    EditText first_Name, last_Name, email, password, con_Password;
    Button sign_up;
    ImageView select_option1, select_option2;
    private SharedPrefrences shPrfs;
    RecyclerView recyclerView;
    private int passwordNotVisible = 1;
    private ImageView passwordHide, passwordHideC;
    LinearLayout linearLayout;
    private boolean select = true;
    String valuesS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recyclerView);
        spinner = findViewById(R.id.spinner);
        first_Name = findViewById(R.id.name);
        linearLayout = findViewById(R.id.linearLayout);
        last_Name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        con_Password = findViewById(R.id.con_password);
        sign_up = findViewById(R.id.signin_but2);
        select_option1 = findViewById(R.id.select_option1);
        select_option2 = findViewById(R.id.select_option2);
        passwordHide = findViewById(R.id.passwordHide);
        passwordHideC = findViewById(R.id.passwordHideC);
        shPrfs = SharedPrefrences.getsharedprefInstance(this);
        shPrfs.setUserName("");
        shPrfs.setUserLastName("");
        getRegionApi();

        passwordHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordNotVisible == 1) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }
                password.setSelection(password.length());

            }
        });
        passwordHideC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordNotVisible == 1) {
                    con_Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {
                    con_Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }
                con_Password.setSelection(con_Password.length());

            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (first_Name.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else if (con_Password.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                } else if (!password.getText().toString().equalsIgnoreCase(con_Password.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Password And Confirm Password Dose not match", Toast.LENGTH_SHORT).show();
                } else {
                    String name = first_Name.getText().toString();
                    String[] separated = name.split(" ");
                    String nameL;
                    String nameF = separated[0];
                    try {
                        if (separated[1] != null) {
                            nameL = separated[1];

                        } else {
                            nameL = " ";
                        }
                        registerApi(nameF, nameL);

                    } catch (Exception e) {
                        nameL = " ";
                        registerApi(nameF, nameL);

                    }

                    // Toast.makeText(RegisterActivity.this, "" + nameF, Toast.LENGTH_SHORT).show();
                }

            }
        });

        select_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_option2.setVisibility(View.VISIBLE);
                select_option1.setVisibility(View.GONE);
            }
        });

        select_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_option2.setVisibility(View.GONE);
                select_option1.setVisibility(View.VISIBLE);
            }
        });


    }

    public void getRegionApi() {
   /*     final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");*/

        Call<GetRegionResponse> call = RetrofitInitialization.getAAService().callRegionApi();
        call.enqueue(new Callback<GetRegionResponse>() {
            @Override
            public void onResponse(Call<GetRegionResponse> call, final Response<GetRegionResponse> response) {
                // progressDialog.dismiss();
                if (response.body().getStatus().equalsIgnoreCase("success")) {
                    regionList.add(response.body().getData().getRegions());
                    GetRegionResponse res = response.body();
                    String projectBlue = "#3F6AED";
                    int whiteInt = Color.parseColor(projectBlue);
                    for (int i = 0; i < res.getData().getRegions().size(); i++) {
                        final TextView rowTextView = new TextView(RegisterActivity.this);
                        rowTextView.setHeight(50);
                        rowTextView.setWidth(180);
                        rowTextView.setTextSize(11);
                        rowTextView.setPadding(10, 0, 10, 0);
                        rowTextView.setGravity(Gravity.CENTER);
                        rowTextView.setTextColor(whiteInt);
                        rowTextView.setBackgroundResource(R.drawable.hole_rect);
                        rowTextView.setText(res.getData().getRegions().get(i));
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins(15, 5, 15, 5);
                        rowTextView.setLayoutParams(params);
                        linearLayout.addView(rowTextView);
                        rowTextView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String white = "#ffffff";
                                int whiteInt = Color.parseColor(white);
                                if (select) {
                                    rowTextView.setBackgroundResource(R.drawable.rect_select_region);
                                    rowTextView.setTextColor(whiteInt);
                                    select = false;
                                } else {
                                    String white1 = "#3F6AED";
                                    int whiteInt1 = Color.parseColor(white1);
                                    rowTextView.setBackgroundResource(R.drawable.hole_rect);
                                    rowTextView.setTextColor(whiteInt1);
                                    select = true;

                                }


                                valuesS = rowTextView.getText().toString();
                            }
                        });

                    }



                 /*   region_selected adapter = new region_selected(RegisterActivity.this, res.getData().getRegions());
                    recyclerView.setLayoutManager(new LinearLayoutManager(RegisterActivity.this, LinearLayoutManager.HORIZONTAL, true));
                    recyclerView.setAdapter(adapter);*/


                } else {
                    Toast.makeText(RegisterActivity.this, "Region Data Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetRegionResponse> call, Throwable t) {
                //    progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void registerApi(String nameF, String nameL) {
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);

        Call<RegisterResponse> call = apiInterface.callRegisterApi(
                nameF,
                nameL,
                email.getText().toString(),
                password.getText().toString(),
                "America");

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    RegisterResponse res = response.body();
                    String auth = res.getTokenType() + " " + res.getAccessToken();
                    shPrfs.setUserAuth(auth);
                    hitApiUseData(auth);
                    Intent intent = new Intent(RegisterActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hitApiUseData(String auth) {

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<User> call = apiInterface.callUser("multipart/form-data", auth);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    if (response.isSuccessful()) {
                        User adListModel = response.body();
                        shPrfs.setUserName(adListModel.getData().getFirstname());
                        shPrfs.setUserLastName(adListModel.getData().getLastname());
                        shPrfs.setUseremail(adListModel.getData().getEmail());
                        shPrfs.setUserProfile(adListModel.getData().getAvatar());
                        shPrfs.setUserRegion(adListModel.getData().getRegion());

                        //       Toast.makeText(LoginActivity.this, "response"+shPrfs.getUserName()+shPrfs.getUseremail()+shPrfs.getUserProfile(), Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }


}
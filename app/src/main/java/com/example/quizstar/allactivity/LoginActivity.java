package com.example.quizstar.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.quizstar.Model.User;
import com.example.quizstar.R;
import com.example.quizstar.Model.AllPojo.Device_Token;
import com.example.quizstar.Model.AllPojo.FeaturesPojo.DataItem;
import com.example.quizstar.Model.AllPojo.RegisterPojo.RegisterResponse;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.InternetConnection;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout register_layout;
    EditText email, password;
    Button sign_In;
    private SharedPrefrences shPrfs;
    public static final int REQUEST_READ_PHONE_STATE = 8;
    ImageView passwordHide;
    private int passwordNotVisible = 1;
    ImageView select_option2, select_option;
    ImageView img_right;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ArrayList<DataItem> featuresList = new ArrayList<>();
        register_layout = findViewById(R.id.ragister_layout);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        img_right=findViewById(R.id.right);
        select_option2 = findViewById(R.id.select_option2);
        select_option = findViewById(R.id.select_option1);

        passwordHide = findViewById(R.id.passwordHide);
        sign_In = findViewById(R.id.signin_but1);
        shPrfs = SharedPrefrences.getsharedprefInstance(this);
        shPrfs.setUserName("");
        shPrfs.setUserLastName("");


        email.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                  boolean values=  match(s);
                  if (values)
                  {
                      img_right.setImageResource(R.drawable.right_icon);

                  }


                }

            }
        });

        passwordHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText paswword = findViewById(R.id.password);
                if (passwordNotVisible == 1) {
                    paswword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {
                    paswword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }
                paswword.setSelection(paswword.length());

            }
        });


        select_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_option2.setVisibility(View.VISIBLE);
                select_option.setVisibility(View.GONE);
            }
        });

        select_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_option2.setVisibility(View.GONE);
                select_option.setVisibility(View.VISIBLE);
            }
        });

    /*    int permissionCheckREAD_PHONE_STATE = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheckREAD_PHONE_STATE != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
            String device_id = ((TelephonyManager) LoginActivity.this.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();


        }*/



   /*     try {
            InstanceID instanceID = InstanceID.getInstance(this);

            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            Log.i(TAG, "GCM Registration Token: " + token);

        }catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }*/


        register_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });

    }

    private boolean match(CharSequence s) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches();

    }


    public void validation() {
        if (email.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else {


            try {
                String EMAIL = email.getText().toString();
                String password1 = password.getText().toString();
                shPrfs.setUserEmailId(EMAIL);
                shPrfs.setUserPassword(password1);

                if (InternetConnection.isConnected(getApplicationContext())) {
                    loginApi();
                } else {
                    new InternetConnectivtiyDialog(LoginActivity.this).show();
                    Toast.makeText(getApplicationContext(), "Please Enable InterNet Connection", Toast.LENGTH_SHORT).show();
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            } catch (Exception e) {

            }

            //hitApiCategoriesLIst();
            //featuresApi();

        }
    }


    /* "s@g.com",
             "123456"*/
    public void loginApi() {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        String userName = email.getText().toString();
        String passwordData = password.getText().toString();

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<RegisterResponse> call = apiInterface.callLoginApi(/*"application/json", "multipart/form-data",*/
                userName,
                passwordData);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        RegisterResponse res = response.body();
                        String auth = res.getTokenType() + " " + res.getAccessToken();
                        shPrfs.setUserAuth(auth);
                        hitApiUseData(auth);
                        if (auth != null) {
                            Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                            startActivity(intent);
                        }

                        //  hitDeviceToken(auth);
                        if (shPrfs.getUserName() != null && shPrfs.getUserLastName().equalsIgnoreCase("")) {
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Please Login ", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {

                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong username and password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void hitApiUseData(String auth) {

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<User> call = apiInterface.callUser("multipart/form-data", auth);
        //  final ProgressDialog dialog = AppProgress.showProgress(activity);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    //   Toast.makeText(getApplicationContext(), "" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        User adListModel = response.body();
                        shPrfs.setUserName(adListModel.getData().getFirstname());
                        shPrfs.setUserLastName(adListModel.getData().getLastname());
                        shPrfs.setUseremail(adListModel.getData().getEmail());
                        shPrfs.setUserProfile(adListModel.getData().getAvatar());
                        shPrfs.setUserRegion(adListModel.getData().getRegion());
                        //    Toast.makeText(LoginActivity.this, "response"+shPrfs.getUserName()+shPrfs.getUseremail()+shPrfs.getUserProfile(), Toast.LENGTH_SHORT).show();
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


    private void hitDeviceToken(String auth) {

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<Device_Token> call = apiInterface.callDevicetoken(auth, "b56c19c8-167a-4ae5-b954-fce0c7446370");
        //  final ProgressDialog dialog = AppProgress.showProgress(activity);

        call.enqueue(new Callback<Device_Token>() {
            @Override
            public void onResponse(Call<Device_Token> call, Response<Device_Token> response) {
                try {
                    //   Toast.makeText(getApplicationContext(), "" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        Device_Token adListModel = response.body();
                        //  Toast.makeText(LoginActivity.this, "response", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Device_Token> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }

/*
    public void featuresApi() {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        Call<FeaturesResponse> call = RetrofitInitialization.getAAService().callFeatures();
        call.enqueue(new Callback<FeaturesResponse>() {
            @Override
            public void onResponse(Call<FeaturesResponse> call, Response<FeaturesResponse> response) {

                progressDialog.dismiss();
                if (response.body().getStatus().equalsIgnoreCase("success")) {
                    FeaturesResponse list = response.body();
                    List<FeaturesResponse> list1 = list.getData();
                    Toast.makeText(LoginActivity.this, "" + list, Toast.LENGTH_SHORT).show();
                 */
/*   featuresList.addAll(response.body().getData());

                    HomeActivityAdapter homeActivityAdapter= new HomeActivityAdapter(getActivity());
                    homeActivityAdapter.add_data(featuresList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL,false);
                    mainList.setLayoutManager(gridLayoutManager);
                    mainList.hasFixedSize();
                    mainList.setAdapter(homeActivityAdapter);*//*

                } else {
                    Toast.makeText(LoginActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeaturesResponse> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
*/


    // Call<Device_Token> call = apiInterface.callDevicetoken("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMzY3ZWI3ZjBjYmRkM2YwYzJmY2MwYTdkNzcyOTZkNTYxNmEyZGI3YzIxYzkyODE2ZjE5Y2YyNTAyZTQ1ZjQ2MGU5ZDAzOGFiMTkyNjcyZWMiLCJpYXQiOjE1OTU4NjY2NDYsIm5iZiI6MTU5NTg2NjY0NiwiZXhwIjoxNjI3NDAyNjQ2LCJzdWIiOiIzIiwic2NvcGVzIjpbImFkbWluIl19.Ek5pq6rK_5r-tCfgCTQzwpP16KHKaBfuCpirSkCXJIcOadDqLtPrwo96_f-QcrmTe4VEmgROPpI7ZIER504R0bucUno77T67y_BwBZZcmE9bcVpo-G6kiCg8V51NWScATKCKG2CtvkyqRTVXaZS0IXJwS7rN_OTzMHw700wfhIthAdanTCjOnycinlf2S84M_GRKOH6D6sQB7EUrQwJ_6mqku8BYLAsJupx2C1uczFnznnQxsp-lUDjgxphGtbAcq6gDRasQCJBAdIRBIe1o8CAyKCVQ9jKpAImZxLxZloU9w-OmfezEIJeBnX1NAbejbh2h_wNW3dlOz8opkknoy6G7rSsizvia2hJoPC-6fFzMwEdV01j9joEVhzZR1ByB8j6bj-1dZoKdLQPaEcEmSBVh4GoIUXgPX-w-JLpyVvNew6nLZTN0pXgmTwSQooGDWlanUTj2cvhn7Mv11uHGzfM_fK5Je0L0DM9RDh_qydDWPxrWPwEHoLv2MD9moeUSFTuxdmd8uIDn4iNRfueXKJo6OlSdfB4rrQYXkbbD2hVfK7-9ybLPQ8n-udDH6x_2TfTTPwvGXql61Ga9ykHv8BEBIaf6gKCCOX9lG9iUhX5Hk6bSh2C4RnqS7RolzF95DHufuDHUCkKtCj-pkNlBHkfUInVUjscjC66GJc-u6oI", "b56c19c8-167a-4ae5-b954-fce0c7446370");

}
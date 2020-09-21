package com.example.quizstar.allactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.quizstar.Model.PaymetAdd;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.ApiP;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;

import java.util.HashMap;
import java.util.Map;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import retrofit2.Call;
import retrofit2.Callback;


public class Api_Payment extends AppCompatActivity {
    String url = "https://www.dipanshutech.com/sportstrade/api/paywithstripe";
    // String url1 = "http://api.myquizstar.com/api/login";
    Button signin_but2;
    EditText card_no, amount, cvv, exp_date, exp_year;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api__payment);
        signin_but2 = findViewById(R.id.signin_but2);
        card_no = findViewById(R.id.card_no);
        amount = findViewById(R.id.ammout);
        cvv = findViewById(R.id.CVV);
        exp_date = findViewById(R.id.Exp_month);
        exp_year = findViewById(R.id.Exp_year);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Api_Payment.this, NavigationActivity.class);
                startActivity(intent);
            }
        });

        signin_but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card_no.getText().toString().isEmpty()) {
                    Toast.makeText(Api_Payment.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (amount.getText().toString().isEmpty()) {
                    Toast.makeText(Api_Payment.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                } else if (cvv.getText().toString().isEmpty()) {
                    Toast.makeText(Api_Payment.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (exp_date.getText().toString().isEmpty()) {
                    Toast.makeText(Api_Payment.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else if (exp_year.getText().toString().isEmpty()) {
                    Toast.makeText(Api_Payment.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                } else {
                    String card = card_no.getText().toString();
                    String amout = amount.getText().toString();
                    String cvvS = cvv.getText().toString();
                    String expYear = exp_year.getText().toString();
                    String exp_dateS = exp_date.getText().toString();
                    registerApi(card, amout, cvvS, expYear, exp_dateS);
                }
            }
        });


    }


    public void registerApi(String card, String amout, String cvvS, String expYear, String exp_dateS) {
        final ProgressDialog progressDialog = new ProgressDialog(Api_Payment.this);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        QuizStarServices apiInterface = ApiP.postApiClient(Api_Payment.this).create(QuizStarServices.class);
        Call<PaymetAdd> call = apiInterface.callPaynApil("2", card, exp_dateS, expYear, cvvS, amout);
        call.enqueue(new Callback<PaymetAdd>() {
            @Override
            public void onResponse(Call<PaymetAdd> call, retrofit2.Response<PaymetAdd> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    PaymetAdd res = response.body();

                    if (res.getMessage().equalsIgnoreCase("Payment success")) {
                        Intent intent = new Intent(Api_Payment.this, NavigationActivity.class);
                        startActivity(intent);
                        Toast.makeText(Api_Payment.this, "" + res.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Api_Payment.this, "" + res.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    finish();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Api_Payment.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PaymetAdd> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Api_Payment.this, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hitApi() {


/*        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("user_id", "2");
        requestParams.put("card_number", "4242424242424242");
        requestParams.put("exp_month", "12");
        requestParams.put("exp_year", "2024");
        requestParams.put("cvv", "111");
        requestParams.put("amount", "1");*/


        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final String d = new String(response);
                        Intent mIntent = new Intent(Api_Payment.this, Api_Payment.class);
                        startActivity(mIntent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Api_Payment.this, "" + error, Toast.LENGTH_SHORT).show();

                    }
                }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Basic" + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMzE5OGU3MzJkMjZiYWI5NjY0MzNiMWZhYjRjZjg5MDJmNjhiZmNjYjQ3YjBjYjkyY2M0ODk4NmRmNWU5ODc5MGRjOWRhZDkxYTRjZDQ2MTEiLCJpYXQiOjE1OTgxMjMyMjgsIm5iZiI6MTU5ODEyMzIyOCwiZXhwIjoxNjI5NjU5MjI4LCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.hPIY0QT_JZuGMcMj6mfcYi_GxKuF30QmaorBNovmypVjNZIHFBWWw7HZhLJufiI9l-zTulpJjqBHvB3pwIRO2ISrSYdkSbVHKiBtEkviP7Wc0AB5UEDc0Y0cEAkDKUKyuJd9me09jFzgklIJsCGp4B3Zm2oUucDhHh26TDvK8TxWhFnYdjCzYYlL9ws-Vo2hJ6-zYHpx0Av80TgmyCo_UTgRWgOY8bGczM0RU5hLQKeUN0Pt8qYgFwaq2jy6mwYUo-QhScyb0u_emwwmcW-pddaymSKw-310GaG0ke9yfzGZXyt0ULhcQ6n3WlYhF_HGnVbUawv8K02hxaVGUDfRwAAx56w9U6GhRxfzfx5GGtzNAu2keaBozVZ74pjcccjYgxe_we4QCkLAsvAYx0tPllPEqHNSGaaXxHZALd_WroDm4r971LjI5NNVXUOLlgIBgE1huR25C2PRUSOdFSA63VCZp8zuUShgq02HA89N8YHImAa3Ep5tlr3EVRi4In1MiGNG_ijrXURlyeNeqpx7RriuuN0au5SsB2puaiWVxXA4P_MnGnceWhigF7tuAd3npqhI9oNy9cjVzkD4M9ljTxwPXquqGhWpPVsZ3c8fuMYQmZ_f5e5qO98TGkbNP2H_g3Fu5TuD8Qy9LfsBwITM6HJ5C7spaLgD0wfgqgGESHA");
                return headers;
            }

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> requestParams = new HashMap<>();
                requestParams.put("user_id", "2");
                requestParams.put("card_number", "4242424242424242");
                requestParams.put("exp_month", "12");
                requestParams.put("exp_year", "2024");
                requestParams.put("cvv", "111");
                requestParams.put("amount", "500");
                return requestParams;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Api_Payment.this);
        requestQueue.add(stringRequest);






  /*      StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(Api_Payment.this, "Register Success!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Api_Payment.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();


                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Api_Payment.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> requestParams = new HashMap<>();
                requestParams.put("user_id", "2");
                requestParams.put("card_number", "4242424242424242");
                requestParams.put("exp_month", "12");
                requestParams.put("exp_year", "2024");
                requestParams.put("cvv", "111");
                requestParams.put("amount", "1");
                return requestParams;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/


    /*    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            // called when response HTTP status is "200 OK"
                            final String responsex = new String(response);
                            JSONObject jsonObject = new JSONObject(responsex);
                            Toast.makeText(Api_Payment.this, "" + jsonObject, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Api_Payment.this, "" + e, Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Api_Payment.this, "" + error, Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> requestParams = new HashMap<>();
                requestParams.put("user_id", "2");
                requestParams.put("card_number", "4242424242424242");
                requestParams.put("exp_month", "12");
                requestParams.put("exp_year", "2024");
                requestParams.put("cvv", "111");
                requestParams.put("amount", "1");
                return super.getParams();
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
*/





        /*final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final String d = new String(response);
                        Toast.makeText(Api_Payment.this, "" + response.toString(), Toast.LENGTH_SHORT).show();

                        Intent mIntent = new Intent(Api_Payment.this, Api_Payment.class);
                        startActivity(mIntent);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Api_Payment.this, "" + error, Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> requestParams = new HashMap<String, String>();
                requestParams.put("user_id", "2");
                requestParams.put("card_number", "4242424242424242");
                requestParams.put("exp_month", "12");
                requestParams.put("exp_year", "2024");
                requestParams.put("cvv", "111");
                requestParams.put("amount", "1");
                return requestParams;
            }
        };

        *//*RequestQueue requestQueue = Volley.newRequestQueue(Api_Payment.this);
        requestQueue.add(stringRequest);*//*

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);*/
    }
}
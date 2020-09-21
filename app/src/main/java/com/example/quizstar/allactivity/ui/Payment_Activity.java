package com.example.quizstar.allactivity.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizstar.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;


import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Payment_Activity extends AppCompatActivity {
    // 10.0.2.2 is the Android emulator's alias to localhost
    //private static final String BACKEND_URL1 = "https://www.mbinfosystem.com/stripe/create.php";
    private static final String BACKEND_URL = "https://www.dipanshutech.com/sportstrade/api/paywithstripe";


    private OkHttpClient httpClient = new OkHttpClient();
    //private String paymentIntentClientSecret = "pi_1HM7mECWWYlp5ZvmxWWe2XwR_secret_nFNjDp7OfI1trXfYk0nSPZcaB";
    private Stripe stripe;
    private String paymentIntentClientSecret;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_);
        // Configure the SDK with your Stripe publishable key so it can make requests to Stripe
        stripe = new Stripe(
                getApplicationContext(),
                Objects.requireNonNull("pk_live_51HLKqcCWWYlp5ZvmWGluyv1xr91pWp1BxZJwhunhWogC41iDMmG17kNfmd2Yimel5JKuRn7ESkxUgKAuG39X2KK000LI2naRAd")
        );
        startCheckout();
    }

    private void startCheckout() {
        // Create a PaymentIntent by calling the server's endpoint.


        double amount = Double.parseDouble("1") * 100;
        Map<String, Object> payMap = new HashMap<>();
        Map<String, Object> itemMap = new HashMap<>();
        List<Map<String, Object>> itemList = new ArrayList<>();
/*
        payMap.put("currency", "usd");
*/
        itemMap.put("user_id", "2");
        itemMap.put("amount", "1");
        itemMap.put("card_number", "4242424242424242");
        itemMap.put("exp_month", "12");
        itemMap.put("exp_year", "2024");
        itemMap.put("cvv", "111");
        itemList.add(itemMap);
        payMap.put("items", itemList);
        String json = new Gson().toJson(payMap);
        //  Log.i("TAG", "startCheckout: " + json);

        // Create a PaymentIntent by calling the server's endpoint.
        // MediaType mediaType = MediaType.get("application/json; charset=utf-8");


        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
  /*      String json = "{"
                + ""currency":"usd","
                + ""items":["
                + "{"id":"photo_subscription"}"
                + "]"
                + "}";*/
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(BACKEND_URL + "create-payment-intent")
                .post(body)
                .build();
        httpClient.newCall(request)
                .enqueue(new PayCallback(Payment_Activity.this));
        // Hook up the pay button to the card widget and stripe instance
        Button payButton = findViewById(R.id.payButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
                PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
                if (params != null) {
                    ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                            .createWithPaymentMethodCreateParams(params, paymentIntentClientSecret);
                    stripe.confirmPayment(Payment_Activity.this, confirmParams);
                }
            }
        });
/*        payButton.setOnClickListener((View view) -> {
            CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
            PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
            if (params != null) {
                ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                        .createWithPaymentMethodCreateParams(params, paymentIntentClientSecret);
                stripe.confirmPayment(this, confirmParams);
            }
        });*/
    }

    private void displayAlert(@NonNull String title,
                              @Nullable String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message);
        builder.setPositiveButton("Ok", null);
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle the result of stripe.confirmPayment
        stripe.onPaymentResult(requestCode, data, new PaymentResultCallback(this));
    }

    private void onPaymentSuccess(@NonNull final Response response) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> responseMap = gson.fromJson(
                Objects.requireNonNull(response.body()).string(),
                type
        );
        try {
            paymentIntentClientSecret = responseMap.get("clientSecret");
            if (paymentIntentClientSecret == null) {
                paymentIntentClientSecret = "pi_1HM7mECWWYlp5ZvmxWWe2XwR_secret_nFNjDp7OfI1trXfYk0nSPZcaB";
            }
            Toast.makeText(this, "" + paymentIntentClientSecret, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }

    }

    private static final class PayCallback implements Callback {
        @NonNull
        private final WeakReference<Payment_Activity> activityRef;

        PayCallback(@NonNull Payment_Activity activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            final Payment_Activity activity = activityRef.get();
            if (activity == null) {
                return;
            }
/*            activity.runOnUiThread(() ->
                    Toast.makeText(
                            activity, "Error: " + e.toString(), Toast.LENGTH_LONG
                    ).show()
            );*/
        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull final Response response)
                throws IOException {
            final Payment_Activity activity = activityRef.get();
            if (activity == null) {
                return;
            }
            if (!response.isSuccessful()) {
            /*    activity.runOnUiThread(() ->
                        Toast.makeText(
                                activity, "Error: " + response.toString(), Toast.LENGTH_LONG
                        ).show()
                );*/
            } else {
                activity.onPaymentSuccess(response);
            }
        }
    }

    private static final class PaymentResultCallback
            implements ApiResultCallback<PaymentIntentResult> {
        @NonNull
        private final WeakReference<Payment_Activity> activityRef;

        PaymentResultCallback(@NonNull Payment_Activity activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(@NonNull PaymentIntentResult result) {
            final Payment_Activity activity = activityRef.get();
            if (activity == null) {
                return;
            }
            PaymentIntent paymentIntent = result.getIntent();
            PaymentIntent.Status status = paymentIntent.getStatus();
            if (status == PaymentIntent.Status.Succeeded) {
                // Payment completed successfully
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                activity.displayAlert(
                        "Payment completed",
                        gson.toJson(paymentIntent)
                );
            } else if (status == PaymentIntent.Status.RequiresPaymentMethod) {
                // Payment failed – allow retrying using a different payment method
                activity.displayAlert(
                        "Payment failed",
                        Objects.requireNonNull(paymentIntent.getLastPaymentError()).getMessage()
                );
            }
        }

        @Override
        public void onError(@NonNull Exception e) {
            final Payment_Activity activity = activityRef.get();
            if (activity == null) {
                return;
            }
            // Payment request failed – allow retrying using the same payment method
            activity.displayAlert("Error", e.toString());
        }
    }
}
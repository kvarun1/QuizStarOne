package com.example.quizstar.allactivity.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizstar.allactivity.Damnn_Bank;
import com.example.quizstar.AllAdapter.HomeActivityAdapter;
import com.example.quizstar.Model.Balance;
import com.example.quizstar.R;
import com.example.quizstar.Model.AllPojo.FeaturesPojo.DataItem;
import com.example.quizstar.Model.AllPojo.FeaturesPojo.FeaturesResponse;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.RetrofitForApiCalling.RetrofitInitialization;
import com.example.quizstar.sharedpreferences.SharedPrefrences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    ArrayList<DataItem> featuresList = new ArrayList<>();
    RecyclerView mainList;
    private SharedPrefrences shPrfs;
    private String auth;
    TextView tv_balance;
    TextView damnn_bank;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tv_balance = view.findViewById(R.id.tv_balance);
        mainList = view.findViewById(R.id.mainList);
        damnn_bank = view.findViewById(R.id.damnn_bank);

        damnn_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Damnn_Bank.class);
                startActivity(intent);
            }
        });

        try {
            shPrfs = SharedPrefrences.getsharedprefInstance(getActivity());
            auth = shPrfs.getUserAuth().toString();
            featuresApi();
            balanceApi();
        } catch (Exception e) {

        }
        return view;
    }

    public void featuresApi() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        Call<FeaturesResponse> call = RetrofitInitialization.getAAService().callFeatures();
        call.enqueue(new Callback<FeaturesResponse>() {
            @Override
            public void onResponse(Call<FeaturesResponse> call, Response<FeaturesResponse> response) {

                progressDialog.dismiss();
                if (response.body().getStatus().equalsIgnoreCase("success")) {
                    featuresList.addAll(response.body().getData());
                        HomeActivityAdapter homeActivityAdapter = new HomeActivityAdapter(getActivity());
                    homeActivityAdapter.add_data(featuresList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false);
                    mainList.setLayoutManager(gridLayoutManager);
                    mainList.hasFixedSize();
                    mainList.setAdapter(homeActivityAdapter);
                } else {
                    Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeaturesResponse> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void balanceApi() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
                            tv_balance.setText("$ " + balance + "/K " + data.getKels());
                        }
                        // Toast.makeText(getActivity(), "" + data.getKels(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Balance> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


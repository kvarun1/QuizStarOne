package com.example.quizstar.AllAdapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizstar.allactivity.Api_Payment;
import com.example.quizstar.Model.Balance;
import com.example.quizstar.Model.Ignore_Challenge;
import com.example.quizstar.Model.Sent_Data;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerView_Pending extends RecyclerView.Adapter<RecyclerView_Pending.ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<Sent_Data> dataS, dataR;
    private int va = 1;
    private SharedPrefrences shPrfs;
    private String auth;


    public RecyclerView_Pending(Context context, List<Sent_Data> dataS1) {
        this.context = context;
        dataS = dataS1;
    }

    @NonNull
    @Override
    public RecyclerView_Pending.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_recyclerview
                        , parent, false);

        return new RecyclerView_Pending.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView_Pending.ViewHolder holder, final int position) {
        try {
            shPrfs = SharedPrefrences.getsharedprefInstance(context);
            auth = shPrfs.getUserAuth().toString();

            if (dataS.get(position).getFrom().getUser().getAvatar() != null && !dataS.get(position).getFrom().getUser().getAvatar().equalsIgnoreCase("")) {
                Picasso.with(context).load(dataS.get(position).getFrom().getUser().getAvatar()).error(R.drawable.dummy_profilex).into(holder.circleImageView_first);
            }
            if (dataS.get(position).getTo().getUser().getAvatar() != null && !dataS.get(position).getTo().getUser().getAvatar().equalsIgnoreCase("")) {
                Picasso.with(context).load(dataS.get(position).getTo().getUser().getAvatar()).error(R.drawable.dummy_profilex).into(holder.circleImageView_SecondR);
            }
            if (dataS.get(position).getFrom().getProgress().getStatus() != null) {
                holder.tv_challenge.setText(dataS.get(position).getFrom().getProgress().getStatus());
            }
            if (dataS.get(position).getTo().getProgress().getStatus() != null) {
                holder.tv_challengeR.setText(dataS.get(position).getTo().getProgress().getStatus());
            }

            if (dataS.get(position).getState() != null) {
                if (dataS.get(position).getState().equalsIgnoreCase("waiting")) {
                    holder.tv_waiting.setVisibility(View.VISIBLE);
                    holder.tv_play.setVisibility(View.GONE);
                    holder.tv_Ignore.setVisibility(View.GONE);
                }
            }


        } catch (Exception e) {
            //  Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
        }
        holder.tv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balanceApi();
            }
        });
        holder.tv_Ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitApiIgnore(auth);
            }
        });
    }


    @Override
    public int getItemCount() {
        try {
            int a = dataR.size() + dataS.size();
           /* int a = dataR.size() + dataS.size();
            if (dataR.size() > dataS.size()) {
                return dataR.size();
            } else {
                return dataS.size();
            }*/
            return a;
        } catch (Exception e) {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_waiting, tv_challenge, tv_challengeR, tv_play, tv_Ignore;
        CircleImageView circleImageView_SecondR, circleImageView_first;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView_first = itemView.findViewById(R.id.first_images);
            circleImageView_SecondR = itemView.findViewById(R.id.second_profile);
            tv_challenge = itemView.findViewById(R.id.tv_Challenged);
            tv_challengeR = itemView.findViewById(R.id.tv_Challenged2);
            tv_play = itemView.findViewById(R.id.play);
            tv_Ignore = itemView.findViewById(R.id.ignore);
            tv_waiting = itemView.findViewById(R.id.waiting);
            //va = dataR.size();


        }
    }

    public void balanceApi() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
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
                            int balanceD = Integer.parseInt(data.getBalance());
                            if (balanceD < 5.0) {
                                Intent intent = new Intent(context, Api_Payment.class);
                                context.startActivity(intent);
                            } else {

                            }
                        }
                    } else {
                        Toast.makeText(context, "Data not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Balance> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(context, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void hitApiIgnore(String auth) {
        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<Ignore_Challenge> call = apiInterface.callIgnore(auth, "17");
        //  final ProgressDialog dialog = AppProgress.showProgress(activity);

        call.enqueue(new Callback<Ignore_Challenge>() {
            @Override
            public void onResponse(Call<Ignore_Challenge> call, Response<Ignore_Challenge> response) {
                try {
                    //   Toast.makeText(getApplicationContext(), "" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        Ignore_Challenge adListModel = response.body();
                        Toast.makeText(context, "" + adListModel.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Ignore_Challenge> call, Throwable t) {
                Toast.makeText(context, "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }

}


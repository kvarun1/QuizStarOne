package com.example.quizstar.allactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.quizstar.AllAdapter.Top_up_Recycler_view;
import com.example.quizstar.R;
import com.example.quizstar.sharedpreferences.SharedPrefrences;

public class Top_Up_Activity extends AppCompatActivity {
    VideoView videoView;
    private RecyclerView recyclerView;
    private SharedPrefrences shPrfs;
    private String auth;
    private int position = 1;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top__up_);
        videoView = findViewById(R.id.videoView);
        // mediaController = new MediaController(this);

        // treasure
        recyclerView = findViewById(R.id.recyclerView_top);

        Top_up_Recycler_view adapter = new Top_up_Recycler_view(Top_Up_Activity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Top_Up_Activity.this, LinearLayoutManager.VERTICAL, true));
        recyclerView.setAdapter(adapter);
        playVideo();
        try {
            shPrfs = SharedPrefrences.getsharedprefInstance(Top_Up_Activity.this);
            auth = shPrfs.getUserAuth().toString();
        } catch (Exception e) {

        }

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                try {
                    playVideo();

                } catch (Exception E) {

                }
            }

        });


    }

    private void playVideo() {
        //mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.treasure);
        // videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }
}

  /*  public void balanceApi() {
        final ProgressDialog progressDialog = new ProgressDialog(Top_Up_Activity.this);
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
                        Toast.makeText(Top_Up_Activity.this, "Data not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Balance> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(Top_Up_Activity.this, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }*/


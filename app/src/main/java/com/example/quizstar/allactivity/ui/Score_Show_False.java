package com.example.quizstar.allactivity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizstar.allactivity.NavigationActivity;
import com.example.quizstar.Model.Score;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Score_Show_False extends AppCompatActivity {

    private TextView tv_status;
    private SharedPrefrences shPrfs;
    String auth;
    ImageView play, undo, pause;
    final String[] Textlist = {"That was close, don’t let it get you down", "So close …… really so close", "Ahhh that one hurt","Jeez …… never mind there is always next time","Really can’t believe that","Super, super unlucky","You’ll get them next time for sure","OMG ….. that is just unlucky","You don’t have to be dead to be stiff","Come on next time for sure","Box on, next time","Look on the positives ….. it was close","Just stay away from Black cats next time","A win is just around the corner for sure","Can’t believe you didn’t win that one","Really though that one was in the bag …… unlucky","Didn’t see that coming ….. up you get and box on","Get back on the horse","Persistence always pays off","Holy Guacamole Batman …... almost won that one","Holy Switch-a-roo Batman …... almost won that one","Holy Cliffhangers Batman …... almost won that one","Holy Here We Go Again Batman …... almost won that one","Holy Disappearing Act Batman …... almost won that one","Holy double cross Batman …... almost won that one","Holy hole in a Donut Batman …... almost won that one","Holy Hallucination Batman …... almost won that one","Holy Ravioli Batman …... almost won that one","Holy wedding cake Batman …... almost won that one"};
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score__show__false);
        play = findViewById(R.id.play);
        undo = findViewById(R.id.undo);
        pause = findViewById(R.id.pause);
        tv_status = findViewById(R.id.status);
        textView = findViewById(R.id.textView);

        Random random = new Random();
        String randomText = Textlist[random.nextInt(Textlist.length)];
        textView.setText(randomText);

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Score_Show_False.this, NavigationActivity.class);
                startActivity(intent);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Score_Show_False.this, NavigationActivity.class);
                startActivity(intent);
            }
        });


        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Score_Show_False.this, NavigationActivity.class);
                startActivity(intent);
            }
        });

/*        try {
            Intent intent = getIntent();

            String statues = intent.getStringExtra("status");

        } catch (Exception e) {

        }*/


        shPrfs = SharedPrefrences.getsharedprefInstance(Score_Show_False.this);
        auth = shPrfs.getUserAuth().toString();
        hitapiScore();
    }

    public void hitapiScore() {
        final ProgressDialog progressDialog = new ProgressDialog(Score_Show_False.this);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        //Call<Delete> call = apiInterface.callDelete("
        // String a="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiZjczODFhZDc0MzEyOWViMzNmZTcwZTIyMDIxYzk1NjFjOTMwMTI1NGI0YzI5N2Q4YzM5OWI2NDZiN2EwZmU4NWY2YmUyMTFjOTQxNDg2ZmQiLCJpYXQiOjE1OTc4MTM2OTgsIm5iZiI6MTU5NzgxMzY5OCwiZXhwIjoxNjI5MzQ5Njk4LCJzdWIiOiIzNiIsInNjb3BlcyI6WyJ1c2VyIl19.B_XhKo1DYw-gNInnmVXvunrQPwWS2POPmxdXykKpOyj1BLVVgLxxiope_56U7boqKQv5YidCqNG2CYdPxRnxCp3tOl-DPiBKe_jynNkZt40SDYsWF93BisW7107_FEIWkRUlXWPamp_8UAsMs9hSXKoGyo026IZPNfKNh-r14Ve7a0aUVDMUDRfwU2zyysLckJL-lH7U4FBSDBiQW_DXjDHVZh7CrkbBLvqy3VVOHH1M1OqHUp-PDdkCe3XXCq7d06O33UGVsVPdGqQJpVlTH8TbKb7ofVszKrXo-oyZIL-sQq_n0eYkZ4TQkfgswCgxwhtC6LbxtTKWNra4CeUcOxaCF-3vP-kdAuAOJ8n0P42Vk0fBeInF-7DoBmDeIwuyxKSVz2FOYdgO3IfrZ2flJmSPq-ivj5JA0Jp28XUxDj6gAovo-CXmfy7UNYGK-WGgs1H1sXX6yxViA-XGP3rCMA0saRFPdmG5_Xy83wpWQZkuuB3KI6eRRGG5U6mlNrszjI_mzEwDxebgaKrrArKcPtzaPXe9B8CCLlYwcyxKkuuZfio0OAemZtpi8YK-2NR0ti2oWvxoXkfi0kUOEdMdjmMaC4HFfk8FvGkeY0Y-g1bB7u6kTAll1lBS-s-XVme4fbRwWyAXhtQ5iq6Ev5JD2clBiBGwIwAZEvInCTq4wAU";
        Call<Score> call = apiInterface.callScore(auth);

        call.enqueue(new Callback<Score>() {
            @Override
            public void onResponse(Call<Score> call, Response<Score> response) {
                progressDialog.dismiss();
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        Score res = response.body();
                        Score data = res.getData();
                        tv_status.setText(data.getStatus());
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Score> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Score_Show_False.this, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Score_Show_False.this, NavigationActivity.class);
        startActivity(intent);
    }
}
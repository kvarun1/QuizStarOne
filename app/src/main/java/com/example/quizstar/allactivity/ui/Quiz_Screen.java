package com.example.quizstar.allactivity.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizstar.allactivity.NavigationActivity;
import com.example.quizstar.Model.Answer_Mode;
import com.example.quizstar.Model.End;
import com.example.quizstar.Model.Question_Mode;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Quiz_Screen extends AppCompatActivity {

    ProgressBar simpleProgressBar, simpleProgressBar1, simpleProgressBarEmpty;
    int progress = 1000;
    TextView tv_question, tv_round, tv_question_No, Tv_optiona, Tv_optionab, Tv_optionac, Tv_optionad;

    int i = 100;
    private SharedPrefrences shPrfs;
    private String auth;
    boolean click = false;
    private int aa;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__screen);
        Tv_optiona = findViewById(R.id.tv_optiona);
        Tv_optionab = findViewById(R.id.tv_optionb);
        Tv_optionac = findViewById(R.id.tv_optionc);
        Tv_optionad = findViewById(R.id.tv_optiond);
        tv_round = findViewById(R.id.tv_round);
        tv_question_No = findViewById(R.id.tv_question_no);
        tv_question = findViewById(R.id.tv_question);
        simpleProgressBarEmpty = findViewById(R.id.simpleProgressBarEmpty);
        simpleProgressBarEmpty.setVisibility(View.GONE);
        tv_question.setText("");
        Tv_optiona.setText("");
        Tv_optionab.setText("");
        Tv_optionac.setText("");
        Tv_optionad.setText("");
        try {
            shPrfs = SharedPrefrences.getsharedprefInstance(this);
            auth = shPrfs.getUserAuth().toString();
            hitApiUseData(auth);
        } catch (Exception e) {

        }

        Tv_optiona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "a";

                try {
                    if (simpleProgressBar.getProgress() == 0) {
                        aa = 0;
                    } else {
                        aa = simpleProgressBar.getProgress();
                    }
                } catch (Exception e) {
                    aa = 0;
                }

                simpleProgressBar.setVisibility(View.GONE);
                simpleProgressBar1 = findViewById(R.id.simpleProgressBar1);
                simpleProgressBar1.setVisibility(View.VISIBLE);
                if (click) {
                    Toast.makeText(Quiz_Screen.this, "Click option is lock", Toast.LENGTH_SHORT).show();
                } else {
                    simpleProgressBar1.setProgress(aa);
                    Tv_optiona.setBackgroundResource(R.drawable.selected_option);
                    Tv_optiona.setTextColor(Color.WHITE);
                    hitApiAnswer(a);
                    click = true;

                }

            }
        });
        Tv_optionab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = "b";
                try {
                    if (simpleProgressBar.getProgress() == 0) {
                        aa = 0;
                    } else {
                        aa = simpleProgressBar.getProgress();

                    }
                } catch (Exception e) {
                    aa = 0;
                }
                simpleProgressBar.setVisibility(View.GONE);
                simpleProgressBar1 = findViewById(R.id.simpleProgressBar1);
                simpleProgressBar1.setVisibility(View.VISIBLE);
                if (click) {
                    Toast.makeText(Quiz_Screen.this, "Click option is lock", Toast.LENGTH_SHORT).show();

                } else {
                    Tv_optionab.setBackgroundResource(R.drawable.selected_option);
                    simpleProgressBar1.setProgress(aa);
                    Tv_optionab.setTextColor(Color.WHITE);
                    hitApiAnswer(b);
                    click = true;
                }


            }
        });

        Tv_optionac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = "c";
                try {
                    if (simpleProgressBar.getProgress() == 0) {
                        aa = 0;
                    } else {
                        aa = simpleProgressBar.getProgress();

                    }
                } catch (Exception e) {
                    aa = 0;
                }
                simpleProgressBar.setVisibility(View.GONE);
                simpleProgressBar1 = findViewById(R.id.simpleProgressBar1);
                simpleProgressBar1.setVisibility(View.VISIBLE);
                if (click) {
                    Toast.makeText(Quiz_Screen.this, "Click option is lock", Toast.LENGTH_SHORT).show();

                } else {
                    Tv_optionac.setBackgroundResource(R.drawable.selected_option);
                    simpleProgressBar1.setProgress(aa);
                    hitApiAnswer(c);
                    click = true;
                }


            }
        });
        Tv_optionad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "d";

                try {
                    if (simpleProgressBar.getProgress() == 0) {
                        aa = 0;
                    } else {
                        aa = simpleProgressBar.getProgress();

                    }
                } catch (Exception e) {
                    aa = 0;
                }
                simpleProgressBar.setVisibility(View.GONE);
                simpleProgressBar1 = findViewById(R.id.simpleProgressBar1);
                simpleProgressBar1.setVisibility(View.VISIBLE);
                if (click) {
                    Toast.makeText(Quiz_Screen.this, "Click option is lock", Toast.LENGTH_SHORT).show();

                } else {
                    Tv_optionad.setBackgroundResource(R.drawable.selected_option);
                    simpleProgressBar1.setProgress(aa);
                    hitApiAnswer(d);
                    click = true;
                }


            }
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        hitApiCallEndPause();
    }

    private void hitApiUseData(String auth) {
        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        String auth1 = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiNjlmYTQzYTk1MzQxNTVlNjMxYjVhNTliYzM5ZjZlY2E2OWQ4NTNmYTA2YTVhMWI5YjczZjAyZDY1OTNhYWY3Mzg1MWM3MGM0MmI2NzUzMjgiLCJpYXQiOjE1OTU4Mzc1NzQsIm5iZiI6MTU5NTgzNzU3NCwiZXhwIjoxNjI3MzczNTc0LCJzdWIiOiIzIiwic2NvcGVzIjpbInVzZXIiXX0.wBI4moIE0Wyt75xISTerOKw-fxXiYGWMqpQk64brCARFqhU32QarLNCmzaEqEv8yBYkTWlOXaxkvgkzbZ0_5TL-ufITmGGMpon5sT9CPzEQJsoelbWgrtvuUQ5e7WNP8xh1OTttDlftfvL2r1qIqVjbj2UD5MaVUxrj04H_cADw8m0IW8R7vwnzf5BSDBFMQlc4xFsMBOuG0PyH9_EFAul_-thuM9eszGOzLXWVfwol7FNN1Adp59Y5wA9yfxT4zxdO8xPOz5RhpeKFNJxefl_8mqOyiBpQFc3_OL7MouEnYDRIoyvritzeM_DfVEQxE2QZ5OQKWM1dtUbYNebeJytoJjhVPaB3-7CySE7yaC8pJ39RleN8rbpmqMJuqC6-Uz_YYbUNlagxeo9-FgfWB53p1i4GlCS9Sz5LN3UorrKj_yJaviTHwBE6sQfvG1ExhRGQEekT59ZPapnfhIlZkW0-Qn3piC1n5QkfieC36A26Ft_w6hiSx2AT1zrpyVLdO3aTCQo8LzqYg9iTWgxodoETK9gxqePQHvS7_CmDqU-7GtDx3H5fkPjJiyciR8ri33LGqAdxisG6KfJMFAbccRyrguJQkQvnpEzL0y0p4E9cRU5Tv7naxDX-TM51YWvkYan6IDvllgpjtG--NDc3bh1GaQo8cMV2t9XiM4UF1DT8";
        String game = "quizstar";
        Call<Question_Mode> call = apiInterface.callStartQuestion(auth, game);
        call.enqueue(new Callback<Question_Mode>() {
            @Override
            public void onResponse(Call<Question_Mode> call, Response<Question_Mode> response) {
                try {
                    if (response.isSuccessful()) {
                        simpleProgressBarEmpty.setVisibility(View.GONE);
                        click = false;
                        Tv_optiona.setBackgroundResource(R.drawable.show_option);
                        Tv_optionab.setBackgroundResource(R.drawable.show_option);
                        Tv_optionac.setBackgroundResource(R.drawable.show_option);
                        Tv_optionad.setBackgroundResource(R.drawable.show_option);
                        Tv_optiona.setTextColor(Color.BLACK);
                        Tv_optionab.setTextColor(Color.BLACK);
                        Tv_optionac.setTextColor(Color.BLACK);
                        Tv_optionad.setTextColor(Color.BLACK);
                        Question_Mode adListModel = response.body();
                        if (adListModel.getStatus().equalsIgnoreCase("success")) {
                            tv_question.setText(adListModel.getData().getQuestion().toString());
                            Tv_optiona.setText(adListModel.getData().getA());
                            Tv_optionab.setText(adListModel.getData().getB());
                            Tv_optionad.setText(adListModel.getData().getD());
                            Tv_optionac.setText(adListModel.getData().getC());
                            tv_question_No.setText(adListModel.getData().getGame());
                            tv_round.setText("Round  " + adListModel.getData().getRound());
                            final Handler progressHandler = new Handler();
                            simpleProgressBar = findViewById(R.id.simpleProgressBar);
                            simpleProgressBar.setVisibility(View.VISIBLE);
                            progress = 1000;
                            simpleProgressBar.setProgress(1000);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    while (progress > 0) {
                                        progress -= 1;
                                        progressHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                simpleProgressBar.setProgress(progress);
                                            }
                                        });
                                        try {
                                            // Sleep for 200 milliseconds.
                                            Thread.sleep(10);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }


                                    if (simpleProgressBar.getProgress() == 0) {
                                        if (click) {

                                        } else {
                                            // hitApiAnswer("");
                                   /*         simpleProgressBar.setVisibility(View.GONE);
                                            simpleProgressBarEmpty.setVisibility(View.VISIBLE);
                                            Drawable drawable = getResources().getDrawable(R.drawable.progres_null);
                                            simpleProgressBar.setProgressDrawable(drawable);*/
                                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    simpleProgressBarEmpty.setVisibility(View.VISIBLE);
                                                }
                                            });
                                            hitApiCallEnd();
                                        }
                                    }

                                }


                            }).start();


                        } else {
                            Toast.makeText(Quiz_Screen.this, "" + adListModel.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                } catch (Exception e) {
                    Toast.makeText(Quiz_Screen.this, "" + e, Toast.LENGTH_SHORT).show();
                    //  progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<Question_Mode> call, Throwable t) {
                Toast.makeText(Quiz_Screen.this, "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
                //  progressDialog.dismiss();
            }
        });

    }

    private void show() {
        try {
            Drawable drawable = getResources().getDrawable(R.drawable.progres_null);
            simpleProgressBar.setProgressDrawable(drawable);
        } catch (Exception e) {

        }

    }
    private void hitApiAnswer(String option1) {

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        final String auth1 = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiNjlmYTQzYTk1MzQxNTVlNjMxYjVhNTliYzM5ZjZlY2E2OWQ4NTNmYTA2YTVhMWI5YjczZjAyZDY1OTNhYWY3Mzg1MWM3MGM0MmI2NzUzMjgiLCJpYXQiOjE1OTU4Mzc1NzQsIm5iZiI6MTU5NTgzNzU3NCwiZXhwIjoxNjI3MzczNTc0LCJzdWIiOiIzIiwic2NvcGVzIjpbInVzZXIiXX0.wBI4moIE0Wyt75xISTerOKw-fxXiYGWMqpQk64brCARFqhU32QarLNCmzaEqEv8yBYkTWlOXaxkvgkzbZ0_5TL-ufITmGGMpon5sT9CPzEQJsoelbWgrtvuUQ5e7WNP8xh1OTttDlftfvL2r1qIqVjbj2UD5MaVUxrj04H_cADw8m0IW8R7vwnzf5BSDBFMQlc4xFsMBOuG0PyH9_EFAul_-thuM9eszGOzLXWVfwol7FNN1Adp59Y5wA9yfxT4zxdO8xPOz5RhpeKFNJxefl_8mqOyiBpQFc3_OL7MouEnYDRIoyvritzeM_DfVEQxE2QZ5OQKWM1dtUbYNebeJytoJjhVPaB3-7CySE7yaC8pJ39RleN8rbpmqMJuqC6-Uz_YYbUNlagxeo9-FgfWB53p1i4GlCS9Sz5LN3UorrKj_yJaviTHwBE6sQfvG1ExhRGQEekT59ZPapnfhIlZkW0-Qn3piC1n5QkfieC36A26Ft_w6hiSx2AT1zrpyVLdO3aTCQo8LzqYg9iTWgxodoETK9gxqePQHvS7_CmDqU-7GtDx3H5fkPjJiyciR8ri33LGqAdxisG6KfJMFAbccRyrguJQkQvnpEzL0y0p4E9cRU5Tv7naxDX-TM51YWvkYan6IDvllgpjtG--NDc3bh1GaQo8cMV2t9XiM4UF1DT8";
        Call<Answer_Mode> call = apiInterface.callanswer(auth, option1);
        call.enqueue(new Callback<Answer_Mode>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<Answer_Mode> call, Response<Answer_Mode> response) {
                try {
                    if (response.isSuccessful()) {
                        final Answer_Mode adListModel = response.body();
                        //    progressDialog.dismiss();
                        if (adListModel.getStatus().equalsIgnoreCase("success")) {
                            if (adListModel.getData().getCorrect().equalsIgnoreCase("true")) {

                                String game = "quizstar";
                                String bum = adListModel.getData().getCorrect_answer();
                                final String max_round = adListModel.getData().getMax_rounds();
                                final String round = adListModel.getData().getRound();
                                final int max_roundN = Integer.parseInt(max_round);
                                final int roundN = Integer.parseInt(round);

                                switch (bum) {
                                    case "A":
                                        Tv_optiona.setBackgroundResource(R.drawable.hole_rext_circle);
                                        Tv_optiona.setTextColor(Color.WHITE);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (roundN < max_roundN) {
                                                    hitApiUseData(auth);

                                                } else {
                                                    Intent intent = new Intent(Quiz_Screen.this, Score_Screen.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                }

                                            }
                                        }, 3000);
                                        break;
                                    case "B":

                                        Tv_optionab.setBackgroundResource(R.drawable.hole_rext_circle);
                                        Tv_optionab.setTextColor(Color.WHITE);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (roundN < max_roundN) {
                                                    //   hitApiUseData(auth);
                                                    hitApiUseData(auth);

                                                } else {
                                                    Intent intent = new Intent(Quiz_Screen.this, Score_Screen.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                }

                                            }
                                        }, 3000);
                                        break;
                                    case "C":
                                        Tv_optionac.setBackgroundResource(R.drawable.hole_rext_circle);
                                        Tv_optionac.setTextColor(Color.WHITE);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (roundN < max_roundN) {
                                                    //  hitApiUseData(auth);
                                                    hitApiUseData(auth);

                                                } else {
                                                    Intent intent = new Intent(Quiz_Screen.this, Score_Screen.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);


                                                }


                                            }
                                        }, 3000);
                                        break;
                                    case "D":
                                        Tv_optionad.setBackgroundResource(R.drawable.hole_rext_circle);
                                        Tv_optionad.setTextColor(Color.WHITE);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (roundN < max_roundN) {
                                                    // hitApiUseData(auth);
                                                    hitApiUseData(auth);

                                                } else {
                                                    Intent intent = new Intent(Quiz_Screen.this, Score_Screen.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                }
                                            }
                                        }, 3000);


                                        break;

                                    default:
                                        break;
                                }


                            } else {
                                String bum = adListModel.getData().getCorrect_answer();
                                final String max_round = adListModel.getData().getMax_rounds();
                                final String round = adListModel.getData().getRound();
                                final int max_roundN = Integer.parseInt(max_round);
                                final int roundN = Integer.parseInt(round);
                                switch (bum) {
                                    case "A":
                                        Tv_optiona.setBackgroundResource(R.drawable.hole_rext_circle);
                                        Tv_optiona.setTextColor(Color.WHITE);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //   hitapiScore();
                                                Intent intent = new Intent(Quiz_Screen.this, Score_Show_False.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);


                                            }
                                        }, 3000);
                                        break;
                                    case "B":

                                        Tv_optionab.setBackgroundResource(R.drawable.hole_rext_circle);
                                        Tv_optionab.setTextColor(Color.WHITE);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                //  hitApiCallEnd();
                                                Intent intent = new Intent(Quiz_Screen.this, Score_Show_False.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                              /*  if (roundN < max_roundN) {
                                                    hitApiUseData(auth);
                                                } else {
                                                    Intent intent = new Intent(Quiz_Screen.this, NavigationActivity.class);
                                                    startActivity(intent);
                                                }*/

                                            }
                                        }, 3000);
                                        break;
                                    case "C":
                                        Tv_optionac.setBackgroundResource(R.drawable.hole_rext_circle);
                                        Tv_optionac.setTextColor(Color.WHITE);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(Quiz_Screen.this, Score_Show_False.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                //  hitApiCallEnd();

                                          /*      if (roundN < max_roundN) {
                                                    hitApiUseData(auth);
                                                } else {
                                                    Intent intent = new Intent(Quiz_Screen.this, NavigationActivity.class);
                                                    startActivity(intent);
                                                }*/


                                            }
                                        }, 3000);
                                        break;
                                    case "D":
                                        Tv_optionad.setBackgroundResource(R.drawable.hole_rext_circle);
                                        Tv_optionad.setTextColor(Color.WHITE);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(Quiz_Screen.this, Score_Show_False.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                //  hitApiCallEnd();

                                             /*   if (roundN < max_roundN) {
                                                    hitApiUseData(auth);
                                                } else {
                                                    Intent intent = new Intent(Quiz_Screen.this, NavigationActivity.class);
                                                    startActivity(intent);
                                                }*/
                                            }
                                        }, 3000);


                                        break;

                                    default:
                                        break;
                                }
                            }
                        } else {
                            Toast.makeText(Quiz_Screen.this, "" + adListModel.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    
                       /* shPrfs.setUserName(adListModel.getData().getFirstname());
                        shPrfs.setUserLastName(adListModel.getData().getLastname());
                        shPrfs.setUseremail(adListModel.getData().getEmail());
                        shPrfs.setUserProfile(adListModel.getData().getAvatar());
                        shPrfs.setUserRegion(adListModel.getData().getRegion());*/

                        //       Toast.makeText(LoginActivity.this, "response"+shPrfs.getUserName()+shPrfs.getUseremail()+shPrfs.getUserProfile(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<Answer_Mode> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }


   /* public void hitapiScore() {
        final ProgressDialog progressDialog = new ProgressDialog(Quiz_Screen.this);
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
                        Intent intent = new Intent(Quiz_Screen.this, Score_Show_False.class);
                        intent.putExtra("status", data.getStatus());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }


                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Score> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(Quiz_Screen.this, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }*/


    private void hitApiCallEndPause() {

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        final String auth1 = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiNjlmYTQzYTk1MzQxNTVlNjMxYjVhNTliYzM5ZjZlY2E2OWQ4NTNmYTA2YTVhMWI5YjczZjAyZDY1OTNhYWY3Mzg1MWM3MGM0MmI2NzUzMjgiLCJpYXQiOjE1OTU4Mzc1NzQsIm5iZiI6MTU5NTgzNzU3NCwiZXhwIjoxNjI3MzczNTc0LCJzdWIiOiIzIiwic2NvcGVzIjpbInVzZXIiXX0.wBI4moIE0Wyt75xISTerOKw-fxXiYGWMqpQk64brCARFqhU32QarLNCmzaEqEv8yBYkTWlOXaxkvgkzbZ0_5TL-ufITmGGMpon5sT9CPzEQJsoelbWgrtvuUQ5e7WNP8xh1OTttDlftfvL2r1qIqVjbj2UD5MaVUxrj04H_cADw8m0IW8R7vwnzf5BSDBFMQlc4xFsMBOuG0PyH9_EFAul_-thuM9eszGOzLXWVfwol7FNN1Adp59Y5wA9yfxT4zxdO8xPOz5RhpeKFNJxefl_8mqOyiBpQFc3_OL7MouEnYDRIoyvritzeM_DfVEQxE2QZ5OQKWM1dtUbYNebeJytoJjhVPaB3-7CySE7yaC8pJ39RleN8rbpmqMJuqC6-Uz_YYbUNlagxeo9-FgfWB53p1i4GlCS9Sz5LN3UorrKj_yJaviTHwBE6sQfvG1ExhRGQEekT59ZPapnfhIlZkW0-Qn3piC1n5QkfieC36A26Ft_w6hiSx2AT1zrpyVLdO3aTCQo8LzqYg9iTWgxodoETK9gxqePQHvS7_CmDqU-7GtDx3H5fkPjJiyciR8ri33LGqAdxisG6KfJMFAbccRyrguJQkQvnpEzL0y0p4E9cRU5Tv7naxDX-TM51YWvkYan6IDvllgpjtG--NDc3bh1GaQo8cMV2t9XiM4UF1DT8";

        Call<End> call = apiInterface.callEnd(auth);

        call.enqueue(new Callback<End>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<End> call, Response<End> response) {
                try {
                    if (response.isSuccessful()) {
                        //    Toast.makeText(Quiz_Screen.this, "Please Wait Show Score Screen  ", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Quiz_Screen.this, NavigationActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    // Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                    //  progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<End> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
                //  progressDialog.dismiss();

            }
        });

    }


    private void hitApiCallEnd() {

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        final String auth1 = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiNjlmYTQzYTk1MzQxNTVlNjMxYjVhNTliYzM5ZjZlY2E2OWQ4NTNmYTA2YTVhMWI5YjczZjAyZDY1OTNhYWY3Mzg1MWM3MGM0MmI2NzUzMjgiLCJpYXQiOjE1OTU4Mzc1NzQsIm5iZiI6MTU5NTgzNzU3NCwiZXhwIjoxNjI3MzczNTc0LCJzdWIiOiIzIiwic2NvcGVzIjpbInVzZXIiXX0.wBI4moIE0Wyt75xISTerOKw-fxXiYGWMqpQk64brCARFqhU32QarLNCmzaEqEv8yBYkTWlOXaxkvgkzbZ0_5TL-ufITmGGMpon5sT9CPzEQJsoelbWgrtvuUQ5e7WNP8xh1OTttDlftfvL2r1qIqVjbj2UD5MaVUxrj04H_cADw8m0IW8R7vwnzf5BSDBFMQlc4xFsMBOuG0PyH9_EFAul_-thuM9eszGOzLXWVfwol7FNN1Adp59Y5wA9yfxT4zxdO8xPOz5RhpeKFNJxefl_8mqOyiBpQFc3_OL7MouEnYDRIoyvritzeM_DfVEQxE2QZ5OQKWM1dtUbYNebeJytoJjhVPaB3-7CySE7yaC8pJ39RleN8rbpmqMJuqC6-Uz_YYbUNlagxeo9-FgfWB53p1i4GlCS9Sz5LN3UorrKj_yJaviTHwBE6sQfvG1ExhRGQEekT59ZPapnfhIlZkW0-Qn3piC1n5QkfieC36A26Ft_w6hiSx2AT1zrpyVLdO3aTCQo8LzqYg9iTWgxodoETK9gxqePQHvS7_CmDqU-7GtDx3H5fkPjJiyciR8ri33LGqAdxisG6KfJMFAbccRyrguJQkQvnpEzL0y0p4E9cRU5Tv7naxDX-TM51YWvkYan6IDvllgpjtG--NDc3bh1GaQo8cMV2t9XiM4UF1DT8";

        Call<End> call = apiInterface.callEnd(auth);

        call.enqueue(new Callback<End>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<End> call, Response<End> response) {
                try {
                    if (response.isSuccessful()) {
                        //    Toast.makeText(Quiz_Screen.this, "Please Wait Show Score Screen  ", Toast.LENGTH_SHORT).show();

                   /*     Intent intent = new Intent(Quiz_Screen.this, Score_Show_False.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);*/
                    }

                } catch (Exception e) {
                    // Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                    //  progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<End> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
                //  progressDialog.dismiss();

            }
        });

    }


    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hitApiCallEndPause();
    }
}


package com.example.quizstar.AllAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.quizstar.Model.AllPojo.FeaturesPojo.DataItem;
import com.example.quizstar.Model.Sent_Challenge;
import com.example.quizstar.Model.all_rank.All_Ranking;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_AllRank extends RecyclerView.Adapter<RecyclerView_AllRank.ViewHolder> {
    Context context;
    LayoutInflater inflater;
    public List<All_Ranking> mData;

    ArrayList<DataItem> featured_arraylist = new ArrayList<>();
    private SharedPrefrences shPrfs;
    String auth;

    public RecyclerView_AllRank(Context context, List<All_Ranking> mData) {
        this.context = context;
        this.mData = mData;

    }


    @NonNull
    @Override
    public RecyclerView_AllRank.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_allrank
                        , parent, false);

        return new RecyclerView_AllRank.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView_AllRank.ViewHolder holder, final int position) {
        holder.textViewId.setText(mData.get(position).getUser().getId());
        holder.post_titile.setText(mData.get(position).getUser().getFirstname() + mData.get(position).getUser().getLastname());
        holder.post_type.setText("Won " + mData.get(position).getWon());
        holder.tv_loss.setText(mData.get(position).getLost());

        if (mData.get(position).getUser().getAvatar() != null && !mData.get(position).getUser().getAvatar().equalsIgnoreCase("")) {
            Picasso.with(context).load(R.drawable.dummy_profilex).error(R.drawable.dummy_profilex).into(holder.ci_profile);
        }

        shPrfs = SharedPrefrences.getsharedprefInstance(context);

        auth = shPrfs.getUserAuth().toString();


        holder.ci_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Sent Challenge")
                        .setMessage("Are you sure you want to Sent Challenge")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id = mData.get(position).getUser().getId();
                                loadData(auth, id);
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        holder.post_titile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Sent Challenge")
                        .setMessage("Are you sure you want to Sent Challenge")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id = mData.get(position).getUser().getId();
                                loadData(auth, id);
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, post_titile, post_type, lost;
        CircleImageView product_image;
        TextView tv_loss;
        CircleImageView ci_profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.id);
            post_titile = itemView.findViewById(R.id.post_title);
            post_type = itemView.findViewById(R.id.post_type);
            tv_loss = itemView.findViewById(R.id.loss);

            ci_profile = itemView.findViewById(R.id.cardView);


        }
    }

    private void loadData(String auth, String id) {
        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<Sent_Challenge> call = apiInterface.callSent_Challenge(auth, id, "20", "start");
        call.enqueue(new Callback<Sent_Challenge>() {
            @Override
            public void onResponse(Call<Sent_Challenge> call, Response<Sent_Challenge> response) {
                if (response.isSuccessful()) {
                    Sent_Challenge sent_challenge = response.body();
                    try {


                        if (sent_challenge.getMessage() == null) {
                            Toast.makeText(context, "Sent challenge Success full", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "" + sent_challenge.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<Sent_Challenge> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}


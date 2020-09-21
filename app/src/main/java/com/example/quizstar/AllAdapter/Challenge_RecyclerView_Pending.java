package com.example.quizstar.AllAdapter;

import android.content.Context;
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

import com.example.quizstar.Model.Ignore_Challenge;
import com.example.quizstar.Model.Sent_Data;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Challenge_RecyclerView_Pending extends RecyclerView.Adapter<Challenge_RecyclerView_Pending.ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<Sent_Data>mData;
    String auth;

    List<String> featured_arraylist;
    boolean select = true;

    public Challenge_RecyclerView_Pending(Context context, List<Sent_Data> mData) {
        this.context = context;
        this.mData =  mData;
    }




    @NonNull
    @Override
    public Challenge_RecyclerView_Pending.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.challenge_recyclerview_second
                        , parent, false);

        return new Challenge_RecyclerView_Pending.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final Challenge_RecyclerView_Pending.ViewHolder holder, final int position) {
       holder.left_status.setText(mData.get(position).getFrom().getProgress().getState());
        holder.rit_status.setText(mData.get(position).getTo().getProgress().getState());
        if (mData.get(position).getFrom().getUser().getAvatar() != null && !mData.get(position).getFrom().getUser().getAvatar().equalsIgnoreCase("")) {
            Picasso.with(context).load(mData.get(position).getFrom().getUser().getAvatar()).error(R.drawable.dummy_profilex).into(holder.left_image);
        }
        if (mData.get(position).getTo().getUser().getAvatar() != null && !mData.get(position).getTo().getUser().getAvatar().equalsIgnoreCase("")) {
            Picasso.with(context).load(mData.get(position).getTo().getUser().getAvatar()).error(R.drawable.dummy_profilex).into(holder.right_image);
        }


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView left_status,rit_status,ignore;
        CircleImageView left_image,right_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SharedPrefrences shPrfs;
            left_status =  itemView.findViewById(R.id.tv_Challenged);
            rit_status =  itemView.findViewById(R.id.tv_Challenged2);
            ignore =  itemView.findViewById(R.id.play);
            left_image =  itemView.findViewById(R.id.first_images);
            right_image =  itemView.findViewById(R.id.second_profile);
            shPrfs = SharedPrefrences.getsharedprefInstance(context);
            auth = shPrfs.getUserAuth().toString();
            ignore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hitApiIgnore(auth);
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

}


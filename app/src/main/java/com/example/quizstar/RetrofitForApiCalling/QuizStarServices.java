package com.example.quizstar.RetrofitForApiCalling;

import com.example.quizstar.Model.Answer_Mode;
import com.example.quizstar.Model.Balance;
import com.example.quizstar.Model.Delete;
import com.example.quizstar.Model.End;
import com.example.quizstar.Model.Ignore_Challenge;
import com.example.quizstar.Model.PaymetAdd;
import com.example.quizstar.Model.Rank;
import com.example.quizstar.Model.Score;
import com.example.quizstar.Model.Sent_Challenge;
import com.example.quizstar.Model.Sent_Data;
import com.example.quizstar.Model.User;
import com.example.quizstar.Model.AllPojo.Device_Token;
import com.example.quizstar.Model.AllPojo.FeaturesPojo.FeaturesResponse;
import com.example.quizstar.Model.AllPojo.GetRegionPojo.GetRegionResponse;
import com.example.quizstar.Model.AllPojo.RegisterPojo.RegisterResponse;
import com.example.quizstar.Model.Question_Mode;
import com.example.quizstar.Model.all_rank.All;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface QuizStarServices {

    @FormUrlEncoded
    @POST("/api/login")
    Call<RegisterResponse> callLoginApi(/*@Header("accept") String token, @Header("content-type") String content_type,*/ @Field("username") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/register")
    Call<RegisterResponse> callRegisterApi(/*@Header("accept") String accept, @Header("content-type") String content_type,*/
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("email") String email,
            @Field("password") String password,
            @Field("region") String region);


    @GET("/api/regions")
    Call<GetRegionResponse> callRegionApi();

    @GET("/api/features")
    Call<FeaturesResponse> callFeatures();


    @GET("/api/user")
    Call<User> callUser(@Header("content-type") String content_type, @Header("Authorization") String token);


    @GET("/api/game/start")
    Call<Question_Mode> callStartQuestion(@Header("Authorization") String token, @Query("game") String game);

    @FormUrlEncoded
    @POST("/api/user/devicetoken")
    Call<Device_Token> callDevicetoken(@Header("Authorization") String Authorization, @Field("token") String token);


    @FormUrlEncoded
    @POST("/api/game/answer")
    Call<Answer_Mode> callanswer(@Header("Authorization") String Authorization, @Field("answer") String answer);


    @DELETE("/api/user/avatar")
    Call<Delete> callDelete(@Header("Authorization") String Authorization);

    ///api/user/avatar

    @GET("/api/bank/balance")
    Call<Balance> callbalance(@Header("Authorization") String Authorization);


    @POST("/api/game/end")
    Call<End> callEnd(@Header("Authorization") String Authorization);


    @GET("api/game/rank")
    Call<Rank> callRank(@Header("Authorization") String token);


    @GET("api/game/challenge/sent")
    Call<Sent_Data> callSent(@Header("Authorization") String token);

    @GET("api/game/challenge/received")
    Call<Sent_Data> callReceived(@Header("Authorization") String token);

    @GET("/api/score")
    Call<Score> callScore(@Header("Authorization") String Authorization);

    @GET("/api/game/rank/all")
    Call<All> callRankData(@Header("Authorization") String Authorization);


    @FormUrlEncoded
    @POST("api/paywithstripe")
    Call<PaymetAdd> callPaynApil(@Field("user_id") String user_id, @Field("card_number") String card_number, @Field("exp_month") String exp_month, @Field("exp_year") String exp_year, @Field("cvv") String cvv, @Field("amount") String amount);

    @GET("api/game/challenge")
    Call<Sent_Challenge> callSent_Challenge(@Header("Authorization") String Authorization, @Query("versus") String versus, @Query("bet") String bet, @Query("action") String action);


/*    @GET("api/bank/balance")
    Call<Balance> callBakbalance(@Header("Authorization") String Authorization);*/

    @GET("api/game/challenge/return")
    Call<Ignore_Challenge> callIgnore(@Header("Authorization") String Authorization, @Query("challenge") String challenge);

    @Multipart
    @POST("/api/user/avatar")
    Call<Delete> callavatara(@Header("Authorization") String Authorization, @Part MultipartBody.Part image);


}

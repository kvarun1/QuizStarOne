package com.example.quizstar.allactivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizstar.Model.Delete;
import com.example.quizstar.Model.User;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public LinearLayout navi_quize_start, navi_damnnbank, navi_logout, navi_invite_friend;
    public TextView user_name;
    ImageView profile_edit;
    CircleImageView profile_pic;
    private SharedPrefrences shPrfs;
    private AlertDialog alertDialog = null;
    private int Image_Two = 1;
    private String photoString;
    private String auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        shPrfs = SharedPrefrences.getsharedprefInstance(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        View header = navigationView.getHeaderView(0);
        navi_quize_start = header.findViewById(R.id.quiz_start);
        navi_damnnbank = header.findViewById(R.id.damnn_bank);
        navi_invite_friend = header.findViewById(R.id.invite_fri);
        user_name = header.findViewById(R.id.user_name);
        navi_logout = header.findViewById(R.id.logout);
        profile_edit = header.findViewById(R.id.photo_edit);
        profile_pic = header.findViewById(R.id.profilePic);

        try {
            auth = shPrfs.getUserAuth().toString();
            hitApiUseData(auth);
        /*    if (shPrfs.getUserName() != null && !shPrfs.getUserName().equalsIgnoreCase("")) {
                user_name.setText(shPrfs.getUserName().toString() + " " + shPrfs.getUserLastName());
                Toast.makeText(this, "" + shPrfs.getUserName(), Toast.LENGTH_SHORT).show();
            }

            if (shPrfs.getUserProfile() != null && !shPrfs.getUserProfile().equalsIgnoreCase("")) {
                Picasso.with(this).load(shPrfs.getUserProfile()).error(R.drawable.dummy_profilex).into(profile_pic);
            }*/


        } catch (Exception e) {

        }

        profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.A);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),Image_Two);*/
                AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.layout_for_images, viewGroup, false);
                ImageView iv_image_cam = dialogView.findViewById(R.id.iv_image_cam);
                ImageView iv_image_gallery = dialogView.findViewById(R.id.iv_image_gallery);
                ImageView iv_image_cancel = dialogView.findViewById(R.id.cancel);
                ImageView iv_image_delete = dialogView.findViewById(R.id.delete);
                builder.setView(dialogView);
                alertDialog = builder.create();
                alertDialog.show();


                iv_image_gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (alertDialog != null) {
                            alertDialog.dismiss();
                        }
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);

                    }
                });


                iv_image_cam.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {

                        /*Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, Image_one);
                        }*/

                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                        } else {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, 1);
                        }
                    }
                    // Intent i5 = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    //  startActivityForResult(i5, Image_one);


                });

                iv_image_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (alertDialog != null) {
                            alertDialog.dismiss();
                        }


                    }
                });
                iv_image_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (alertDialog != null) {
                            alertDialog.dismiss();
                            hitApiDelete(auth);
                        }


                    }
                });

            }
        });

        navi_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UT = null;
                shPrfs.setUserPassword(UT);
                String UI = null;
                shPrfs.setUserEmailId(UI);
                shPrfs.setUserAuth(UI);

                // showAlertDialogBox();
                Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        navi_damnnbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(NavigationActivity.this, Api_Payment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        navi_quize_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(NavigationActivity.this, Challenges.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            auth = shPrfs.getUserAuth().toString();
            //       hitApiUseData(auth);


        } catch (Exception e) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            auth = shPrfs.getUserAuth().toString();
            //     hitApiUseData(auth);


        } catch (Exception e) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void hitApiDelete(String auth) {
        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        //Call<Delete> call = apiInterface.callDelete("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMzY3ZWI3ZjBjYmRkM2YwYzJmY2MwYTdkNzcyOTZkNTYxNmEyZGI3YzIxYzkyODE2ZjE5Y2YyNTAyZTQ1ZjQ2MGU5ZDAzOGFiMTkyNjcyZWMiLCJpYXQiOjE1OTU4NjY2NDYsIm5iZiI6MTU5NTg2NjY0NiwiZXhwIjoxNjI3NDAyNjQ2LCJzdWIiOiIzIiwic2NvcGVzIjpbImFkbWluIl19.Ek5pq6rK_5r-tCfgCTQzwpP16KHKaBfuCpirSkCXJIcOadDqLtPrwo96_f-QcrmTe4VEmgROPpI7ZIER504R0bucUno77T67y_BwBZZcmE9bcVpo-G6kiCg8V51NWScATKCKG2CtvkyqRTVXaZS0IXJwS7rN_OTzMHw700wfhIthAdanTCjOnycinlf2S84M_GRKOH6D6sQB7EUrQwJ_6mqku8BYLAsJupx2C1uczFnznnQxsp-lUDjgxphGtbAcq6gDRasQCJBAdIRBIe1o8CAyKCVQ9jKpAImZxLxZloU9w-OmfezEIJeBnX1NAbejbh2h_wNW3dlOz8opkknoy6G7rSsizvia2hJoPC-6fFzMwEdV01j9joEVhzZR1ByB8j6bj-1dZoKdLQPaEcEmSBVh4GoIUXgPX-w-JLpyVvNew6nLZTN0pXgmTwSQooGDWlanUTj2cvhn7Mv11uHGzfM_fK5Je0L0DM9RDh_qydDWPxrWPwEHoLv2MD9moeUSFTuxdmd8uIDn4iNRfueXKJo6OlSdfB4rrQYXkbbD2hVfK7-9ybLPQ8n-udDH6x_2TfTTPwvGXql61Ga9ykHv8BEBIaf6gKCCOX9lG9iUhX5Hk6bSh2C4RnqS7RolzF95DHufuDHUCkKtCj-pkNlBHkfUInVUjscjC66GJc-u6oI");
        Call<Delete> call = apiInterface.callDelete(auth);

        //  final ProgressDialog dialog = AppProgress.showProgress(activity);
        call.enqueue(new Callback<Delete>() {
            @Override
            public void onResponse(Call<Delete> call, Response<Delete> response) {
                try {
                    //   Toast.makeText(getApplicationContext(), "" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        Delete adListModel = response.body();
                        if (adListModel.getStatus().equalsIgnoreCase("success")) {
                            Toast.makeText(NavigationActivity.this, "Delete Images " + adListModel.getData().getMessage(), Toast.LENGTH_SHORT).show();
                            Bitmap a = null;
                            profile_pic.setImageResource(R.drawable.dummy_profilex);
                        } else {
                            Toast.makeText(NavigationActivity.this, "Delete Images " + adListModel.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Delete> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Image_Two) {
                Bitmap photo1 = (Bitmap) data.getExtras().get("data");
               /* Uri uri = data.getData();
                profile_pic.setImageURI(uri);*/
                alertDialog.dismiss();
                if (photo1 != null) {
                    profile_pic.setImageBitmap(photo1);
                    ByteArrayOutputStream blob = new ByteArrayOutputStream();
                    photo1.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob);
                    byte[] bitmapdata = blob.toByteArray();
                    hitApiAvatar(bitmapdata);


                }
            } else if (requestCode == 2) {
                if (data.getData() != null && data.getClipData() == null) {
                    Log.w("CLIPDATA==null", "START");

                    try {
                        InputStream is = getContentResolver().openInputStream(data.getData());
                        hitApiAvatar(getBytes(is));
                        Uri filePath;
                        filePath = data.getData();
                        profile_pic.setImageURI(filePath);
                        alertDialog.dismiss();
                    } catch (Exception e) {

                    }



                 /*   Log.d("vishal", filePath.toString());
                    if (getimage(filePath) != null) {
                        photoString = getimage(filePath);

                        //  hitApiAvatar1(photoString);

                        //imageurllist.add(getimage(filePath));
                    }*/

                }

            }

        }

    }


    private void hitApiAvatar(byte[] a) {

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), a);

        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", "avatar.jpg", requestFile);
        //Call<Delete> call = apiInterface.callavatara("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMzY3ZWI3ZjBjYmRkM2YwYzJmY2MwYTdkNzcyOTZkNTYxNmEyZGI3YzIxYzkyODE2ZjE5Y2YyNTAyZTQ1ZjQ2MGU5ZDAzOGFiMTkyNjcyZWMiLCJpYXQiOjE1OTU4NjY2NDYsIm5iZiI6MTU5NTg2NjY0NiwiZXhwIjoxNjI3NDAyNjQ2LCJzdWIiOiIzIiwic2NvcGVzIjpbImFkbWluIl19.Ek5pq6rK_5r-tCfgCTQzwpP16KHKaBfuCpirSkCXJIcOadDqLtPrwo96_f-QcrmTe4VEmgROPpI7ZIER504R0bucUno77T67y_BwBZZcmE9bcVpo-G6kiCg8V51NWScATKCKG2CtvkyqRTVXaZS0IXJwS7rN_OTzMHw700wfhIthAdanTCjOnycinlf2S84M_GRKOH6D6sQB7EUrQwJ_6mqku8BYLAsJupx2C1uczFnznnQxsp-lUDjgxphGtbAcq6gDRasQCJBAdIRBIe1o8CAyKCVQ9jKpAImZxLxZloU9w-OmfezEIJeBnX1NAbejbh2h_wNW3dlOz8opkknoy6G7rSsizvia2hJoPC-6fFzMwEdV01j9joEVhzZR1ByB8j6bj-1dZoKdLQPaEcEmSBVh4GoIUXgPX-w-JLpyVvNew6nLZTN0pXgmTwSQooGDWlanUTj2cvhn7Mv11uHGzfM_fK5Je0L0DM9RDh_qydDWPxrWPwEHoLv2MD9moeUSFTuxdmd8uIDn4iNRfueXKJo6OlSdfB4rrQYXkbbD2hVfK7-9ybLPQ8n-udDH6x_2TfTTPwvGXql61Ga9ykHv8BEBIaf6gKCCOX9lG9iUhX5Hk6bSh2C4RnqS7RolzF95DHufuDHUCkKtCj-pkNlBHkfUInVUjscjC66GJc-u6oI", body);

        Call<Delete> call = apiInterface.callavatara(auth, body);
        //  final ProgressDialog dialog = AppProgress.showProgress(activity);
        call.enqueue(new Callback<Delete>() {
            @Override
            public void onResponse(Call<Delete> call, Response<Delete> response) {
                try {
                    //   Toast.makeText(getApplicationContext(), "" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        Delete adListModel = response.body();
                        Toast.makeText(NavigationActivity.this, "" + adListModel.getStatus(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Delete> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }


    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }


    private void showAlertDialogBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you Want to  Logout?? ");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String UT = null;
                        shPrfs.setUserPassword(UT);
                        String UI = null;
                        shPrfs.setUserEmailId(UI);
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void hitApiUseData(String auth) {

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<User> call = apiInterface.callUser("multipart/form-data", auth);
        //  final ProgressDialog dialog = AppProgress.showProgress(activity);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    //   Toast.makeText(getApplicationContext(), "" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        User adListModel = response.body();
                        user_name.setText(adListModel.getData().getFirstname() + " " + adListModel.getData().getLastname());

                        if (adListModel.getData().getAvatar() != null && !adListModel.getData().getAvatar().equalsIgnoreCase("")) {
                            Picasso.with(NavigationActivity.this).load(shPrfs.getUserProfile()).error(R.drawable.dummy_profilex).into(profile_pic);
                        }


                        //    Toast.makeText(LoginActivity.this, "response"+shPrfs.getUserName()+shPrfs.getUseremail()+shPrfs.getUserProfile(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }



/*        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setTitle("Alert !")
                .setMessage("Do you Want to  Logout?? ")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String UT = null;
                        shPrfs.setUserPassword(UT);
                        String UI = null;
                        shPrfs.setUserEmailId(UI);
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();*/


    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 66, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

 /*   private String getimage(Uri filePath) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getApplicationContext()).getContentResolver(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap lastBitmap = null;
        lastBitmap = bitmap;
        //encoding image to string
        return getStringImage(lastBitmap);
    }*/

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);

    }
}
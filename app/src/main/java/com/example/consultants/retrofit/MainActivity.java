package com.example.consultants.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.consultants.retrofit.api.RandomAPI;
import com.example.consultants.retrofit.models.UserResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://randomuser.me/";

    private static final String TAG = "MainActivity_TAG";

private TextView textView;
private ImageView imageView;


    private Retrofit client;

    private RandomAPI randomAPI;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

textView=findViewById(R.id.textView);
imageView=findViewById(R.id.imageView);

        Log.d(TAG, "onCreate: Thread "

                + Thread.currentThread().getName());



        client = prepareRetrofitClient();

        randomAPI = client.create(RandomAPI.class);



        randomAPI.getRandomUser().enqueue(new Callback<UserResponse>() {

            @Override

            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.isSuccessful()) {

                    UserResponse randomUser = response.body();

                    if(randomUser!=null) {

                        Log.d(TAG, "onResponse: seed " +

                                randomUser.getInfo().getSeed());

                        Log.d(TAG, "onResponse: Thread " +

                                Thread.currentThread().getName());

                        textView.setText(

                                randomUser.getResults().get(0).getName().getFirst()

                                        + " " +

                                        randomUser.getResults().get(0).getName().getLast()

                        );


                        // gets the image and with picasso transforms it from url into an image

                        Picasso.get().load(

                                randomUser.getResults().get(0).getPicture().getMedium()).into(imageView);

                    }

                }

            }



            @Override

            public void onFailure(Call<UserResponse> call, Throwable t) {



            }

        });

    }



    private Retrofit prepareRetrofitClient() {

        Retrofit client = new Retrofit.Builder()

                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())

                .build();

        return client;

    }



}
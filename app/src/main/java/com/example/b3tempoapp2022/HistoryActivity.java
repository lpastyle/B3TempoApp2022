package com.example.b3tempoapp2022;

import static com.example.b3tempoapp2022.MainActivity.edfApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    private static final String LOG_TAG = HistoryActivity.class.getSimpleName();

    // Data model
    List<TempoDate> tempoDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (edfApi != null) {
            // Create call to getTempoDaysLeft
            Call<TempoHistory> call = edfApi.getTempoHistory("2021", "2022");

            call.enqueue(new Callback<TempoHistory>() {
                @Override
                public void onResponse(@NonNull Call<TempoHistory> call, @NonNull Response<TempoHistory> response) {
                    tempoDates.clear();
                    if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                        tempoDates.addAll(response.body().getTempoDates());
                        Log.d(LOG_TAG,"nb elements = " + tempoDates.size());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<TempoHistory> call, @NonNull Throwable t) {

                }
            });

        }



    }
}
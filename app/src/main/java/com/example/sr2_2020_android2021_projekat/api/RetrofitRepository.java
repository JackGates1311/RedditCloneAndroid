package com.example.sr2_2020_android2021_projekat.api;

import android.view.View;
import androidx.annotation.NonNull;
import com.example.sr2_2020_android2021_projekat.tools.RetrofitTools;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitRepository<T> {

    T responseData;

    public void sendRequest(Call<T> data, View view, Runnable afterResponseCode) {

        Callback<T> callback = new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, Response<T> response) {
                if(!response.isSuccessful()) {

                    RetrofitTools.showResponseErrorMessage(response.code(), view);

                    return;
                }

                setResponseData(response.body());

                afterResponseCode.run();
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                RetrofitTools.showConnectionErrorMessage(t, view);
            }
        };

        data.enqueue(callback);

    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }
}

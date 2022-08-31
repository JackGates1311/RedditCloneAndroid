package com.example.sr2_2020_android2021_projekat.api;

import android.view.View;
import androidx.annotation.NonNull;
import com.example.sr2_2020_android2021_projekat.tools.RetrofitTools;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrudService <T> {

    List<T> responseData;

    public void getData(Call<List<T>> data, View view, Runnable afterResponse){

        Callback<List<T>> listCallback = new Callback<List<T>>() {

            @Override
            public void onResponse(@NonNull Call<List<T>> call, Response<List<T>> response) {
                if(!response.isSuccessful()) {

                    RetrofitTools.showResponseErrorMessage(response.code(), view);

                    return;
                }

                setResponseData(response.body());

                afterResponse.run();

            }

            @Override
            public void onFailure(@NonNull Call<List<T>> call, @NonNull Throwable t) {
                RetrofitTools.showConnectionErrorMessage(t, view);
            }
        };

        data.enqueue(listCallback);
    }

    private void postData(){}

    private void updateData(){}

    private void deleteData(){}

    public List<T> getResponseData(){
        return this.responseData;
    }

    public void setResponseData(List<T> responseBody) {
        this.responseData = responseBody;
    }

}

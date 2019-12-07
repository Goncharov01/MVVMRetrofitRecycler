package com.example.mvvmretrofitrecycler;

import java.util.ArrayList;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TaskApi {

    @GET("api/task")
    Observable<ArrayList<TaskModel>> getTask();


}

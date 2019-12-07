package com.example.mvvmretrofitrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Disposable disposable = null;

    public RecyclerView recyclerView;

    private TaskApi taskApiServer() {
      return   RetrofitClient.getApi();
    }

    public List<TaskModel> taskModels = new ArrayList<>();

    Retrofit retrofit;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    initRecyclerView();

        disposable = taskApiServer().getTask()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> recyclerAdapter.onChange(list));
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(taskModels,this);
        recyclerView.setAdapter(recyclerAdapter);
    }


}

package com.parisa00100.retrofitwithrx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.parisa00100.retrofitwithrx.Adapter.MyAdapter;
import com.parisa00100.retrofitwithrx.Model.MyModel;
import com.parisa00100.retrofitwithrx.Retrofit.RetrofitClient;
import com.parisa00100.retrofitwithrx.Retrofit.Services;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Services mServices;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Retrofit retrofit = RetrofitClient.getInstance();
        mServices = retrofit.create(Services.class);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        compositeDisposable.add((Disposable) mServices.getModel().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<MyModel>>() {
                    @Override
                    public void accept(List<MyModel> myModels) throws Exception {


                        showData(myModels);
                    }
                }));


    }

    private void showData(List<MyModel> myModels) {

        MyAdapter customAdapter = new MyAdapter(myModels, MainActivity.this);

        recyclerView.setAdapter(customAdapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}

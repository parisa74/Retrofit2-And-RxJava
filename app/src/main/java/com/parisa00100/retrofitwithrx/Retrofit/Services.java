package com.parisa00100.retrofitwithrx.Retrofit;



import com.parisa00100.retrofitwithrx.Model.MyModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Services {

    @GET("posts")
    Observable<List<MyModel>> getModel();

}

package com.ebartmedia.Retrofit;

import com.ebartmedia.Model.Categories;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface IMyAPI {

//    @GET("api/categories")
//    Observable<List<Categories>> getCategories();


    @GET("api/getinfo")
    Observable<List<Categories>> getCategories();

}


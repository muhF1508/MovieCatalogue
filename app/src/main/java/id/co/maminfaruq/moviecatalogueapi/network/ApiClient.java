package id.co.maminfaruq.moviecatalogueapi.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;


    //Membuat method return untuk mendapatkan retrofit yang sudah berisi base url
    public static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}

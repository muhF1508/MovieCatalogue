package id.co.maminfaruq.moviecatalogueapi;

import id.co.maminfaruq.moviecatalogueapi.database.MovieLocalDataSource;
import id.co.maminfaruq.moviecatalogueapi.database.MovieUpLocalDataSource;
import id.co.maminfaruq.moviecatalogueapi.remote.MovieRemoteDataSource;
import id.co.maminfaruq.moviecatalogueapi.remote.MovieRepository;
import id.co.maminfaruq.moviecatalogueapi.remote.remoteup.UpRemoteDataSource;
import id.co.maminfaruq.moviecatalogueapi.remote.remoteup.UpRepository;

public class Injection {
    public static MovieRepository provideRepository(){
        return new MovieRepository(new MovieRemoteDataSource(),new MovieLocalDataSource());
    }

    public static UpRepository provideDataRepository(){
        return new UpRepository(new UpRemoteDataSource(),new MovieUpLocalDataSource());
    }
}

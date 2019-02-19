package id.co.maminfaruq.moviecatalogueapi.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;
import id.co.maminfaruq.moviecatalogueapi.model2.ResultsItem;

@Dao
public interface MovieDao {

    @Insert
    void insert(List<ResultsItemNow> resultsItemNowList);

    @Query(" SELECT * FROM movie ORDER BY original_title ASC ")
    List<ResultsItemNow>select();

    @Delete
    void delete(ResultsItemNow resultsItemNow);

    @Update
    void update(ResultsItemNow resultsItemNow);

    @Insert
    void insertUp(List<ResultsItem> resultsItems);

    @Query(" SELECT * FROM upComing ORDER BY original_title ASC ")
    List<ResultsItem>selectUp();

    @Delete
    void deleteUp(ResultsItem resultsItem);

    @Update
    void updateUp(ResultsItem resultsItem);
}

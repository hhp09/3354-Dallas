package com.example.newsappv3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface SavedArticleDao {
    //Todo? not sure if needed
    @Query("SELECT * FROM savedArticle")
    List<SavedArticle> getAll();


    @Insert
    void insert(SavedArticle savedArticle);

    @Delete
    void delete(SavedArticle savedArticle);

}

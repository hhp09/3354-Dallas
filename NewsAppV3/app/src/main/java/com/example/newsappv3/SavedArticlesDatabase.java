package com.example.newsappv3;

import androidx.room.RoomDatabase;
import androidx.room.Database;

@Database(entities = {SavedArticle.class}, version=1)
public abstract class SavedArticlesDatabase extends RoomDatabase {
    public abstract SavedArticleDao getSavedArticleDao();
}




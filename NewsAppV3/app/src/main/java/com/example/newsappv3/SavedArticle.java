package com.example.newsappv3;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SavedArticle {
    /* Saving author, source, description and content */

    @PrimaryKey(autoGenerate = true)
    private int savedId;

    @ColumnInfo(name = "savedArticleContent")
    private String author;
    private String source;
    private String description;
    private String content;

    public SavedArticle(int savedID, String author, String source, String description, String content){
        this.savedId = savedID;
        this.author = author;
        this.source = source;
        this.description = description;
        this.content = content;
    }

    /*Getters and Setters*/
    public int getSavedId(){
        return savedId;
    }
    public void setSavedId(int savedId){
        this.savedId = savedId;
    }

    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    public String getSource(){
        return source;
    }
    public void setSource(String source){
        this.source = source;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    @Override
    public boolean equals(Object art) {
        if (this == art)
            return true;
        if (!(art instanceof SavedArticle ))
            return false;

        SavedArticle savedArticle = (SavedArticle) art;

        if (savedId != savedArticle.savedId)
            return false;

        return source != null ? source.equals(savedArticle.source) : savedArticle.source == null;
    }

    @Override
    public int hashCode() {
        int result = savedId;
        result = 31 * result + ( source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        //todo make this better
        return "Place holder for information";
    }

}
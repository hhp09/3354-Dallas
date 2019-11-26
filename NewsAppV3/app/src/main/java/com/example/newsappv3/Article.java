package com.example.newsappv3;
import android.os.Environment;
import android.content.Context;
import android.util.Log;
import java.io.*;
import java.util.List;
import java.nio.file.*;
import java.util.stream.Collectors;

//This will be the article object for saving articles
public class Article {
    public static String author;
    public static String url;
    public static String description;
    public static String content;

    final static String fileName = "savedArticles.txt";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/storedData/" ;
    final static String TAG = Article.class.getName();

    public void setInfo(String author, String url, String description, String content){
        this.author = author;
        this.url = url;
        this.description = description;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void printAuthor(){
        System.out.println(author);
    }

    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void printUrl(){
        System.out.println(url);
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void printDescription(){
        System.out.println(description);
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void printContent(){
        System.out.println(content);
    }

    private static String getAll() {
        return author +"\t"+ url +"\t"+ description +"\t"+ content +"\n";
    }

    public static String ReadFile(Context context){
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return line;
    }

    public static boolean saveToFile(){
        try {
            new File(path  ).mkdir();
            File file = new File(path+ fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write((getAll() + System.getProperty("line.separator")).getBytes());

            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;
    }

    public void deleteArticle()
    {
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                if (line == getAll())
                    line = null;
            }
            fileInputStream.close();
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

}

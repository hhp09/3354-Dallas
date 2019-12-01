package com.example.newsappv3;
import android.os.Environment;
import android.content.Context;
import android.util.Log;
import java.io.*;

//import android.content.pm.PackageManager;
//import java.util.List;
//import java.nio.file.*;
//import java.util.stream.Collectors;

//This will be the article object for saving articles
public class Article {
    public static String author;
    public static String url;
    public static String description;
    public static String content;

    final static String fileName = "savedArticles.txt";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath();
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

    public static String showArticles(Context context){
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
                System.out.println("before create new file");
                file.createNewFile();
                System.out.println("After");
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

    public boolean savedArticles(){
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("Try, everything open, looking at saved");
            while ( (line = bufferedReader.readLine()) != null )
            {
                System.out.println(line);
                fileInputStream.close();
                line = stringBuilder.toString();
                bufferedReader.close();
                return true;
            }
            fileInputStream.close();
            line = stringBuilder.toString();
            bufferedReader.close();
            System.out.println("Didnt find anything");
            return false;
        }
        catch(Exception e){
            System.out.println("Catch");
            return false;
        }
    }

}

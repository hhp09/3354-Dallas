package com.example.newsappv3;
import android.os.Environment;
import android.content.Context;
import android.util.Log;
import java.io.*;

//This will be the article object for saving articles - Mary "Abby" Strebel
public class Article {
    //Bits of information to be saved
    public static String author;
    public static String url;
    public static String description;
    public static String content;

    //Information needed to save the files
    final static String fileName = "savedArticles.txt";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    final static String TAG = Article.class.getName();

    //Setter for all the information
    public void setInfo(String author, String url, String description, String content){
        this.author = author;
        this.url = url;
        this.description = description;
        this.content = content;
    }

    //Unused setters getters and printers for all the info stored
    /*
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void printAuthor(){
        System.out.println(author);
    }
*/
    public String getUrl(){
        return url;
    }
    /*
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
     */

    private static String getAll() {
        return author +"\t"+ url +"\t"+ description +"\t"+ content +"\n";
    }

    //Reads Articles from file, then returns a string of all the saved information
    public static String showArticles(Context context){
        String line = null;
        //Trys to read the file
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //When the line being read is not empty, add the line to the string builder
            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }
            //Done reading. stop reading, properly add all the text to Line and stop using buffered reader
            fileInputStream.close();
            line = stringBuilder.toString();
            bufferedReader.close();
        }
        //Dealing with exceptions
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return line; //Should be all the saved info
    }

    //Saves Articles to file
    public static boolean saveToFile(){
        try {
            //Sets correct path
            new File(path  ).mkdir();
            File file = new File(path+ fileName);
            //Makes a new file, if needed
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            //Should save Articles
            fileOutputStream.write((getAll() + System.getProperty("line.separator")).getBytes());
            return true; //Saved!
            //Handles exceptions
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;//Didn't Save
    }

    public void deleteArticle()
    {
        String line = null;
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null )
            {
                if (line == getAll())
                    line = null;//Deletes line if its the article to be deleted
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

    public void savedArticles(){
        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ( (line = bufferedReader.readLine()) != null )
            {
                //todo below
                System.out.println(line); //Would change this to setting values to display all articles, similar to the main page
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
    }

}

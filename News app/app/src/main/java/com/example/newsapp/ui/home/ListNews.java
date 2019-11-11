import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;


// this implements methods that handle the display of articles' author, publishing date, image, title, and a description.
//It reads the layout from the xml file, directs the information read from the URL, and then displays it
class ListNews extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListNews(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
    }
    //following 3 methods are fetchers
    public int getCount(){
        return data.size();
    }

    public Object getItem(int postiion) {
        return postition;
    }

    public long getItemId(int postiion) {
        return position;

    }

    public View getView (int position. View convertView, ViewGroup parent) {
        ListNewsViewHolder newsHold = null;

        if (convertView == null){
            newsHold = new ListNewsViewHolder();
            //read the xml file as well as parent to retrieve the layout
            convertView = LayoutInflater.from(activity).inflate(R.layout.list_row, parent, false);
            //read the aspects newsHold needs and set them
            newsHold.galleryImage = (ImageView) convertView.findViewById(R.id.galleryImage);
            newsHold.author = (TextView) convertView.findViewById(R.id.author);
            newsHold.title = (TextView) convertView.findViewById(R.id.title);
            newsHold.sdetails = (TextView) convertView.findViewById(R.id.sdetails);
            newsHold.time = (TextView) convertView.findViewById(r.id.time);
            convertView.setTag(newsHold);


        } else{
            newsHold= (ListNewsViewHolder) convertView.getTag();
        }
        //set the position of all elements in newsHold
        newsHold.galleryImage.setId(position);
        newsHold.author.setId(position);
        newsHold.title.setId(position);
        newsHold.sdetails.setId(position);
        newsHold.time.setId(position);

        HashMap< String, String> song = new HashMap<String, String>();
        song = data.get(position);

        try{
            //Attempt to display the text gotten from convertView
            newsHold.author.setText(song.get(MainActivity.KEY_AUTHOR));
            newsHold.title.setText(song.get(MainActivity.KEY_TITLE));
            newsHold.time.setText(song.get(MainActivity.KEY_PUBLISHEDAT));
            newsHold.sdetails.setText(song.get(MainActivity.KEY_DESCRIPTION));

            if(song.get(MainActivity.KEY_URLTOIMAGE).toString().length() < 5){
                newsHold.galleryImage.setVisibility(View.GONE);
            } else{
                Picasso.get()
                        .load(song.get(MainActivity.KEY_URLTOIMAGE))
                        .resize(300.200)
                        .centerCrop()
                        .into(newsHold.galleryImage);
            }

        } catch(Exception e){}
        return convertView;
    }
}
//define the holder of all the information from the website
class ListNewsViewHolder{
    ImageView galleryImage;
    TextView author, title, sdetails, time;
}
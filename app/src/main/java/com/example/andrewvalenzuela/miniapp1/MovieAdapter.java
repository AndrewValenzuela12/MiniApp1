package com.example.andrewvalenzuela.miniapp1;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;



import java.util.ArrayList;

/**
 * Created by AndrewValenzuela on 2/13/18.
 */

//adapter is needed when you want to do any sort of list or table view
//gets data and decides where to display in the activity

public class MovieAdapter extends BaseAdapter {

    // anytime you use BaseAdapter you have to Override these methods
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    //constructor
    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList) {

        //initialize instance variables
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // methods
    // a list of methods we need to override

    // gives you the number of movies in the data source
    @Override
    public int getCount() { return mMovieList.size();}

    // returns the item at a specific position in the data source
    @Override
    public Object getItem(int position) {return mMovieList.get(position);}

    // returns the row ID associated with the specific position in the list
    @Override
    public long getItemId(int position) {return position;}

    // to display only three character names
        // mMovieList.get(movie.getItemId)

    //returns a view
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyId again
        if (convertView == null) {
            // inflate
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);

            // add the views to the holder
            holder = new ViewHolder();

            // views
            holder.thumbnailImageView = convertView.findViewById(R.id.poster_imageview);
            holder.titleTextView = convertView.findViewById(R.id.title_text_view);
            holder.descriptionTextView = convertView.findViewById(R.id.description_text_view);
            holder.characterTextView = convertView.findViewById(R.id.character_text_view);
            holder.HasSeenTextView = convertView.findViewById(R.id.has_seen_text_view);

            // add the holder to the view
            convertView.setTag(holder);
        }

        else{
            // get the view holder from the convertView
            holder = (ViewHolder) convertView.getTag();
        }

        // get relevant subview of the row view
        ImageView thumbnailImageView = holder.thumbnailImageView;
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView characterTextView = holder.characterTextView;
        TextView HasSeenTextView = holder.HasSeenTextView;

        // get corresponding movie for each row
        Movie movie = (Movie)getItem(position);

        //update the row view's textviews and imageview to display the information

        // imageView -- use Picasso library to load image from the image url
        Picasso.with(mContext).load(movie.poster).into(thumbnailImageView);

        // titleTextView
            titleTextView.setText(movie.title);
            titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent ));
            titleTextView.setTextSize(20);

        // descriptionTextView
            descriptionTextView.setText(movie.description);
            descriptionTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
            descriptionTextView.setTextSize(9);


        // characterTextView
            characterTextView.setText(movie.MainCharacters);
            characterTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
            characterTextView.setTextSize(15);


        return convertView;
    }

    // viewHolder
    // is used to customize what you want to put into the view
    // it depends on the layout design of your row
    // this will be a private static class you have to define

    private static class ViewHolder{
        public ImageView thumbnailImageView;
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView characterTextView;
        public TextView HasSeenTextView;
    }

}

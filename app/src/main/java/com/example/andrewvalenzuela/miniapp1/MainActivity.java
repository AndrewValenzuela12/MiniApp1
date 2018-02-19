package com.example.andrewvalenzuela.miniapp1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;
    private Movie selectedMovie;
    private View selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        // get data to display
        final ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json", this);

        // create the adapter
        MovieAdapter adapter = new MovieAdapter(this, movieList);

        // find the listview in the layout
        // set the adapter to listview

        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);

        // Each Row should be clickable
        // when the row is clicked the intent is created and sent
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedMovie = movieList.get(position);
                selectedItem = mListView.getChildAt(position - mListView.getFirstVisiblePosition());

                // create the intent package
                // add all the information needed for detail page
                // startActivity with that intent

                //explicit from
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);

                // put title, description, poster image

                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("description", selectedMovie.description );
                detailIntent.putExtra("poster", selectedMovie.poster);

                startActivityForResult(detailIntent, 1);
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK) { // SECOND ACTIVITY IS SENDING DATA
               String result = data.getStringExtra("radioButtonResult");

               // we need to see which one has been selected and then display
                // the different strings in the textView

                TextView texttoDisplay = (TextView) selectedItem.findViewById(R.id.has_seen_text_view);

                if (result.equals("")) {
                    texttoDisplay.setText("Has Seen?");
                }

                else {
                    if (result.equals("Already Seen")) {
                        texttoDisplay.setText(result);
                        texttoDisplay.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                    }

                     else if (texttoDisplay.equals("Want to See")) {
                    texttoDisplay.setText(result);
                    texttoDisplay.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                    }

                    else {
                        texttoDisplay.setText(result);
                        texttoDisplay.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                    }
                    }




            }
        }
    }
}

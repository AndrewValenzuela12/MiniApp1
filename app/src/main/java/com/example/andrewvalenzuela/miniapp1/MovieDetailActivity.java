package com.example.andrewvalenzuela.miniapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

/**
 * Created by AndrewValenzuela on 2/13/18.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private Context mContext;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private Button submitButton;
    private RadioGroup myRadioGroup;
    private ImageView posterImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        mContext = this;
        titleTextView = findViewById(R.id.movie_activity_title);
        descriptionTextView = findViewById(R.id.description_new_activity);
        submitButton = findViewById(R.id.button);
        myRadioGroup = findViewById(R.id.radioGroup);
        posterImageView = findViewById(R.id.poster_new_activity);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                // construct intent
                Intent buttonchoiceIntent = new Intent();

                String selectedbuttonname = "";

                int selectedID = myRadioGroup.getCheckedRadioButtonId();

                if (selectedID != -1) {
                    RadioButton selectedbutton = findViewById(selectedID);
                    selectedbuttonname = selectedbutton.getText().toString();
                }

                buttonchoiceIntent.putExtra("radioButtonResult", selectedbuttonname);


                // send back to the activity
                setResult(RESULT_OK, buttonchoiceIntent);
                finish();
            }
        });

        // change welcomeText to display: username + welcome back!

        String title = this.getIntent().getExtras().getString("title");
        titleTextView.setText(title);

        String description = this.getIntent().getExtras().getString("description");
        descriptionTextView.setText(description);

        String poster = this.getIntent().getExtras().getString("poster");

        Picasso.with(mContext).load(poster).into(posterImageView);

    }
}

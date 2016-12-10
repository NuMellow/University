package com.nodeflux.university;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView uniList;
    private DatabaseReference uniDb;

    private String courseSelect; //stores the universityname from the previous page. To be used to filter the db
                                 //Holds the name of the university



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courseSelect = "Natural Resource Management NUST";

        uniDb = FirebaseDatabase.getInstance().getReference().child("University");

        uniList = (RecyclerView) findViewById(R.id.uni_list);
        uniList.setHasFixedSize(true);
        uniList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<University, UniViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<University, UniViewHolder>(
                University.class,
                R.layout.university_row,
                UniViewHolder.class,
                uniDb //.orderByChild("NameUniIndex").equalTo(courseSelect)
        ) {
            @Override
            protected void populateViewHolder(UniViewHolder viewHolder, University model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setCity(model.getCity());
                viewHolder.setLink(model.getUrl());
                viewHolder.setLogo(getApplicationContext(), model.getLogo());
            }
        };

        uniList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class UniViewHolder extends RecyclerView.ViewHolder
    {
        View myView;

        public UniViewHolder(View itemView)
        {
            super(itemView);

            myView = itemView;
        }

        public void setName(String name)
        {
            TextView uniName = (TextView) myView.findViewById(R.id.textUniversityName);
            uniName.setText(name);
        }

        public  void setCity(String city)
        {
            TextView cityName = (TextView) myView.findViewById(R.id.textUniCity);
            cityName.setText(city);
        }

        public  void setLink(String link)
        {
            TextView uniLink = (TextView) myView.findViewById(R.id.textUniLink);
            uniLink.setText(link);
        }

        public  void setLogo(Context ctx, String image)
        {
            ImageView uniLogo = (ImageView) myView.findViewById(R.id.imageUniLogo);
            Picasso.with(ctx).load(image).into(uniLogo);
        }
    }
}

package com.example.sih_r2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class ResolvedActivity extends AppCompatActivity {

    TextView titleView;
    TextView descView;

    ImageView imageView;

    TextView comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolved);

        titleView = (TextView) findViewById(R.id.titleText);

        descView = (TextView)findViewById(R.id.descText);

        imageView = (ImageView) findViewById(R.id.reso_image);

        comments = (TextView) findViewById(R.id.comments);


        Bundle extras = getIntent().getExtras();

        titleView.setText(extras.getString("TITLE"));
        descView.setText(extras.getString("DESC"));
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(extras.getByteArray("URL"), 0, extras.getByteArray("URL").length));

        DBHelper helper = new DBHelper(this);

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM IssueTable", null);


        if (cursor.moveToFirst()){
            do {
                if (Objects.equals(cursor.getString(1), extras.getString("TITLE"))) {
                    comments.setText(cursor.getString(4));
                }
            }while(cursor.moveToNext());
        }

        cursor.close();


    }
}
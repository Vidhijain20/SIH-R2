package com.example.sih_r2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResolutionActivity extends AppCompatActivity {

    TextView titleView;
    TextView descView;

    ImageView imageView;

    TextView comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolution);

        titleView = (TextView) findViewById(R.id.titleText);

        descView = (TextView)findViewById(R.id.descText);

        imageView = (ImageView) findViewById(R.id.reso_image);

        comments = (TextView) findViewById(R.id.comments);

        Button submitButton = (Button)findViewById(R.id.submit_button);

        Bundle extras = getIntent().getExtras();

        titleView.setText(extras.getString("TITLE"));
        descView.setText(extras.getString("DESC"));
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(extras.getByteArray("URL"), 0, extras.getByteArray("URL").length));

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper helper = new DBHelper(getApplicationContext());

                SQLiteDatabase DB = helper.getWritableDatabase();

                DB.execSQL("UPDATE IssueTable set Resolution = '" + comments.getText().toString() + "' where Title = '"+extras.getString("TITLE")+"' ");

                DB.execSQL("UPDATE IssueTable set Status = '1' where Title = '"+extras.getString("TITLE")+"'");

                DB.close();

                Intent intent = new Intent(getApplicationContext(), DashboardActivityExpert.class);
                startActivity(intent);
            }
        });

    }
}
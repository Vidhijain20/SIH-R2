package com.example.sih_r2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    Button pendingButton;
    Button resolvedButton;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = (RecyclerView) findViewById(R.id.issues_view);

        DBHelper helper = new DBHelper(this);

        ArrayList<IssueModel> issueModelArrayList = new ArrayList<>();
        issueModelArrayList.clear();

        issueModelArrayList = helper.readPendingQueries();

        IssueAdapter issueAdapter = new IssueAdapter(this, issueModelArrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(issueAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        }
        WindowInsetsController controller = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            controller = getWindow().getInsetsController();
        }

        if (controller != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                controller.hide(WindowInsets.Type.navigationBars());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        }
        else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.button_add);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewIssue.class);
                startActivity(intent);
            }
        });

        pendingButton = (Button)findViewById(R.id.button_pending);
        resolvedButton = (Button)findViewById(R.id.button_resolved);

        resolvedButton.setBackgroundTintList(ContextCompat.getColorStateList(DashboardActivity.this, R.color.white));
        resolvedButton.setTextColor(getColor(R.color.teal_700));
        pendingButton.setTextColor(getColor(R.color.white));
        pendingButton.setBackgroundTintList(ContextCompat.getColorStateList(DashboardActivity.this, R.color.teal_700));
        pendingButton.setBackground(getDrawable(R.drawable.button_background));


        pendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolvedButton.setBackgroundTintList(ContextCompat.getColorStateList(DashboardActivity.this, R.color.white));
                resolvedButton.setTextColor(getColor(R.color.teal_700));
                pendingButton.setTextColor(getColor(R.color.white));
                pendingButton.setBackgroundTintList(ContextCompat.getColorStateList(DashboardActivity.this, R.color.teal_700));
                pendingButton.setBackground(getDrawable(R.drawable.button_background));


                DBHelper helper = new DBHelper(getApplicationContext());

                ArrayList<IssueModel> issueModelArrayList = new ArrayList<>();
                issueModelArrayList.clear();

                issueModelArrayList = helper.readPendingQueries();

                IssueAdapter issueAdapter = new IssueAdapter(getApplicationContext(), issueModelArrayList);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(issueAdapter);

            }
        });

        resolvedButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                pendingButton.setBackgroundTintList(ContextCompat.getColorStateList(DashboardActivity.this, R.color.white));
                pendingButton.setTextColor(getColor(R.color.teal_700));
                resolvedButton.setTextColor(getColor(R.color.white));
                resolvedButton.setBackgroundTintList(ContextCompat.getColorStateList(DashboardActivity.this, R.color.teal_700));
                resolvedButton.setBackground(getDrawable(R.drawable.button_background));

                DBHelper helper = new DBHelper(getApplicationContext());

                ArrayList<IssueModel> issueModelArrayList = new ArrayList<>();
                issueModelArrayList.clear();

                issueModelArrayList = helper.readResolvedQueries();

                IssueAdapterUser issueAdapter = new IssueAdapterUser(getApplicationContext(), issueModelArrayList);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(issueAdapter);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        resolvedButton.setBackgroundTintList(ContextCompat.getColorStateList(DashboardActivity.this, R.color.white));
        resolvedButton.setTextColor(getColor(R.color.teal_700));
        pendingButton.setTextColor(getColor(R.color.white));
        pendingButton.setBackgroundTintList(ContextCompat.getColorStateList(DashboardActivity.this, R.color.teal_700));
        pendingButton.setBackground(getDrawable(R.drawable.button_background));


        DBHelper helper = new DBHelper(this);

        ArrayList<IssueModel> issueModelArrayList = new ArrayList<>();
        issueModelArrayList.clear();

        issueModelArrayList = helper.readPendingQueries();

        IssueAdapter issueAdapter = new IssueAdapter(this, issueModelArrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(issueAdapter);
    }
}
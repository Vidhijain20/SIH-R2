package com.example.sih_r2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IssueAdapterUser extends RecyclerView.Adapter<IssueAdapterUser.MyViewHolder> {

    Context context;

    ArrayList<IssueModel> arrayList;

    @NonNull
    @Override
    public IssueAdapterUser.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new IssueAdapterUser.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueAdapterUser.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        IssueModel issueModel = arrayList.get(position);
        holder.position = position;
        holder.bind(issueModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public long getItemId(int position)
    {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleView;

        TextView descView;

        ImageView imageView;

        int position;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.titleView = (TextView) itemView.findViewById(R.id.titleView);
            this.descView = (TextView) itemView.findViewById(R.id.descView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView3);
        }

        public void bind(IssueModel issueModel)
        {
            titleView.setText(issueModel.getSavedTitle());
            descView.setText(issueModel.getSavedDesc());

            imageView.setImageBitmap(BitmapFactory.decodeByteArray(issueModel.getSavedURL(), 0, issueModel.getSavedURL().length));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ResolvedActivity.class);
                    intent.putExtra("TITLE", issueModel.getSavedTitle());
                    intent.putExtra("DESC", issueModel.getSavedDesc());
                    intent.putExtra("URL", issueModel.getSavedURL());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    public IssueAdapterUser(Context context, ArrayList<IssueModel> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }
}

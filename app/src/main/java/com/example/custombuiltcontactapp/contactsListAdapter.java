/*Contact Adapter class in which recycler view and its button's
functionalites are implemented */
package com.example.custombuiltcontactapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.List;

public class contactsListAdapter extends RecyclerView.Adapter<contactsListAdapter.contactViewHolder> {
    private List<contact> contactsList=new ArrayList<>();

    // RecyclerView recyclerView;  
    public contactsListAdapter(List<contact> contactsList) {
        this.contactsList = contactsList;
    }

    @Override
    public contactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.customview, parent, false);
        contactViewHolder viewHolder = new contactViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(contactViewHolder holder, int position) {
        final contact contact = contactsList.get(position);
        holder.nameTextView.setText(contactsList.get(position).getcName());
        holder.numberTextView.setText(contactsList.get(position).getcNumber());
        holder.imageView.setImageBitmap(contactsList.get(position).getcImage());


        //Setting functionality of close btn
        try {
            holder.constraintLayout.findViewById(R.id.cvButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemRemoved(position);
                    contactsList.remove(position);
                    notifyItemRangeChanged(position,contactsList.size());
                }
            });
        }
        catch (Exception e) { System.out.println(e); }


    }


    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public  class contactViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView numberTextView;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;

        public contactViewHolder(View itemView) {
            super(itemView);
            this.nameTextView = (TextView) itemView.findViewById(R.id.cvName);
            this.numberTextView = (TextView) itemView.findViewById(R.id.cvNumber);
            this.imageView = (ImageView) itemView.findViewById(R.id.cvImage);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.cvConstrainLayout);
        }
    }
}


package com.rabi.simplecontacts.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabi.simplecontacts.R;
import com.rabi.simplecontacts.model.Contact;

import java.util.List;

import static com.rabi.simplecontacts.activity.ContactDetailActivity.generateOTP;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private List<Contact> mContactList;
    private OnItemClickListener mClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public ContactAdapter(Context context, List<Contact> mContactList) {
        Context mContext = context;
        this.mContactList = mContactList;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_item_card,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactAdapter.ViewHolder viewHolder, int i) {

        Contact contact = mContactList.get(i);


        String name = contact.getmFullName();
        viewHolder.contactImage.setText(name.substring(0,1));
        viewHolder.contactImage.getBackground().setColorFilter(
                Color.parseColor("#"+generateOTP()), PorterDuff.Mode.SRC_ATOP);

        viewHolder.contactName.setText(contact.getmFullName());
        viewHolder.contactNumber.setText(contact.getmNumber());

        // sets on click listener on each item
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(view, viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contactImage; // ImageView where, text drawable will be populated
        private TextView contactName;   // TextView to show contact name
        private TextView contactNumber; // TextView to show contact number

        ViewHolder(View itemView) {
            super(itemView);
            contactImage = (TextView) itemView.findViewById(R.id.contact_image);
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            contactNumber = (TextView) itemView.findViewById(R.id.contact_number);
        }
    }
}

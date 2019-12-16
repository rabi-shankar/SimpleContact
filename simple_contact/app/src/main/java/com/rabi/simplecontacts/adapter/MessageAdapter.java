package com.rabi.simplecontacts.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rabi.simplecontacts.R;
import com.rabi.simplecontacts.db.dbHelper;
import com.rabi.simplecontacts.model.Contact;
import com.rabi.simplecontacts.model.Message;

import java.util.ArrayList;
import java.util.List;

import static com.rabi.simplecontacts.activity.ContactDetailActivity.generateOTP;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private List<Message> mMessageList;
    private OnItemClickListener mClickListener;
    private Context mContext;
    private RecyclerView mRecyclerView;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public MessageAdapter(Context context, List<Message> mMessageList,RecyclerView mRecyclerView) {
        this.mContext = context;
        this.mMessageList = mMessageList;
        this.mRecyclerView = mRecyclerView;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_item_card,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageAdapter.ViewHolder viewHolder, final int i) {

        final Message message = mMessageList.get(i);

        //contact image
        String name = message.getContact().getmFullName();
        viewHolder.contactImage.setText(name.substring(0,1));
        viewHolder.contactImage.getBackground().setColorFilter(
                Color.parseColor("#"+generateOTP()), PorterDuff.Mode.SRC_ATOP);


        viewHolder.contactName.setText(name);
        viewHolder.contactNumber.setText(message.getContact().getmNumber());
        viewHolder.otp.setText("OTP: "+message.getOTP());
        viewHolder.timesTamp.setText(message.getTimestamp());
        viewHolder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Snackbar snackBar = (Snackbar) Snackbar.make(mRecyclerView, "Delete the Message ("+message.getContact().getmFullName()+")?", Snackbar.LENGTH_LONG);
                snackBar.setAction("Delete", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dbHelper db = new dbHelper(mContext);
                        if(db.deleteMessage(message)){
                            Toast.makeText(mContext,"Message is delete successfully.",Toast.LENGTH_SHORT).show();
                        }
                        //mMessageList.remove(i);
                        //mRecyclerView.notify();
                        snackBar.dismiss();
                    }
                });
                snackBar.show();
            }
        });

        /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(view, viewHolder.getAdapterPosition());
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contactImage;
        private TextView contactName;
        private TextView contactNumber;
        private TextView otp;
        private TextView timesTamp;
        private ImageView action;


        ViewHolder(View itemView) {
            super(itemView);
            contactImage = (TextView) itemView.findViewById(R.id.contact_image);
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            contactNumber = (TextView) itemView.findViewById(R.id.contact_number);
            otp = (TextView) itemView.findViewById(R.id.otp);
            timesTamp = (TextView) itemView.findViewById(R.id.timestamp);
            action = (ImageView) itemView.findViewById(R.id.action);
        }
    }


}
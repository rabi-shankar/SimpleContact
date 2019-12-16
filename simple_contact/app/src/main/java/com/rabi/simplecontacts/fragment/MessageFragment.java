
package com.rabi.simplecontacts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rabi.simplecontacts.R;
import com.rabi.simplecontacts.adapter.MessageAdapter;
import com.rabi.simplecontacts.db.dbHelper;
import com.rabi.simplecontacts.model.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private LinearLayout mEmptyMsgBox;
    Context context;
    final int CONTEXT_MENU_VIEW = 1;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contener, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mEmptyMsgBox = (LinearLayout) view.findViewById(R.id.empty_message_box);

        context = getActivity();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        dbHelper db = new dbHelper(context);
        ArrayList<Message> messageList = db.getAllMessage();

        if(messageList.size()>0) {

            mEmptyMsgBox.setVisibility(View.GONE);
            MessageAdapter adapter = new MessageAdapter(context, messageList,mRecyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(adapter);

        }else {
            mEmptyMsgBox.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Action delete Message");
        menu.add(Menu.NONE, CONTEXT_MENU_VIEW, Menu.NONE, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId() == CONTEXT_MENU_VIEW) {
            //TODO
        }
        return super.onContextItemSelected(item);
    }

}

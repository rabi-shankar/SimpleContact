package com.rabi.simplecontacts.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rabi.simplecontacts.R;
import com.rabi.simplecontacts.activity.ContactDetailActivity;
import com.rabi.simplecontacts.adapter.ContactAdapter;
import com.rabi.simplecontacts.model.Contact;
import com.rabi.simplecontacts.model.ContactAttributes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {

    public static final String CONTACT_Name = "contactDetailName";
    public static final String CONTACT_PhoneNumber = "contactDetailPhone";
    public ContactFragment() {
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

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        List<Contact> contactList = new ArrayList<>();
        try {
            contactList =  getContactList();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(contactList != null) {
            ContactAdapter adapter = new ContactAdapter(getContext(), contactList);
            setOnclickListener(adapter, contactList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }

    }

    private void setOnclickListener(ContactAdapter adapter, final List<Contact> contactList) {
        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent startIntent = new Intent(getActivity(),ContactDetailActivity.class);
                startIntent.putExtra(CONTACT_Name, contactList.get(position).getmFullName());
                startIntent.putExtra(CONTACT_PhoneNumber, contactList.get(position).getmNumber());
                startActivity(startIntent);
            }
        });
    }

    private List<Contact> getContactList() throws JSONException, IOException {

        StringBuilder statesJson = new StringBuilder();
        Resources resources = getResources();
        InputStream rawStates = resources.openRawResource(R.raw.contact);
        BufferedReader reader = new BufferedReader(new InputStreamReader(rawStates));
        String line = null;
        while ((line = reader.readLine()) != null) {
            statesJson.append(line);
        }

        JSONArray jsonArray = new JSONArray(statesJson.toString());
        JSONObject contact;
        List<Contact> mContactList = new ArrayList<>(jsonArray.length());

        for(int i = 0; i < jsonArray.length(); i++) {
            contact = jsonArray.getJSONObject(i);
            final String fullName = contact.getString(ContactAttributes.FULL_NAME);
            final String countryCode = contact.getString(ContactAttributes.COUNTRY_CODE);
            final String mobileNumber = contact.getString(ContactAttributes.MOBILE_NUMBER);

            mContactList.add(new Contact(fullName, countryCode+mobileNumber));

        }
        return mContactList;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

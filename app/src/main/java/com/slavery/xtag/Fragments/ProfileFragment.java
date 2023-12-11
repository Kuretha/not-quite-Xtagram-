package com.slavery.xtag.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slavery.xtag.Adapter.ChatAdapter;
import com.slavery.xtag.Model.Chat;
import com.slavery.xtag.R;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {

    private RecyclerView recyclerViewSearch;
    private ChatAdapter chatAdapter;
    private List<Chat> chatList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        recyclerViewSearch = view.findViewById(R.id.recycle_view_friend);
//        recyclerViewSearch.setHasFixedSize(true);
//        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));

        chatList = new ArrayList<>();


        return view;
    }
}
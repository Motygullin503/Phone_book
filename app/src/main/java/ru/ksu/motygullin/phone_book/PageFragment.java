package ru.ksu.motygullin.phone_book;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.ksu.motygullin.phone_book.adapters.MyItemRecyclerViewAdapter;

/**
 * Created by UseR7 on 21.10.2016.
 */

public class PageFragment extends Fragment {

    private boolean isDeleted;
    private MyItemRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    public static PageFragment newInstance(boolean isDeleted) {

        Bundle args = new Bundle();
        args.putBoolean("isDeleted", isDeleted);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void updateAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        isDeleted = getArguments().getBoolean("isDeleted");

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        adapter = new MyItemRecyclerViewAdapter(isDeleted, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }


}


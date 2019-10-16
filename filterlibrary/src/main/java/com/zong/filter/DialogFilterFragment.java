package com.zong.filter;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DialogFilterFragment extends DialogFragment {
    private static final String TAG = "DialogFilterFragment";
    CategoryAdapter adapter;
    View view;
    List<CategoryBean> listBeanList;
    RecyclerView rv;

    public static DialogFilterFragment newInstance(List<CategoryBean> list) {
        DialogFilterFragment fragment = new DialogFilterFragment();
        fragment.listBeanList = list;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = null;
        if(view==null){
            Log.i(TAG, "onCreateView: 创建");
            view = inflater.inflate(R.layout.filter_dialog, container, false);
        }
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
        this.view=view;
        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CategoryAdapter(listBeanList,getActivity());
        rv.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}

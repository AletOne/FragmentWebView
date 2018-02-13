package com.example.wang.fragmentwebview;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wang.fragmentwebview.dummy.Data;

import java.nio.file.WatchEvent;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragment extends Fragment {
    private String[] mData;
    private ListView mListView;
    private ArrayAdapter mAdapter;
    private String url = "https://en.wikipedia.org/wiki/";
    boolean mDualPane;
    int mCurrPosition = 0;

    public ChildFragment() {
        // Required empty public constructor
    }

    public static ChildFragment newChildFragment(String key){
        ChildFragment fragment = new ChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", key);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child, container, false);
        Bundle bundle = getArguments();
        String key = bundle.getString("key");
        mData = Data.items.get(key);

        mListView = view.findViewById(R.id.breeds_listView);
        mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item,
                mData);
        mListView.setAdapter(mAdapter);

        View webFragment = getActivity().findViewById(R.id.webview_fragment);
        mDualPane = webFragment != null && webFragment.getVisibility() == View.VISIBLE;

        Log.d("Dual", mDualPane + "");

        if (savedInstanceState != null){

            mCurrPosition = savedInstanceState.getInt("position", 0);
            Log.d("Istate", mCurrPosition+"  " + mDualPane);
        }

        if (mDualPane){
            mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurrPosition);
        }


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                showDetails(position);
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", mCurrPosition);
    }

    private void showDetails(int position){
        mCurrPosition = position;
        String r = url + mData[position];
        if (mDualPane){
            mListView.setItemChecked(position, true);

            WebViewFragment fragment = (WebViewFragment) getFragmentManager().findFragmentById(R.id.webview_fragment);
            if (fragment == null || fragment.getShownPosition() != position){
                fragment = WebViewFragment.newInstance(r, position);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.webview_fragment, fragment);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();
            }

        }else{
            Intent intent = new Intent();
            intent.setClass(getActivity(), WebViewActivity.class);
            intent.putExtra("URL", r);
            startActivity(intent);
        }
    }

}

package com.example.wang.fragmentwebview;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {

    private WebView mWebView;
    public WebViewFragment() {
        // Required empty public constructor
    }

    public static WebViewFragment newInstance(String url, int position){
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("URL", url);
        bundle.putInt("P", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    public int getShownPosition(){
        return getArguments().getInt("P");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        Bundle bundle = getArguments();
        String url = bundle.getString("URL");
        mWebView = view.findViewById(R.id.wiki_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        Log.d("url", url);
        mWebView.loadUrl(url);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return view;
    }

}

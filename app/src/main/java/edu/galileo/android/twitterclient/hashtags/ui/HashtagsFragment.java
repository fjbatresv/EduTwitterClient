package edu.galileo.android.twitterclient.hashtags.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.TwitterClientApp;
import edu.galileo.android.twitterclient.entities.Hash;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.hashtags.DI.HashesComponent;
import edu.galileo.android.twitterclient.hashtags.HashesPresenter;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.HashAdapter;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener;
import edu.galileo.android.twitterclient.images.DI.ImagesComponent;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashtagsFragment extends Fragment implements HashesView, OnItemClickListener {
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.reclyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.fragmenContainer)
    FrameLayout container;

    public HashtagsFragment() {
        // Required empty public constructor
    }
    @Inject
    HashesPresenter presenter;
    @Inject
    HashAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        setUpInjection();
        presenter.getHashtagTweetes();
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setUpInjection() {
        TwitterClientApp app = (TwitterClientApp)getActivity().getApplication();
        HashesComponent hashesComponent = app.getHashComponent(this, this, this);
        //opcion 1
        hashesComponent.inject(this);
        //opcion 2: Inyectar variable por variable.
        //presenter = imagesComponent.getPresenter();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setContent(List<Hash> hashes) {
        adapter.setItems(hashes);
    }

    @Override
    public void onItemClick(Hash hash) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(hash.getTweetUrl())));
    }
}

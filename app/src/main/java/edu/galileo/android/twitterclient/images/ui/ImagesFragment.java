package edu.galileo.android.twitterclient.images.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.TwitterClientApp;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.images.DI.ImagesComponent;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.adapters.ImagesAdapter;
import edu.galileo.android.twitterclient.images.adapters.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment implements ImagesView, OnItemClickListener {
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.reclyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.fragmenContainer)
    FrameLayout container;

    //La etiqueta Inject es para que el component, sepa que variables cargar
    @Inject
    ImagesPresenter presenter;
    @Inject
    ImagesAdapter adapter;

    public ImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        setUpInjection();
        presenter.getImageTweetes();
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    private void setUpInjection() {
        TwitterClientApp app = (TwitterClientApp)getActivity().getApplication();
        ImagesComponent imagesComponent = app.getImagesComponent(this, this, this);
        //opcion 1
        imagesComponent.inject(this);
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
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
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
    public void setContent(List<Image> imgs) {
        adapter.setItems(imgs);
    }

    @Override
    public void onItemClick(Image image) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetUrl())));
    }
}

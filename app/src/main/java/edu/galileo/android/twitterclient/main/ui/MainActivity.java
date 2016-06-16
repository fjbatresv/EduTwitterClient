package edu.galileo.android.twitterclient.main.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.sdk.android.Twitter;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsFragment;
import edu.galileo.android.twitterclient.images.ui.ImagesFragment;
import edu.galileo.android.twitterclient.login.ui.LoginActivity;
import edu.galileo.android.twitterclient.main.ui.adapters.MainSectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.containerMain)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpToolBar();
        setUpAdapter();
    }

    private void setUpAdapter() {
        Fragment[] fragments = new Fragment[]{
                new ImagesFragment(),
                new HashtagsFragment()
        };
        String[] titles = new String[]{
                getString(R.string.main_header_images),
                getString(R.string.main_header_hashtags)
        };
        MainSectionsPagerAdapter adapter = new MainSectionsPagerAdapter(
                getSupportFragmentManager(),
                fragments,
                titles
        );
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    private void setUpToolBar() {
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Twitter.logOut();
        startActivity(new Intent(this, LoginActivity.class).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
        ));
    }
}

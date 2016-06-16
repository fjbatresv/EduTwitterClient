package edu.galileo.android.twitterclient.login.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.main.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.containerLogin)
    RelativeLayout container;
    @Bind(R.id.twitterLoginButton)
    TwitterLoginButton twitterLoginButton;

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if(Twitter.getSessionManager().getActiveSession() != null){
            navigateToMainScreen();
        }
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                navigateToMainScreen();
            }

            @Override
            public void failure(TwitterException exception) {
                //getLocalizedMessage nos da un error segun la localizacion del usuario(idioma).
                Snackbar.make(
                        container,
                        String.format(
                                getString(R.string.login_error_message),
                                exception.getLocalizedMessage()
                        ),
                        Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    private void navigateToMainScreen(){
        startActivity(new Intent(this, MainActivity.class).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
        ));
    }

}

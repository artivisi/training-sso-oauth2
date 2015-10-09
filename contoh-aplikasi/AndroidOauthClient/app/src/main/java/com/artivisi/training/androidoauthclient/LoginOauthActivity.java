package com.artivisi.training.androidoauthclient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson.JacksonFactory;
import com.wuman.android.auth.AuthorizationFlow;
import com.wuman.android.auth.AuthorizationUIController;
import com.wuman.android.auth.DialogFragmentController;
import com.wuman.android.auth.OAuthManager;
import com.wuman.android.auth.oauth2.store.SharedPreferencesCredentialStore;

import java.io.IOException;

public class LoginOauthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_oauth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void login(View view){
        Toast.makeText(this, "Login Dulu",Toast.LENGTH_SHORT)
        .show();

        String authorizeUrl = "http://192.168.88.66:10000/oauth/authorize";
        String tokenUrl = "http://192.168.88.66:10000/oauth/token";
        final String redirectUrl = "http://android.local/";
        String resourceServerUrl = "http://192.168.88.66:8080/api/halo";
        String clientId = "jsclient";
        String clientSecret = "123456";

        SharedPreferencesCredentialStore credentialStore =
                new SharedPreferencesCredentialStore(getApplicationContext(),
                        "preferenceFileName", new JacksonFactory());

        AuthorizationFlow.Builder builder = new AuthorizationFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(),
                AndroidHttp.newCompatibleTransport(),
                new JacksonFactory(),
                new GenericUrl(tokenUrl),
                new ClientParametersAuthentication(clientId, clientSecret),
                clientId,
                authorizeUrl);
        builder.setCredentialStore(credentialStore);
        AuthorizationFlow flow = builder.build();

        AuthorizationUIController controller =
                new DialogFragmentController(getFragmentManager()) {

                    @Override
                    public String getRedirectUri() throws IOException {
                        return redirectUrl;
                    }

                    @Override
                    public boolean isJavascriptEnabledForWebView() {
                        return true;
                    }

                };

        OAuthManager oauth = new OAuthManager(flow, controller);

        OAuthManager.OAuthCallback<Credential> callback = new OAuthManager.OAuthCallback<Credential>() {
            @Override public void run(OAuthManager.OAuthFuture<Credential> future) {
                try {
                    Credential c = future.getResult();
                    String accessToken = c.getAccessToken();
                    Toast.makeText(getApplicationContext(),
                            "Token : "+accessToken, Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error authorize " + e.getMessage(), Toast.LENGTH_SHORT);

                }

            }
        };


        oauth.authorizeImplicitly("userid", callback, null);


    }

}

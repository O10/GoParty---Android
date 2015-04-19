package com.studiumrogusowe.goparty.authorization;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.studiumrogusowe.goparty.R;
import com.studiumrogusowe.goparty.authorization.model.AuthLoginBodyObject;
import com.studiumrogusowe.goparty.authorization.model.AuthResponseObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by O10 on 16.04.15.
 */
public class RegisterActivity extends Activity {
    private EditText login, password, confirmPassword;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

    }

    private void initViews() {
        this.login = (EditText) findViewById(R.id.name);
        this.password = (EditText) findViewById(R.id.password);
        this.confirmPassword = (EditText) findViewById(R.id.password_confirm);
        this.signup = (Button) findViewById(R.id.submit);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            return;
        }
        final AuthLoginBodyObject authLoginBodyObject = new AuthLoginBodyObject();
        authLoginBodyObject.setPassword(password.getText().toString());
        authLoginBodyObject.setEmail(login.getText().toString());
        AuthRestAdapter.getInstance().getAuthApi().registerAndGetToken(authLoginBodyObject, new Callback<AuthResponseObject>() {
            @Override
            public void success(AuthResponseObject authResponseObject, Response response) {
                Bundle data = new Bundle();


                data.putString(AccountManager.KEY_ACCOUNT_NAME, authLoginBodyObject.getEmail());
                data.putString(AccountManager.KEY_ACCOUNT_TYPE, AuthorizationUtilities.ACCESS_TOKEN_TYPE);
                data.putString(AccountManager.KEY_AUTHTOKEN, authResponseObject.getAccess_token());
                data.putString(LoginActivity.PARAM_USER_PASS, authLoginBodyObject.getPassword());
                final Intent res = new Intent();
                res.putExtras(data);
                setResult(RESULT_OK, res);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}

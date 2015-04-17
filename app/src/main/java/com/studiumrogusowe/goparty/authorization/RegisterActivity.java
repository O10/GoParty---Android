package com.studiumrogusowe.goparty.authorization;

import android.accounts.Account;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.studiumrogusowe.goparty.R;

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

    private void initViews(){
        this.login= (EditText) findViewById(R.id.name);
        this.password= (EditText) findViewById(R.id.password);
        this.confirmPassword= (EditText) findViewById(R.id.password_confirm);
        this.signup= (Button) findViewById(R.id.submit);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register(){

    }
}

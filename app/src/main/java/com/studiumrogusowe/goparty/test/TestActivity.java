package com.studiumrogusowe.goparty.test;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.studiumrogusowe.goparty.R;
import com.studiumrogusowe.goparty.authorization.AuthorizationUtilities;
import com.studiumrogusowe.goparty.authorization.TokenCallback;

import java.io.IOException;

/**
 * Created by O10 on 16.04.15.
 * przykladowa activity z programu
 */
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserToken(new TokenCallback() {
            @Override
            public void onTokenAquired(String accessToken, Account account) {

            }

            @Override
            public void onFailed() {

            }
        });
    }

    /**
     * Tą metodę damy do jakiejś bazowej activity i będziemy dziedziczyć żeby było dostępna z każ∂ego miejsca
     * Token jest pobierany asynchronicznie jeśli go nie ma odpalana jest login activity stąd ten callback
     * @param tokenCallback
     */
    protected void getUserToken(final TokenCallback tokenCallback) {
        final AccountManager accountManager = AccountManager.get(this);
        final Account[] accounts = accountManager.getAccountsByType(getString(R.string.go_part_acc_type));
        if (accounts.length == 1) {
            accountManager.getAuthToken(accounts[0], AuthorizationUtilities.ACCESS_TOKEN_TYPE, null, TestActivity.this, new GeneralHelperAccountManagerCallback(accounts[0], tokenCallback), null);
        } else if (accounts.length == 0) {
            accountManager.addAccount(getString(R.string.go_part_acc_type), AuthorizationUtilities.ACCESS_TOKEN_TYPE, null, null, this, new AccountManagerCallback<Bundle>() {
                @Override
                public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                    final Account[] accounts = accountManager.getAccountsByType(getString(R.string.go_part_acc_type));
                    accountManager.getAuthToken(accounts[0], AuthorizationUtilities.ACCESS_TOKEN_TYPE, null, TestActivity.this, new GeneralHelperAccountManagerCallback(accounts[0], tokenCallback), null);
                }
            }, null);
        }
    }



    private class GeneralHelperAccountManagerCallback implements AccountManagerCallback<Bundle> {
        private Account account;
        private TokenCallback tokenCallback;

        public GeneralHelperAccountManagerCallback(Account account, TokenCallback tokenCallback) {
            this.account = account;
            this.tokenCallback = tokenCallback;
        }

        @Override
        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            try {
                final Bundle result = accountManagerFuture.getResult();
                final String authtoken = result.getString(AccountManager.KEY_AUTHTOKEN);
                tokenCallback.onTokenAquired(authtoken, account);
            } catch (OperationCanceledException | IOException | AuthenticatorException e) {
                e.printStackTrace();
                tokenCallback.onFailed();
            }
        }
    }
}

package com.syrous.yccestudentlibraryjava.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.syrous.yccestudentlibraryjava.Activities.LoginActivity;
import com.syrous.yccestudentlibraryjava.HomeActivity;
import com.syrous.yccestudentlibraryjava.R;

/**
 * Created By : Syrous
 * date : 26/1/20
 */

public class LoginFragment extends Fragment {

    public static final String TAG = "SignUpActivity";
    public static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mClient;


    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                    .requestEmail()
                                    .requestId()
                                    .build();

        mClient = GoogleSignIn.getClient(getActivity(), gso);


        SignInButton signInButton = v.findViewById(R.id.signInButton);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);
        signInButton.setOnClickListener((View) -> { signIn();});

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity()); // Check the Last Signed In User
        updateUI(account);
    }

    public void signIn(){
        Intent signInIntent = mClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "Username : "+account.getDisplayName());
                Log.d(TAG, "UserID : "+account.getId());
                Log.d(TAG, "UserEmail : "+account.getEmail());
                Log.d(TAG, "UserToken : "+account.getIdToken());

                updateUI(account);
            } catch (ApiException e) {

                Log.w(TAG, "Error : "+e.getStatusCode());
                updateUI(null);
            }
    }

    public void signOut(){
        mClient.signOut().addOnCompleteListener(getActivity(), (View) -> {Log.d(TAG, "User Signed Out : ");});
    }

//    public void revokeAccess(){
//        mClient.revokeAccess()
//                .addOnCompleteListener(getActivity(), //TODO : onCompleteListener);
//    }

    private void updateUI(GoogleSignInAccount account) {
        if(account == null){
            //TODO : SignOut
        }
        else {
            //TODO : Move to The Next Screen
            Intent intent= new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);

        }
    }
}

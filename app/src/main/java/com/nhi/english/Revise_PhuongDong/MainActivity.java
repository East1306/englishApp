package com.nhi.english.Revise_PhuongDong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import  com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nhi.english.PhatLe.Activity_HomeMenu;
import com.nhi.english.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView google;
    SignInButton signInButton;
    GoogleSignInOptions gso;
//    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInClient gsc;
    static final int RC_SIGN_IN = 0;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Map<String, Object> users = new HashMap<>();
        firestore = FirebaseFirestore.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        gsc = GoogleSignIn.getClient(this, gso);
        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                signIn();
//                switch (v.getId()) {
//                    case R.id.sign_in_button:
//                        signIn();
//                        break;
//                    // ...
//                }
            }

        });

//        google = findViewById(R.id.btnLogin);
//
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        gsc = GoogleSignIn.getClient (this,gso);
//
//        google.getOnClickListener(new View.OnClickListener(){
//            @Override
//            public  void onClick(View view){
//                SignIn();
//            }
//        });
    }

    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1000) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            completedTask.getResult(ApiException.class);
            Toast.makeText(this, "Sign-in-success", Toast.LENGTH_SHORT).show();
            navigateToActivity_HomeMenu();
        } catch (ApiException e) {
            Log.w( "Error","signInResult:failed code=" + e.getStatusCode());
        }

    }

    void navigateToActivity_HomeMenu(){
        finish();
        Intent intent = new Intent(MainActivity.this, Activity_HomeMenu.class);
        startActivity(intent);
    }
//    private void SignIn() {
//        Intent intent = gsc.getSignInIntent();
//        startActivityForResult(intent, 1000);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1000){
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                task.getResult(ApiException.class);
//                Toast.makeText(this, "Sign-in-success", Toast.LENGTH_SHORT).show();
//                navigateToActivity_HomeMenu();
//            } catch (ApiException e) {
//                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                Log.w( "Error","signInResult:failed code=" + e.getStatusCode());
//            }
//        }
//    }
//    void navigateToActivity_HomeMenu(){
//        finish();
//        Intent intent = new Intent(this, Activity_HomeMenu.class);
//        startActivity(intent);
//    }
}
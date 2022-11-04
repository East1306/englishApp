package com.nhi.english;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

//    ImageView google;
//    SignInButton signInButton;
//    GoogleSignInClient mGoogleSignInClient;
//    static final int RC_SIGN_IN = 0;

//    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Map<String, Object> users = new HashMap<>();
//        firestore = FirebaseFirestore.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_menu);

//        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        signInButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                switch (v.getId()) {
//                    case R.id.sign_in_button:
//                        signIn();
//                        break;
//                    // ...
//                }
//            }
//
//        });
//
////        google = findViewById(R.id.btnLogin);
//
////        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
////                .requestEmail()
////                .build();
////        gsc = GoogleSignIn.getClient (this,gso);
////
////        google.getOnClickListener(new View.OnClickListener(){
////            @Override
////            public  void onClick(View view){
////                SignIn();
////            }
////        });
//    }
//
//    private void signIn() {
//            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//            startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            Toast.makeText(this, "Sign-in-success", Toast.LENGTH_SHORT).show();
//        } catch (ApiException e) {
//            Log.w( "Error","signInResult:failed code=" + e.getStatusCode());
//        }
    }

//    private void SignIn() {
//        Intent intent = gsc.getSignInIntent();
//        startActivityForResult(intent, 100);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (request Code ==100){
//            Task<GoogleSignInACcount> task = GoogleSignIN. getSigned
//        }
//    }
}
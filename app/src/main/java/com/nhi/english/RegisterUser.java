package com.nhi.english;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhi.english.PhatLe.Activity_HomeMenu;

public class RegisterUser extends AppCompatActivity {
    private Button register;
    private EditText name;
    private  EditText password;
    private ImageView checkUser, checkPass;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name = findViewById(R.id.nameUser);
        password = findViewById(R.id.password);
        checkUser = findViewById(R.id.checkUser);
        checkPass = findViewById(R.id.checkPass);
        register = findViewById(R.id.btnRegister);

        checkUser.setVisibility(View.INVISIBLE);
        checkPass.setVisibility(View.INVISIBLE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameUser = name.getText().toString();
                String password_ = password.getText().toString();

                if(TextUtils.isEmpty(nameUser) || TextUtils.isEmpty(password_)){
                    Toast.makeText(RegisterUser.this
                            , "Please enter user name and password", Toast.LENGTH_LONG).show();
                }else{
                    registerUser(nameUser, password_);
                }


            }
        });
    }

    private void registerUser(String nameUser, String password_) {
        Intent intent = new Intent(this, Activity_HomeMenu.class);
        intent.putExtra("User", name.getText().toString());
        startActivity(intent);
    }
}

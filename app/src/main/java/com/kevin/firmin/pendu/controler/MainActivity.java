package com.kevin.firmin.pendu.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kevin.firmin.pendu.R;
import com.kevin.firmin.pendu.model.User;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private TextView mMessageWelcome;
    private TextView mName;
    private EditText mNameInput;
    private Button mStart;
    private User mMyUser;

    public final static String KEY_NAMEUSER="KEY_NAMEUSER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyUser=new User();

        mMessageWelcome=(TextView)findViewById(R.id.MainActivity_welcomeMessage_txt);
        mName=(TextView)findViewById(R.id.MainActivity_Name_txt);
        mNameInput=(EditText)findViewById(R.id.MainActivity_EnterName_input);
        mStart=(Button)findViewById(R.id.MainActivity_start_btn);
        mStart.setEnabled(false);

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                mStart.setEnabled(charSequence.toString().length()!=0);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMyUser.setUserName(mNameInput.getText().toString());

                Intent gameActivity=new Intent(MainActivity.this,GameActivity.class);
                gameActivity.putExtra(KEY_NAMEUSER,mMyUser.getUserName());
                startActivity(gameActivity);
            }
        });
    }
}

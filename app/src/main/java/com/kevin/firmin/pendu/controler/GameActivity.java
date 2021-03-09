package com.kevin.firmin.pendu.controler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kevin.firmin.pendu.R;
import com.kevin.firmin.pendu.model.BankWord;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {



    private TextView mText;
    private Button mViewButton;
    private Button mValidationButton;
    private EditText mUserInput;

    private int mNbreCoups;
    private BankWord mBank;
    private String displayWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


         mNbreCoups=10;
         mText=(TextView)findViewById(R.id.GameActivity_textCoups_txt);
         mViewButton=(Button)findViewById(R.id.GameActivity_viewButton_btn);
         mUserInput=(EditText)findViewById(R.id.GameActivity_InputLetter_input);
         mValidationButton=(Button)findViewById(R.id.GameActivity_ValidationButton_btn);


         mText.setText("Il vous reste "+ mNbreCoups+" chances pour trouver le mot");
         mBank=new BankWord(Arrays.asList("CAMION","TELEVISION","ORDINATEUR","CALIBRAGE","INFORMATIQUE", "SORCIER","MESSAGER","CROYANCE",
                 "INTELLIGENCE","ARTIFICIELLE","INGENIEUR","OBJET","APPLICATION","COMMANDE","VIE","ALIEN","TEST","VISION"));
         displayWord=UpdateViewButtonbyUnderscore(mBank.getMotCourant());
         mViewButton.setText(displayViewButton(displayWord));
         mViewButton.setEnabled(false);
         mValidationButton.setEnabled(false);

         mUserInput.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                 mValidationButton.setEnabled(charSequence.toString().length()!=0);
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });

         mValidationButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String chaine=updateDisplayViewButtonByInputUser(mUserInput.getText().toString(),displayWord,mBank.getMotCourant());
                 String enterUser=mUserInput.getText().toString().toUpperCase();
                 mUserInput.setText("");

                 if(mBank.getMotCourant().indexOf(enterUser.charAt(0))==-1)
                 {
                    mNbreCoups--;
                    String chaine2="Desole!!!,il vous reste "+ mNbreCoups +" chances pour trouver le mot";
                    mText.setText(chaine2);
                 }
                 else{


                     String chaine2="bravo!!!,il vous reste "+ mNbreCoups +" chances pour trouver le mot";
                     mText.setText(chaine2);
                     displayWord=chaine;
                     mViewButton.setText(displayViewButton(displayWord));
                 }

                 StopActivity();
             }
         });

    }

    private String UpdateViewButtonbyUnderscore(String WORD)
    {

        String word="";

        for (int i=0;i<WORD.length();i++)
        {
            word=word+"_";
        }

        return word;
    }

    private String displayViewButton(String WORD)
    {
        String word="";
        for (int i=0;i<WORD.length();i++)
        {
            word=word+WORD.charAt(i)+" ";
        }

        return word;
    }

    private String updateDisplayViewButtonByInputUser(String letter,String displayWord,String WORD)
    {
        StringBuffer buffer=new StringBuffer(displayWord);
        letter=letter.toUpperCase();

        if(WORD.indexOf(letter.charAt(0))!=-1)
        {

            for(int i=0;i<WORD.length();i++)
            {
                if(WORD.charAt(i)==letter.charAt(0))
                {
                    buffer.setCharAt(i,letter.charAt(0));
                }
            }
        }

        return buffer.toString();
    }

    private void StopActivity() {

        if (mNbreCoups == 0 || displayWord.indexOf('_') == -1)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            String nameUser=(String)getIntent().getSerializableExtra("KEY_NAMEUSER");

            if(mNbreCoups == 0 )
            {

                builder.setTitle("Desole!!").setMessage("Eh bien desole "+nameUser+" vous avez perdu").
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).create()
                        .show();


            }
            if(displayWord.indexOf('_') == -1)
            {
                builder.setTitle("Bravo!!").setMessage("Eh bien bravo "+nameUser+" vous avez gagnez").
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();

                            }
                        }).create()
                        .show();


            }


        }
    }
}

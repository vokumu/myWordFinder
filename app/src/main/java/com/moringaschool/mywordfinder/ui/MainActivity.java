package com.moringaschool.mywordfinder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.mywordfinder.Constants;
import com.moringaschool.mywordfinder.R;
import com.moringaschool.mywordfinder.models.UserHelperClass;
import com.moringaschool.mywordfinder.models.WordHelperClass;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private DatabaseReference mSearchedWordReference;
    TextInputLayout searchPhrase;

    @BindView(R.id.findWord)
    Button mFindWord;
    @BindView(R.id.datamuse)
    Button mDataMuse;
    @BindView(R.id.homeButton)
    Button homeButton;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedWordReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        searchPhrase= (TextInputLayout) findViewById(R.id.searchPhrase);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
       // awesomeValidation.addValidation(this, R.id.wordEditText, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.worderror);
        mFindWord.setOnClickListener(this);
        mDataMuse.setOnClickListener(this);
        homeButton.setOnClickListener(this);

    }
    public boolean validateSearchPhrase(){
        String val=searchPhrase.getEditText().getText().toString();
        if(val.isEmpty()){
            searchPhrase.setError("Field cannot be empty");
            return false;
        }
        else{
            searchPhrase.setError(null);
            searchPhrase.setErrorEnabled(false);
            return true;
        }

    }

    @Override
    public void onClick(View v) {
        if (v == mFindWord) {
            if (!validateSearchPhrase()) {
                return;
            }
            else{
                String word = searchPhrase.getEditText().getText().toString();
                saveWordToFirebase(word);
                Intent intent = new Intent(MainActivity.this, WordsActivity.class);
                intent.putExtra("word", word);
                startActivity(intent);


            }

        }
        if(v==mDataMuse){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.datamuse.com/api/"));
            startActivity(webIntent);
        }

    }
    public void saveWordToFirebase(String word) {
       // mSearchedWordReference.setValue(word);
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("words");
        WordHelperClass helperClass=new WordHelperClass(word);
        reference.child(word).setValue(helperClass);
    }
}
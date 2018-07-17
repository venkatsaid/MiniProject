package com.example.user.miniproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;

public class StartActivity extends AppCompatActivity {
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
   // @BindView(R.id.textCheck)
    TextView textCheck;
    Pojo p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        textCheck=findViewById(R.id.textCheck);
        txtSpeechInput = findViewById(R.id.txtSpeechInput);
        btnSpeak = findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    if(result.get(0).equals("switch on")||result.get(0).equals("switch off")){
                        textCheck.setText(result.get(0));
                    }
                    //Main2Activity.text=txtSpeechInput.getText().toString();
                }
                break;
            }

        }
    }

    public void firebutton(View view) {
        //ikkda rayi
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("switchs");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
              /*  String value = dataSnapshot.getValue(String.class);

                Toast.makeText(StartActivity.this, value, Toast.LENGTH_SHORT).show();*/
                /*Map<String, Object> value=(Map<String,Object>) dataSnapshot.getValue();
                Toast.makeText(StartActivity.this, value, Toast.LENGTH_SHORT).show();*/
               /* Pojo p= dataSnapshot.getValue(Pojo.class);*/
               /* String value = dataSnapshot.getValue(String.class);
                Toast.makeText(StartActivity.this,(p.getSwitch1()), Toast.LENGTH_SHORT).show();
                Toast.makeText(StartActivity.this, (p.getSwitch2()), Toast.LENGTH_SHORT).show();
*/
                collectcolleges((Map<String,Object>) dataSnapshot.getValue());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private void collectcolleges(Map<String, Object> value) {
       // ArrayList<String> phoneNumbers = new ArrayList<>();


      //  p=new Pojo((String) value.get(1),(String) value.get(2));
///        Toast.makeText(this,Boolean.toString((Boolean) value.get(1))+Boolean.toString((Boolean) value.get(2))+Boolean.toString((Boolean) value.get("b1") ), Toast.LENGTH_SHORT).show();
       // collegearray=new ArrayList<>();
        //iterate through each user, ignoring their UID
        /*for (Map.Entry<String, Object> entry : value.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            p=new Pojo((String) singleUser.get("1"),(String) singleUser.get("2"));
            Toast.makeText(this,(String) value.get("1")+(String) value.get("2") , Toast.LENGTH_SHORT).show();
            *//*collegepojo cp=new collegepojo((String) singleUser.get("accrediatatedto"),(String) singleUser.get("district"),
                    (String) singleUser.get("image"),(String) singleUser.get("location"),(String) singleUser.get("name")
                    ,(String) singleUser.get("type"),(Double) singleUser.get("userrating"),(String) singleUser.get("weblink"));
            phoneNumbers.add((String) singleUser.get("name"));
            collegearray.add(cp);
            Log.i("name2",cp.getName());
            Toast.makeText(this,(String) singleUser.get("1")+(String) singleUser.get("2") , Toast.LENGTH_SHORT).show();
            //namevalues=namevalues+"\n"+(String) singleUser.get("name");*//*
        }*/
        //  Log.i("name",namevalues);
        //Log.i("name",collegearray.get(1).getName());

       /* if(collegearray.size()>0) {
            Log.i("name3",collegearray.get(1).getName()+"hiii");
            //ikkada
            MainActivity.rv.setLayoutManager(new GridLayoutManager(this, 1));
            rv.setAdapter(new MyAdapter(this, collegearray));
        }*/
       // Log.i("number",Integer.toString(collegearray.size()));
        //System.out.println(phoneNumbers.toString());
    }
}

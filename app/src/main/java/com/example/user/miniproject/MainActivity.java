package com.example.user.miniproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity{
    TextView textView1,textView2;
//    @BindView(R.id.button1)
    Button button1;
 //   @BindView(R.id.button2)
    Button button2;
    String string;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    ValueEventListener getDataListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.text1);
        textView2=findViewById(R.id.text2);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference().child("Home");
        firebaseDatabase=FirebaseDatabase.getInstance();
        getFireData();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Pojo pojo=dataSnapshot.getValue(Pojo.class);
                        if(textView1.getText()=="true"){
                            textView1.setText("false");
                            button1.setText("off");
                            pojo.setS1(false);
                        }
                        else {
                            textView1.setText("true");
                            button1.setText("On");
                            pojo.setS1(true);
                        }
                        myref.setValue(pojo);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Pojo pojo=dataSnapshot.getValue(Pojo.class);
                        if(textView2.getText()=="true"){
                            textView2.setText("false");
                            button2.setText("off");
                            pojo.setS2(false);
                        }
                        else {
                            textView2.setText("true");
                            button2.setText("On");
                            pojo.setS2(true);
                        }
                        myref.setValue(pojo);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    private void getFireData(){
        myref=FirebaseDatabase.getInstance().getReference().child("Home");
        getDataListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pojo pojo=dataSnapshot.getValue(Pojo.class);
                Log.i("pojo",pojo.getS1().toString());
                if (pojo.getS1()){
                    textView1.setText("true");
                    button1.setText("on");
                }
                else {
                    textView1.setText("false");
                    button1.setText("off");
                }
                if (pojo.getS2()){
                    textView2.setText("true");
                    button2.setText("on");
                }
                else {
                    textView2.setText("false");
                    button2.setText("off");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myref.addListenerForSingleValueEvent(getDataListener);
    }
}
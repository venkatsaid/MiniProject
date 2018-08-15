package com.example.user.miniproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    String string;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.text1);
        textView2=findViewById(R.id.text2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Home");
        firebaseDatabase=FirebaseDatabase.getInstance();
        // myref=firebaseDatabase.getReference("clg1");
        myref = firebaseDatabase.getReference();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             /*Log.i("count", Long.toString(dataSnapshot.getChildrenCount()));
                Toast.makeText(MainActivity.this,Long.toString(dataSnapshot.getChildrenCount()), Toast.LENGTH_SHORT).show();
               Pojo user = (Pojo) dataSnapshot.getValue();
                Log.i("hiiii", Boolean.toString(user.getId1()));*/
                /* Toast.makeText(MainActivity.this, Boolean.toString(user.id1), Toast.LENGTH_SHORT).show();*/
                // Log.d(TAG, "User name: " + user.getName() + ", email " + user.getEmail());
                collectcolleges((Map<String,Object>) dataSnapshot.getValue());
            }

            private void collectcolleges(Map<String, Object> value) {
                ArrayList<Pojo> c=new ArrayList<>();
                for (Map.Entry<String, Object> entry : value.entrySet()){
                    Map singleUser = (Map) entry.getValue();
                    Pojo p=new Pojo((Boolean) singleUser.get("s1"),(Boolean) singleUser.get("s2"));
                    Toast.makeText(MainActivity.this, Boolean.toString(p.getS1()), Toast.LENGTH_SHORT).show();
                    c.add(p);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}
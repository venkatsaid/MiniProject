package com.example.user.miniproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity{
    TextView textView;
    @BindView(R.id.button)
    Button button;
    String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text1);

    }

    public void btnclick(View view) {
        string= (String) textView.getText();
        if (string.equals(getString(R.string.on))){
            textView.setText(R.string.off);
            Toast.makeText(this,string, Toast.LENGTH_SHORT).show();
        }
        else if (string.equals(R.string.off)){
            textView.setText(R.string.on);
        }
    }
}
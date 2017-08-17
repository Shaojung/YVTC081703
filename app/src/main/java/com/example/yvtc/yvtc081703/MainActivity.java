package com.example.yvtc.yvtc081703;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click1(View v)
    {

        File f = new File(getFilesDir().getAbsolutePath() + File.separator + "test1.txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write("Hello World");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void click2(View v)
    {
        File f = new File(getExternalFilesDir("Data") + File.separator + "test2.txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write("Hello World");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void click3(View v)
    {
        File exf = Environment.getExternalStorageDirectory();
        Log.d("EFILE", exf.getAbsolutePath());
        File f = new File(exf.getAbsolutePath() + File.separator + "test3.txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write("Hello World");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

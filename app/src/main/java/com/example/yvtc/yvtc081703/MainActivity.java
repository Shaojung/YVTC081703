package com.example.yvtc.yvtc081703;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

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
        int permission = ActivityCompat.checkSelfPermission(this,
                WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[] {WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                    123
            );
        }
        else
        {
            writeFile();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case 123:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //取得權限，進行檔案存取
                    writeFile();
                } else {
                    //使用者拒絕權限，停用檔案存取功能
                }
                return;
        }
    }

    private void writeFile()
    {
        Log.d("PERM", "test 123");
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

    public void clickSave(View v)
    {
        SharedPreferences sp = getSharedPreferences("MYDATA", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("DATA", "123123");
        ed.commit();
    }
    public void clickRead(View v)
    {
        SharedPreferences sp = getSharedPreferences("MYDATA", MODE_PRIVATE);
        String str = sp.getString("DATA", "000");
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Settings");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(it);
        return super.onOptionsItemSelected(item);
    }
}

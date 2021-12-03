package com.example.filieexternaldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void save(View view) throws IOException {
        EditText textEdit = findViewById(R.id.text);
        String text = textEdit.getText().toString();

        EditText filenameEdit = findViewById(R.id.filename);
        String filename = filenameEdit.getText().toString();


        File file = new File(getExternalFilesDir(null), filename);
//        File f2 = new File(file, filename);
//
//        FileWriter fw = new FileWriter(f2);
//        fw.write(text);
//        fw.close();


        FileOutputStream fos = new FileOutputStream(file);
        fos.write(text.getBytes());
        fos.close();

    }

    public void load(View view) throws IOException {
        EditText filenameEdit = findViewById(R.id.filename);
        String filename = filenameEdit.getText().toString();

        File file = new File(getExternalFilesDir(null), filename);
//        File f2 = new File(file, filename);

        BufferedReader br = new BufferedReader(new FileReader(file));
        String str, fullText = "";

        while ((str = br.readLine()) != null)
            fullText += str + "\n";

        EditText textEdit = findViewById(R.id.text);
        textEdit.setText(fullText.substring(0, fullText.length() - 1)); // 마지막 단어 하나 빼는것 \n
        br.close();
    }

    //외부저장소상태점검
    public void checkSDCard(View view) {
        String state = Environment.getExternalStorageState();
        String msg;

        if (Environment.MEDIA_MOUNTED.equals(state))
            msg = "쓰기가능";
        else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            msg = "읽기전용";
        else
            msg = "사용불가";

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
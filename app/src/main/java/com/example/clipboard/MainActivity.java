package com.example.clipboard;

//ClipData.newPlainText(CharSequence label, CharSequence text)://Copy文字數據(1,標籤名,2.要複製的文字)(回傳值ClipData)
//ClipData.getItemAt(int index):(回傳直ClipData.Item )://返回copy的數據item(第0筆)
//ClipboardManager.setPrimaryClip(@NonNull ClipData clip): //剪貼版上設定要複製的文字data(文字data)
//ClipboardManager.ClipData getPrimaryClip()://取得黏貼布

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit_input;
    TextView txt_msg;
    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.init
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        edit_input = findViewById(R.id.edit_text);
        txt_msg = findViewById(R.id.txt_msg);

    }


    public void copy(View view) {
        String copyTxt = edit_input.getText().toString();

        if (!txt_msg.equals("")) {
            ClipData clipData = ClipData.newPlainText("copTxt", copyTxt); //Copy文字數據(1,標籤名,2.要複製的文字)
            clipboardManager.setPrimaryClip(clipData); //剪貼版上設定要複製的文字data(文字data)

            Toast.makeText(this, "以複製完成", Toast.LENGTH_SHORT).show();
            Log.v("hank", "ClipData.newPlainText:" + clipData.getItemAt(0).getText().toString());
        }
    }

    public void paste(View view) {
        ClipData clipData = clipboardManager.getPrimaryClip(); //取得黏貼布
        ClipData.Item item = clipData.getItemAt(0);//返回copy的數據item(第0筆)

        txt_msg.setText(item.getText().toString());
        Toast.makeText(this, "剪貼完成", Toast.LENGTH_SHORT).show();
        Log.v("hank", "ClipData.Item: " + item.getText().toString());

    }

    public void toPage2(View view) {
        Intent getIntent = new Intent(MainActivity.this,GetActivity.class);
        startActivity(getIntent);
    }
}

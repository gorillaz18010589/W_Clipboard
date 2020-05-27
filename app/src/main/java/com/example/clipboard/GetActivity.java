package com.example.clipboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GetActivity extends AppCompatActivity {
    private TextView txt_invite_get_msg,txt_title;
    private Button btn_invite_copy;
    private String inCode;
    private ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        //1.init
        txt_invite_get_msg = findViewById(R.id.txt_invite_get_msg);
        txt_title = findViewById(R.id.txt_title);
        btn_invite_copy = findViewById(R.id.btn_invite_copy);
        inCode = txt_invite_get_msg.getText().toString();


        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        btn_invite_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copy();
            }
        });
    }

    private void copy() {
        //複製
        ClipData clipData = ClipData.newPlainText("inCode", inCode); //Copy文字數據(1,標籤名,2.要複製的文字)
        clipboardManager.setPrimaryClip(clipData); //剪貼版上設定要複製的文字data(文字data)

        Toast.makeText(this, "以複製完成", Toast.LENGTH_SHORT).show();
        Log.v("hank", "ClipData.newPlainText:" + clipData.getItemAt(0).getText().toString());

        //貼上確認是否正確
        ClipData.Item item = clipData.getItemAt(0);
        txt_title.setText(item.getText().toString());
    }

}


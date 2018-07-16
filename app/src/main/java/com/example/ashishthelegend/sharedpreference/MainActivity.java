package com.example.ashishthelegend.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button save,settext;
    private EditText editText;
    private TextView textView;
    private Switch aSwitch;
    private SeekBar seekBar;
    private ProgressBar pbbar;

    public static String text12="ABC";
    public static String switch1="switch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        save=findViewById(R.id.btn_Save);
        editText=findViewById(R.id.et_name);
        textView=findViewById(R.id.tv_yourtext);
        aSwitch=findViewById(R.id.sw_1);
        settext=findViewById(R.id.btn_settext);
        pbbar=findViewById(R.id.pb_pbbarsBar);
        seekBar=findViewById(R.id.sb_s1);
        load();

        settext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText().toString());
                pbbar.setProgress(seekBar.getProgress());
                closekeyboard();
            }
        });
     save.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             saveinfo();
         }
     });


    }

    private void closekeyboard() {
        View view=this.getCurrentFocus();
        if(view!=null)
        {
            InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    private void load() {
        SharedPreferences sharedPreferences=getSharedPreferences("Userinfo",Context.MODE_PRIVATE);
        String name=sharedPreferences.getString(text12,"ashish");
        Boolean status=sharedPreferences.getBoolean(switch1,false);
        textView.setText(name);
        aSwitch.setChecked(status);

    }

    private void saveinfo() {
        SharedPreferences sharedPreferences=getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(text12,editText.getText().toString());
        editor.putBoolean("switch",aSwitch.isChecked());
        editor.apply();
        Toast.makeText(this, "Data saved Successfully", Toast.LENGTH_SHORT).show();
    }


}

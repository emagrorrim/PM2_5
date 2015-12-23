package com.emagrorrim.pm25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private static final String className = "MainActivity";

    private EditText cityNameTextField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // Translucent navigation bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }


        cityNameTextField = (EditText)findViewById(R.id.city_name);

        findViewById(R.id.request_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryBtnAction();
            }
        });
    }



    private void queryBtnAction() {
        final String cityName = cityNameTextField.getText().toString();
        Intent itent=new Intent();
        itent.setClass(MainActivity.this, DetialActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("cityName",cityName);
        itent.putExtras(bundle);
        startActivity(itent);
        //MainActivity.this.finish();
    }



}

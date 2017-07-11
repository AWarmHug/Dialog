package com.warm.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_AlertDialog,bt_DialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_AlertDialog= (Button) findViewById(R.id.bt_AlertDialog);
        bt_DialogFragment= (Button) findViewById(R.id.bt_DialogFragment);
        bt_AlertDialog.setOnClickListener(this);
        bt_DialogFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_AlertDialog:

                break;
            case R.id.bt_DialogFragment:
                MyDialogFragment myDialogFragment=MyDialogFragment.newInstance();
                myDialogFragment.show(getSupportFragmentManager(),null);

                break;
        }

    }
}

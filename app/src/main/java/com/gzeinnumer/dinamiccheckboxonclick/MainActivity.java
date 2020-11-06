package com.gzeinnumer.dinamiccheckboxonclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> name = new ArrayList<String>();

    LinearLayout ln;
    TextView tvValue;
    TextView tvPosition;
    TextView tvTag;

    String clickedValue = "";
    String clickedPosition = "";
    String clickedTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetroServer.getInstance().createUser("").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        ln = findViewById(R.id.ln);
        tvValue = findViewById(R.id.tv_value);
        tvPosition = findViewById(R.id.tv_position);
        tvTag = findViewById(R.id.tv_tag);

        name.add("Satu");
        name.add("Dua");
        name.add("Tiga");
        name.add("Empat");

        for (int i = 0; i < name.size(); i++) {
            final CheckBox checkBox = new CheckBox(this);
            checkBox.setText(name.get(i));
            checkBox.setTag("_" + name.get(i));
            checkBox.setId(i);
            final int finalI = i;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(MainActivity.this, finalI + "_" + name.get(finalI) + "_" + checkBox.getText().toString() + "_checked", Toast.LENGTH_SHORT).show();
                        addToTextView(name.get(finalI), checkBox.getTag().toString(), finalI, true);
                    } else {
                        Toast.makeText(MainActivity.this, finalI + "_" + name.get(finalI) + "_" + checkBox.getText().toString() + "_notchecked", Toast.LENGTH_SHORT).show();
                        addToTextView(name.get(finalI), checkBox.getTag().toString(), finalI, false);
                    }
                }
            });
            ln.addView(checkBox);
        }
    }

    private void addToTextView(String value, String tag, int position, boolean isAdd) {
        if (isAdd) {
            clickedValue = clickedValue + value + ",";
            clickedPosition = clickedPosition + position + ",";
            clickedTag = clickedTag + tag + ",";
        } else {
            clickedValue = clickedValue.replace(value + ",", "");
            clickedPosition = clickedPosition.replace(position + ",", "");
            clickedTag = clickedTag.replace(tag + ",", "");
        }
        if (clickedValue.length() > 0 && clickedPosition.length() > 0 && clickedTag.length() > 0) {
            tvValue.setText(clickedValue.substring(0, clickedValue.length() - 1));
            tvPosition.setText(clickedPosition.substring(0, clickedPosition.length() - 1));
            tvTag.setText(clickedTag.substring(0, clickedTag.length() - 1));
        } else{
            tvValue.setText("");
            tvPosition.setText("");
            tvTag.setText("");
        }

    }
}
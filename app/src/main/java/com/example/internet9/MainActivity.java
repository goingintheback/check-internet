package com.example.internet9;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageButton myButton;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        myButton = (ImageButton) findViewById(R.id.imageButton);
        constraintLayout = (ConstraintLayout) findViewById(R.id.main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void onClick(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()) {
            myButton.setVisibility(View.INVISIBLE);
            constraintLayout.setBackground(getDrawable(R.drawable.online));
            Toast toast1 = Toast.makeText(getApplicationContext(), "Подключение есть \n Добро пожаловать в приложение", Toast.LENGTH_LONG);
            toast1.show();
        }else {
            myButton.setVisibility(View.INVISIBLE);
            constraintLayout.setBackground(getDrawable(R.drawable.offline));
            Toast toast2 = Toast.makeText(getApplicationContext(), "Нет подключения \n Разрешите доступ и повторите попытку", Toast.LENGTH_LONG);
            toast2.show();
        }
    }
}
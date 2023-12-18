package ru.studyproject.weatherapplication.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.Locale;

import ru.studyproject.weatherapplication.R;
import ru.studyproject.weatherapplication.domain.Hourly;
import ru.studyproject.weatherapplication.ui.adapters.HourlyAdapter;

public class MainActivity extends AppCompatActivity {

    private static int MIN_VALUE = -60;
    private static int MAX_VALUE = 60;

    private String tempState = "C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycleView();

        SimpleDateFormat formatter= new SimpleDateFormat("dd MMM '|' HH:mm", new Locale("ru"));

        TextView date = findViewById(R.id.label_date);
        date.setText(formatter.format(new Date((System.currentTimeMillis()))));

        TextView label_temp = findViewById(R.id.label_temp);
        double tempValue = (Math.random() * (MAX_VALUE - MIN_VALUE )) + MIN_VALUE;
        label_temp.setText(String.format("%.0f", tempValue) + "°C");

        ImageButton updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(v -> {
            date.setText(formatter.format(new Date((System.currentTimeMillis()))));
            double tV = (Math.random() * (MAX_VALUE - MIN_VALUE )) + MIN_VALUE;
            switch (tempState){
                case "F":
                    tV = tV * 9 / 5 + 32;
                    break;
                case "K":
                    tV = tV + 273.15;
                    break;
            }
            label_temp.setText(String.format("%.2f",tV) + "°" + tempState);
        });

        Button convertFahrenheitBtn = findViewById(R.id.convert_to_fahrenheit_btn);
        Button convertCelsiusBtn = findViewById(R.id.convert_to_celsius_btn);
        Button convertKelvinBtn = findViewById(R.id.convert_to_kelvin_btn);

        convertFahrenheitBtn.setOnClickListener(v -> {
            try {
                String t = label_temp.getText().toString().split("°")[0].replace(",", ".");
                double newTemp = Double.parseDouble(t);
                switch (tempState){
                    case "C":
                        newTemp = newTemp * ((double) 9 / 5) + 32;
                        break;
                    case "K":
                        newTemp = (newTemp - 273.15) * ((double) 9 / 5) + 32;
                        break;
                }
                tempState = "F";
                label_temp.setText(String.format("%.2f",newTemp) + "°F");
            } catch (NumberFormatException e){
                Log.d("ERROR", e.toString());
            }

        });

        convertCelsiusBtn.setOnClickListener(v -> {
            try {
                String t = label_temp.getText().toString().split("°")[0].replace(",", ".");
                double newTemp = Double.parseDouble(t);
                switch (tempState){
                    case "F":
                        newTemp = (newTemp - 32) * ((double) 5 / 9);
                        break;
                    case "K":
                        newTemp = newTemp - 273.15;
                        break;
                }
                tempState = "C";
                label_temp.setText(String.format("%.2f",newTemp) + "°C");
            } catch (NumberFormatException e){
                Log.d("ERROR", e.toString());
            }
        });

        convertKelvinBtn.setOnClickListener(v -> {
            try {
                String t = label_temp.getText().toString().split("°")[0].replace(",", ".");
                double newTemp = Double.parseDouble(t);
                switch (tempState){
                    case "F":
                        newTemp = (newTemp - 32) * ((double) 5 /9) + 273.15;
                        break;
                    case "C":
                        newTemp = newTemp + 273.15;
                        break;
                }
                tempState = "K";
                label_temp.setText(String.format("%.2f", newTemp) + "°K");
            } catch (NumberFormatException e){
                Log.d("ERROR", e.toString());
            }

        });

        TextView windValue = findViewById(R.id.text_value_wind);
        windValue.setText("7 м/с");

        TextView humidityValue = findViewById(R.id.text_value_humidity);
        humidityValue.setText("83%");
    }

    private void initRecycleView(){
        ArrayList<Hourly> items = new ArrayList<>();

        items.add(new Hourly("18:00", 5, "cloudy"));
        items.add(new Hourly("19:00", 5, "cloudy"));
        items.add(new Hourly("20:00", 4, "cloudy"));
        items.add(new Hourly("21:00", 3, "rainy"));
        items.add(new Hourly("22:00", 1, "rainy"));

        RecyclerView recyclerView = findViewById(R.id.recycle_view_week);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        RecyclerView.Adapter adapter = new HourlyAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}
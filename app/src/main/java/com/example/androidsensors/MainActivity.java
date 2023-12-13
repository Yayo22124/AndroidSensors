package com.example.androidsensors;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvValues;
    // Sensors
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private SensorEventListener accelerometerEventListener;
    private long x,y,z;
    private String direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buscar por ID
        tvValues = findViewById(R.id.tv_values);

        // Administrador de sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelerometerEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // Registrar los valores en X (Vienen en RAD's por ello se pasa a grados)
                x = Math.round(Math.toDegrees(event.values[0]));
                y = Math.round(Math.toDegrees(event.values[1]));
                z = Math.round(Math.toDegrees(event.values[2]));
                if (x < -200)
                    direction = "↑";
                else if (x > 280)
                    direction = "↓";
                else if (y < -200)
                    direction = "←";
                else if (y > 250)
                    direction = "→";
                else
                    direction = "■";

                tvValues.setText(direction);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        // Register
        sensorManager.registerListener(accelerometerEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);


    }
}
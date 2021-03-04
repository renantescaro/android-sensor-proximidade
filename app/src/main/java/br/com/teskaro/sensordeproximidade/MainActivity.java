package br.com.teskaro.sensordeproximidade;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView txvLongeOuPerto;
    private TextView txvSensor;
    private SensorManager sensorManager;
    private Sensor sensorDeProximidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvLongeOuPerto = findViewById(R.id.txvLongeOuPerto);
        txvSensor       = findViewById(R.id.txvSensor);

        txvLongeOuPerto.setText(R.string.txv_longe);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorDeProximidade = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(new SensorDeProximidadeListener(),
                                        sensorDeProximidade,
                                        SensorManager.SENSOR_DELAY_UI);
    }

    class SensorDeProximidadeListener implements SensorEventListener{
        @Override
        public void onSensorChanged(SensorEvent event) {
            float valor = event.values[0];
            txvSensor.setText(String.valueOf(valor));
            if (valor > 5){
                txvLongeOuPerto.setText(R.string.txv_longe);
            }else{
                txvLongeOuPerto.setText(R.string.txv_perto);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
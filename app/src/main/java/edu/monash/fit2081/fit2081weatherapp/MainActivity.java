package edu.monash.fit2081.fit2081weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private WeatherItem weatherItem;
    private EditText location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location = findViewById(R.id.locationTextBox);
    }

    public void onClick(View v) {
        makeRequest();
    }

    private void makeRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String locationString = location.getText().toString();
        String url = "https://api.weatherapi.com/v1/current.json?key=cde75afa40ce4179b2781925223003&q=" + locationString;

        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                TextView temperatureText = findViewById(R.id.temp);
                                TextView precipitationText = findViewById(R.id.precip);
                                TextView humidityText = findViewById(R.id.humid);
                                TextView windText = findViewById(R.id.wind);

                                String temperature;
                                String wind;
                                String precipitation;
                                String humidity;
                                try {
                                    JSONObject listItems = response.getJSONObject("current");
                                    temperature = listItems.getString("temp_c");
                                    wind = listItems.getString("wind_kph");
                                    precipitation = listItems.getString("precip_mm");
                                    humidity = listItems.getString("humidity");
                                    weatherItem = new WeatherItem(temperature, precipitation, humidity, wind);

                                    temperatureText.setText(weatherItem.getTemperature());
                                    precipitationText.setText(weatherItem.getPrecipitation());
                                    humidityText.setText(weatherItem.getHumidity());
                                    windText.setText(weatherItem.getWind());
                                } catch (Exception e) {
                                    Log.d("weather", e.getMessage());
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("weather", error.getMessage());
                    }
                });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(stringRequest);
    }
}
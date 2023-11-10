package com.app.weather_app

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.app.weather_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//e56960eb7d42e8dbe0f070fc0f09ccaf

class MainActivity : AppCompatActivity() {
    private  val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetachWeatherData()
    }

    private fun fetachWeatherData() {
        val retrofit= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(Apiinterface::class.java)
        val response = retrofit.getWeatherData("dhaka","e56960eb7d42e8dbe0f070fc0f09ccaf","metric")
        response.enqueue(object :Callback<WeatherApp>{
            override fun onResponse(call: Call<WeatherApp>, response: Response<WeatherApp>) {
               val responseBody=response.body()
                if(response.isSuccessful && responseBody != null){
                    val tempreture = responseBody.main.temp.toString()
                    val tempmax = responseBody.main.temp_max
                    val tempmin = responseBody.main.temp_min
                    val humudity= responseBody.main.humidity
                    val windspeed = responseBody.wind.speed
                    val sunRise = responseBody.sys.sunrise
                    val sunset = responseBody.sys.sunset
                    val seaLevel = responseBody.main.pressure
                    val condition = responseBody.weather.firstOrNull()?.main?:"unknow"

                    binding.maxtemp.text="$tempmax °C"
                    binding.mintemp.text="$tempmin °C"
                    binding.temp.text= "$tempreture °C"
                    binding.weather.text=condition
                    binding.Humidity.text="$humudity %"
                    binding.Windspeed.text="$windspeed m/s"
                    binding.Sunrise.text="$sunRise"
                    binding.Sunset.text="$sunset"
                    binding.Sea.text="$seaLevel pHa"
                    binding.maxtemp.text="$tempmax"
                    binding.condition.text=condition


                   // Log.d("TAG", "onResponse: $tempreture")
                }
            }

            override fun onFailure(call: Call<WeatherApp>, t: Throwable) {

            }

        })
    }
}
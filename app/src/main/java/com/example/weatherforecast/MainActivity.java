package com.example.weatherforecast;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherforecast.bean.WeatherBean;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvLocation, tvCond_txt, tvTmp, tvWind_sc, tvWind_dir;
    private EditText et;
    private ImageView ivIcon;
    WeatherBean weatherBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getCityData("青岛");//第一次进入应用时，显示青岛的天气
    }

    private void initView() {
        tvLocation = findViewById(R.id.tv_location);
        tvCond_txt = findViewById(R.id.tv_cond_txt);
        tvTmp = findViewById(R.id.tv_tmp);
        tvWind_sc = findViewById(R.id.tv_wind_sc);
        tvWind_dir = findViewById(R.id.tv_wind_dir);
        ivIcon = findViewById(R.id.iv_icon);
        findViewById(R.id.btn_search).setOnClickListener(this);
        et = findViewById(R.id.et);
    }

    /**
     * 根据城市获取对应的天气信息
     */
    private void getCityData(String city) {
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 2 创建一个请求
        String url = "https://free-api.heweather.net/s6/weather/now?location=" + city + "&key=3c3fa198cacc4152b94b20def11b2455";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                weatherBean = JSonParser.parse(jsonObject.toString());
                setData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "请求失败！", Toast.LENGTH_SHORT).show();
            }
        });
        // 3 将创建的请求添加到请求队列中
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {  //查询按钮的点击事件
        switch (v.getId()) {
            case R.id.btn_search:
                getCityData(et.getText().toString());
                break;
        }
    }

    /**
     * 设置界面数据
     */
    private void setData() {
        String location = weatherBean.getHeWeather6().get(0).getBasic().getLocation();
        String cond_txt = weatherBean.getHeWeather6().get(0).getNow().getCond_txt();
        String tmp = weatherBean.getHeWeather6().get(0).getNow().getTmp();
        String wind_sc = weatherBean.getHeWeather6().get(0).getNow().getWind_sc();
        String wind_dir = weatherBean.getHeWeather6().get(0).getNow().getWind_dir();
        tvLocation.setText(location);
        tvCond_txt.setText(cond_txt);
        tvTmp.setText(tmp + "°C");
        tvWind_sc.setText("风力：" + wind_sc + "级");
        tvWind_dir.setText("风向：" + wind_dir);
        if (("晴转多云").equals(cond_txt)) {
            ivIcon.setImageResource(R.drawable.cloud_sun);
        } else if (("多云").equals(cond_txt)) {
            ivIcon.setImageResource(R.drawable.clouds);
        } else if (("晴").equals(cond_txt)) {
            ivIcon.setImageResource(R.drawable.sun);
        }
    }
}


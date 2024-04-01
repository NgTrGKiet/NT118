package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNum1, editTextNum2;
    private Button buttonCalculate;
    private TextView textViewResult;
    private static final String BASE_URL = "http://192.168.0.200:3000/api/v1"; // Change to your server URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        int num1 = Integer.parseInt(editTextNum1.getText().toString());
        int num2 = Integer.parseInt(editTextNum2.getText().toString());

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("num1", num1);
            requestBody.put("num2", num2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(requestBody);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        System.out.println(requestQueue);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Kiểm tra nếu response là một số nguyên
                            int result = response.getInt("result");
                            textViewResult.setText("Result: " + result);
                        } catch (JSONException e) {
                            // Nếu có lỗi khi chuyển đổi thành số nguyên, hiển thị thông báo lỗi
                            e.printStackTrace();
                            textViewResult.setText("Error occurred!");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewResult.setText(error.toString());
                System.out.println(error.toString());

            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}

package com.example.weedwackerapp.Controller;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weedwackerapp.Model.Register;
import com.example.weedwackerapp.Model.Url;
import com.example.weedwackerapp.Model.User;
import com.example.weedwackerapp.security.HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCustomer {
    private RequestQueue _queue;
    private Context _context;
    private Url _url;

    public ServiceCustomer(Context context){
        HttpsTrustManager.allowAllSSL();
        _context=context;
        _queue= Volley.newRequestQueue(_context);
        _url=new Url();
    }




    public void getCustomerOffer(Register register){
        String url = _url.customerOffer+register.getId();
        List<String> jsonResponses = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //String email = jsonObject.getString("email");
                        System.out.println(jsonObject.toString());
                        System.out.println("--------------");
                        //jsonResponses.add(email);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " +register.getToken() );
                return headers;
            }
        };

        _queue.add(jsonObjectRequest);

    }

    public void test(Register register){
        JsonObjectRequest jsonObejct = new JsonObjectRequest(Request.Method.GET, _url.customerOffer+register.getId(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println("The Response "+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+register.getToken());
                return params;
            }
        };
        _queue.add(jsonObejct);
    }

}

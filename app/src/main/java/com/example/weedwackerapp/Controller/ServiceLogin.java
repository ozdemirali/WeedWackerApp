package com.example.weedwackerapp.Controller;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weedwackerapp.Model.Url;
import com.example.weedwackerapp.Model.User;
import com.example.weedwackerapp.security.HttpsTrustManager;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceLogin {
    private RequestQueue _queue;
    private Context _context;
    private Url _url;

    public ServiceLogin(Context context){
        HttpsTrustManager.allowAllSSL();
        _context=context;
        _queue= Volley.newRequestQueue(_context);
        _url=new Url();
    }

    public void PostLogin(User user,MyBean bean) {
        JsonObjectRequest _jsonObjectRequest = null;
        try {
            JSONObject _jsonObject = new JSONObject();
            //_jsonObject.put("email","admin");
            //_jsonObject.put("password","123456");
            _jsonObject.put("email",user.getEmail());
            _jsonObject.put("password",user.getPassword());

            _jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, _url.login, _jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //System.out.println(response);
                            try {
                                //System.out.println(response);
                                //System.out.println(response.getString("token"));
                                bean.setMyProperty(response.getString("token"));
//                                if(response.getString("token").isEmpty()){
//                                    System.out.println("Boş");
//                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(_context, "Hastalık kayıt edilemedi", Toast.LENGTH_SHORT).show();
                    //progressDialog.dismiss();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        _queue.add(_jsonObjectRequest);
    }

}

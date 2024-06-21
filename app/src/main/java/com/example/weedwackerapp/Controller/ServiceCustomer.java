package com.example.weedwackerapp.Controller;

import android.content.Context;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weedwackerapp.Model.CustomerOffer;
import com.example.weedwackerapp.Model.Register;
import com.example.weedwackerapp.Model.Url;
import com.example.weedwackerapp.Model.User;
import com.example.weedwackerapp.R;
import com.example.weedwackerapp.adapter.MyListAdapter;
import com.example.weedwackerapp.security.HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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




    public void getCustomerOffer(ListView listView,List<CustomerOffer> customerOfferList){
        String url = _url.customerOffer+Register.getId();
        ArrayList<CustomerOffer> customerOffers=new ArrayList<CustomerOffer>();

        ArrayList<String> mainTitle=new ArrayList<String>();
        ArrayList<String> subTitle=new ArrayList<String>();
        ArrayList<Integer>imgId=new ArrayList<Integer>();
        List<String> jsonResponses = new ArrayList<>();

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for(int i = 0; i < response.length(); i++){
                       // System.out.println(response);
                        JSONObject obj = response.getJSONObject(i);
                         //System.out.println(obj.toString());
                        //System.out.println("--------------");
                        mainTitle.add(obj.getString("user"));
                        subTitle.add(obj.getString("price")+ " TL - "+obj.getString("phone"));
                        imgId.add(R.drawable.icon_home);

                        CustomerOffer customerOffer=new CustomerOffer();
                        customerOffer.setUser(obj.getString("user"));
                        customerOffer.setPhone(obj.getString("phone"));
                        customerOffer.setPrice(Double.valueOf(obj.getString("price")));
                        customerOffer.setStartTime(convertDate(obj.getString("startTime")));
                        customerOffer.setEndTime(convertDate(obj.getString("endTime")));
                        customerOfferList.add(customerOffer);
                    }

                    MyListAdapter adapter=new MyListAdapter(_context, mainTitle, subTitle, imgId);
                    listView.setAdapter(adapter);



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
                headers.put("Authorization", "Bearer " +Register.getToken() );
                return headers;
            }
        };

        _queue.add(jsonObjectRequest);

    }


    //This method get all PlateCode and Name of City from Database
    public void getCity( Spinner spinner,List<Integer> plateCode){
        String url = _url.getCity;
       List<String> spinnerArray=new ArrayList<String>();


        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for(int i = 0; i < response.length(); i++){
                        // System.out.println(response);
                        JSONObject obj = response.getJSONObject(i);
                        //System.out.println(obj.toString());
                        System.out.println("--------------");
                        System.out.println(obj.getString("plateCode"));
                        spinnerArray.add(obj.getString("name"));
                        plateCode.add(Integer.parseInt(obj.getString("plateCode")));

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(_context, android.R.layout.simple_spinner_item, spinnerArray);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);





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
                headers.put("Authorization", "Bearer " +Register.getToken() );
                return headers;
            }
        };

        _queue.add(jsonObjectRequest);

    }

    public void getDistrict( Spinner spinner,List<Integer> districtId,Integer plateCode){
        String url = _url.getDistrict+plateCode;
        List<String> spinnerArray=new ArrayList<String>();


        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONArray response) {
                try {
                    System.out.println("Döngüden önce");
                    System.out.println(response.toString());
                    for(int i = 0; i < response.length(); i++){
                        // System.out.println(response);
                        JSONObject obj = response.getJSONObject(i);
                        System.out.println("service getDistrict");
                        //System.out.println(obj.toString());
                        System.out.println(obj.getString("id"));
                        System.out.println(obj.getString("name"));
                        System.out.println("--------------");
                        spinnerArray.add(obj.getString("name"));
                        districtId.add(Integer.parseInt(obj.getString("id")));

                    }

                       ArrayAdapter<String> adapter = new ArrayAdapter<String>(_context, android.R.layout.simple_spinner_item, spinnerArray);
                       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                       spinner.setAdapter(adapter);


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
                headers.put("Authorization", "Bearer " +Register.getToken() );
                return headers;
            }
        };

        _queue.add(jsonObjectRequest);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private String convertDate(String inputTime){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDate date = LocalDate.parse(inputTime, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String outputString = date.format(outputFormatter);
        return outputString;

    }


}

package com.example.weedwackerapp.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
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
import com.example.weedwackerapp.Model.DataPart;
import com.example.weedwackerapp.Model.Register;
import com.example.weedwackerapp.Model.Url;
import com.example.weedwackerapp.Model.User;
import com.example.weedwackerapp.Model.Work;
import com.example.weedwackerapp.R;
import com.example.weedwackerapp.adapter.MyListAdapter;
import com.example.weedwackerapp.security.HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
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

                    if(Register.getApply())
                    {
                        int spinnerPosition=adapter.getPosition(Register.getCity());
                        spinner.setSelection(spinnerPosition);
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
                headers.put("Authorization", "Bearer " +Register.getToken() );
                return headers;
            }
        };

        _queue.add(jsonObjectRequest);

    }

    public void getDistrict( Spinner spinner,List<Integer> districtId,Integer plateCode){
        String url = _url.getDistrictByPlateCode+plateCode;
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
                        //System.out.println(obj.getString("id"));
                        //System.out.println(obj.getString("name"));
                        //System.out.println("--------------");
                        spinnerArray.add(obj.getString("name"));
                        districtId.add(Integer.parseInt(obj.getString("id")));

                    }

                       ArrayAdapter<String> adapter = new ArrayAdapter<String>(_context, android.R.layout.simple_spinner_item, spinnerArray);
                       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                       spinner.setAdapter(adapter);

                    System.out.println("Get District ");
                    //System.out.println(Register.getDistrict());

                    if(Register.getApply())
                    {
                        System.out.println("Set District worked");
                        int spinnerPosition = adapter.getPosition(Register.getDistrict());
                        spinner.setSelection(spinnerPosition);
                        Register.setApply(false);
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
                headers.put("Authorization", "Bearer " +Register.getToken() );
                return headers;
            }
        };

        _queue.add(jsonObjectRequest);

    }


    //This method get all PlateCode and Name of City from Database
    public void getAddress(String userId){
        String url = _url.getAddressById+userId;
//        List<String> spinnerArray=new ArrayList<String>();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Get Address");
                System.out.println(response);
                try {

                    System.out.println(Register.getApply());
                    Register.setAddInfo(response.getString("addInfo"));
                    Register.setCity(response.getString("city"));
                    Register.setDistrict(response.getString("district"));
                    Register.setPostCode("postCode");
                    Register.setPhone(response.getString("phone"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//
//                    response.getString(Integer.parseInt("city"));
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


    //Upload image
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void uploadBitmap(final Bitmap bitmap, Work work){
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, _url.sendImage,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            System.out.println("Upload Method");
                            System.out.println(obj.getString("fileName"));
                            Toast.makeText(_context.getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            //agriculturalDisease.setImageName(obj.getString("fileName"));
                            //PostProduct(agriculturalDisease);
                            work.setImage(obj.getString("fileName"));
                            postWork(work);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                        //Toast.makeText(_context.getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })


              {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
                  @Override
                  public Map<String, String> getHeaders() throws AuthFailureError {
                      HashMap<String, String> headers = new HashMap<String, String>();
                      headers.put("Authorization", "Bearer " +Register.getToken() );
                      return headers;
                  }

        };
        Volley.newRequestQueue(_context).add(volleyMultipartRequest);
    }


    public void postWork(Work work){
        JsonObjectRequest _jsonObjectRequest = null;
        try {
            JSONObject _jsonObject = new JSONObject();
            //_jsonObject.put("email","admin");
            //_jsonObject.put("password","123456");
            _jsonObject.put("plateDode",work.getPlateCode());
            _jsonObject.put("districtId",work.getDistrictId());
            _jsonObject.put("description",work.getDescription());
            _jsonObject.put("image",work.getImage());
            _jsonObject.put("userId",Register.getId());


            _jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, _url.postWork, _jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //System.out.println(response);

                            System.out.println("Post Work");
                            try {
                                System.out.println(response.getString("status"));
                                Toast.makeText(_context, response.getString("status") , Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error.getMessage());
                    Toast.makeText(_context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    //progressDialog.dismiss();
                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer " +Register.getToken() );
                    return headers;
                }
            };
        } catch (JSONException e) {
            e.printStackTrace();
        }
        _queue.add(_jsonObjectRequest);
    }
}


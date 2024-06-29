package com.example.weedwackerapp.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.weedwackerapp.Controller.ServiceCustomer;
import com.example.weedwackerapp.Model.Register;
import com.example.weedwackerapp.Model.Work;
import com.example.weedwackerapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddWorkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddWorkFragment extends Fragment {

    private Context _context;
    private Bitmap bitmap;

    private Spinner spinnerCity;
    private Spinner spinnerDistrict;
    private List<Integer> plateCode;
    private List<Integer> districtId;
    private ServiceCustomer _serviceCustomer;
    private Work _work;

    public static final int RequestPermissionCode=1;
    ActivityResultLauncher<Intent> activityResultLauncher;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddWorkFragment(Context context) {
        this._context=context;
        // Required empty public constructor
    }

    public AddWorkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddWorkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddWorkFragment newInstance(String param1, String param2) {
        AddWorkFragment fragment = new AddWorkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_add_work,container,false);

        ImageView imageView=view.findViewById(R.id.imageView);
        EditText description=view.findViewById(R.id.editTextAddDescription);
        Button save=view.findViewById(R.id.butonAddWork);

        spinnerCity=view.findViewById(R.id.spinnerAddCity);
        plateCode=new ArrayList<Integer>();

        spinnerDistrict=view.findViewById(R.id.spinnerAddDistrict);
        districtId=new ArrayList<Integer>();




        _serviceCustomer=new ServiceCustomer(_context);
        _serviceCustomer.getCity(spinnerCity,plateCode);

        _work=new Work();

        
        EnableRuntimePermission();



        //Receiver
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                try {
                    //System.out.println("result Code");
                    //System.out.println(result.getResultCode());
                    //System.out.println(result.getData());
                    if (result.getResultCode() == -1 && result.getData()!=null) {
                        Bundle bundle= result.getData().getExtras();
                        bitmap = (Bitmap) bundle.get("data");
                        imageView.setImageBitmap(bitmap);


                    }
                }catch (Exception e){

                    System.out.println(e.getMessage());
                }

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Tıklnadı");

                try {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(intent.resolveActivity(_context.getPackageManager())!=null){
                        activityResultLauncher.launch(intent);

                    }else{
                        activityResultLauncher.launch(intent);
                        Toast.makeText(_context,"There is no app that support this section",Toast.LENGTH_SHORT).show();
                    }
                }
                 catch (Exception e){
                     System.out.println(e.getMessage());
                 }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Tıklandı");
                //Static Test
                System.out.println("Add Work Static test");
                //System.out.println(Register.getToken());
                //System.out.println(Register.getId());

                if(bitmap==null) {
                    //System.out.println(bitmap);
                    Toast.makeText(_context, "Resim Çekmelisiniz", Toast.LENGTH_SHORT).show();
                }else if(description.getText().toString().isEmpty()) {
                    Toast.makeText(_context, "İşi Tanımlamalısınız", Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("Tamam");
                    _work.setDescription(description.getText().toString());
                    _serviceCustomer.uploadBitmap(bitmap,_work);
                }

            }
        });

       spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
               System.out.println("City spinner selected");
               System.out.println(position);
               System.out.println(plateCode.get(position));

               _work.setPlateCode(plateCode.get(position));

               _serviceCustomer.getDistrict(spinnerDistrict,districtId,plateCode.get(position));
               Toast.makeText(_context, plateCode.get(position).toString(), Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

       spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
               _work.setDistrictId(districtId.get(position));
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        // Inflate the layout for this fragment
        return view;
    }


    // Camera
    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) _context,
                Manifest.permission.CAMERA)) {
            Toast.makeText(_context,"CAMERA permission allows us to Access CAMERA app",Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions((Activity) _context,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        super.onRequestPermissionsResult(requestCode, permissions, result);
        switch (requestCode) {
            case RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(_context, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(_context, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    //Camera End


}
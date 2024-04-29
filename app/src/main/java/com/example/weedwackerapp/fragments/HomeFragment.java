package com.example.weedwackerapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.media.session.PlaybackState;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weedwackerapp.Controller.ListenerCustomerOffer;
import com.example.weedwackerapp.Controller.MyBean;
import com.example.weedwackerapp.Controller.ServiceCustomer;
import com.example.weedwackerapp.MainActivity;
import com.example.weedwackerapp.Model.CustomerOffer;
import com.example.weedwackerapp.Model.Register;
import com.example.weedwackerapp.R;
import com.example.weedwackerapp.RegisterActivity;
import com.example.weedwackerapp.adapter.MyListAdapter;
import com.example.weedwackerapp.dialog.CustomerOfferDialog;
import com.example.weedwackerapp.dialog.InfoAlertDialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private Register _register;
    private Context _context;
    private ServiceCustomer _serviceCustomer;
    private List<CustomerOffer> customerOfferList;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment(Register register, Context context) {
        this._register=register;
        this._context=context;
        // Required empty public constructor
    }

    public HomeFragment() {

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        //accept Listview From this fragment
        View  view = inflater.inflate(R.layout.fragment_home, container, false);


        ListView list=view.findViewById(R.id.list);
        customerOfferList=new ArrayList<CustomerOffer>();


        _serviceCustomer=new ServiceCustomer(_context);
        _serviceCustomer.getCustomerOffer(getContext(),_register,list,customerOfferList);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //System.out.println(i);
                //System.out.println(customerOfferList.get(i).getUser());
                //new InfoAlertDialog(customerOfferList.get(i)).show(getChildFragmentManager(),"asdd");
                new CustomerOfferDialog(customerOfferList.get(i)).show(getChildFragmentManager(),"Offer");

            }

        });

        return  view;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
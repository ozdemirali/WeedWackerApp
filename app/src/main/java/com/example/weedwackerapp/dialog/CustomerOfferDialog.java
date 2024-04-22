package com.example.weedwackerapp.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.weedwackerapp.Model.CustomerOffer;
import com.example.weedwackerapp.R;

public class CustomerOfferDialog extends DialogFragment {
    private CustomerOffer _customerOffer;
    private TextView txtCompanyName;
    private TextView txtWorkStartTime;
    private TextView txtWorkEndTime;
    private TextView txtWorkPrice;

    public CustomerOfferDialog(CustomerOffer customerOffer){
        this._customerOffer=customerOffer;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.customer_dialog_offer,null);

        //A sample is done to change a value of textview.
        txtCompanyName=(TextView)view.findViewById(R.id.txtCompanyName);
        txtWorkStartTime=(TextView)view.findViewById(R.id.txtWorkStartTime);
        txtWorkEndTime=(TextView)view.findViewById(R.id.txtWorkEndTime);
        txtWorkPrice=(TextView)view.findViewById(R.id.txtWorkPrice);
        txtCompanyName.setText(_customerOffer.getUser());
        txtWorkStartTime.setText(_customerOffer.getStartTime());
        txtWorkEndTime.setText(_customerOffer.getEndTime());
        txtWorkPrice.setText(String.valueOf(_customerOffer.getPrice())+" TL");



        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setNeutralButton("Geri", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })

                .setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                });
        return builder.create();
    }

}

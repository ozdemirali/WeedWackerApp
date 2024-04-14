package com.example.weedwackerapp.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.weedwackerapp.Model.CustomerOffer;
import com.example.weedwackerapp.R;

public class InfoAlertDialog extends DialogFragment {

    private CustomerOffer _customerOffer;
    private TextView text;

    public InfoAlertDialog(CustomerOffer customerOffer){
        this._customerOffer=customerOffer;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_offer,null);

        //A sample is done to change a value of textview.
        text=(TextView)view.findViewById(R.id.offerWork);
        System.out.println(text.getText().toString());
        text.setText("Hello World!");
        text.setTextColor(Color.RED);



        System.out.println("Info Alert Dialog");
        System.out.println(_customerOffer.getUser());
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
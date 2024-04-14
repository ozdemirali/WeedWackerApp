package com.example.weedwackerapp.Controller;

import com.example.weedwackerapp.Model.CustomerOffer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ListenerCustomerOffer {
    public static ArrayList<CustomerOffer> myProperty;

    public ArrayList<CustomerOffer> getMyProperty() {
        return myProperty;
    }

    public void setMyProperty(ArrayList<CustomerOffer> myProperty) {
        ArrayList<CustomerOffer> oldVal=this.myProperty;
        this.myProperty = myProperty;
        firePropertyChange("myProperty", oldVal, myProperty);
    }

    private PropertyChangeListener listener;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.listener = listener;
    }

    public void firePropertyChange(String propertyName, ArrayList<CustomerOffer> oldValue, ArrayList<CustomerOffer> newValue) {
        if (listener != null) {
            listener.propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
        }
    }
}

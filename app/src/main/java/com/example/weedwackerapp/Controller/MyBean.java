package com.example.weedwackerapp.Controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyBean {
    public static String myProperty;

    // Getter and setter for myProperty
    public String getMyProperty() {
        return myProperty;
    }

    public void setMyProperty(String myProperty) {
        String oldVal = this.myProperty;
        this.myProperty = myProperty;
        firePropertyChange("myProperty", oldVal, myProperty);
    }

    private PropertyChangeListener listener;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.listener = listener;
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (listener != null) {
            listener.propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
        }
    }
}

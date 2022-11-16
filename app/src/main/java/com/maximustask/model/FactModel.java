package com.maximustask.model;

import androidx.lifecycle.MutableLiveData;

public class FactModel extends MutableLiveData<FactModel> {

    String fact;
    int length;

    public FactModel() {
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

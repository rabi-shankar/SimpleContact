package com.rabi.simplecontacts.model;

public class Contact {

    private String mFirstName; // first name of the contact
    private String mLastName;  // last name of the contact
    private String mFullName;  // full name of the contact
    private String mNumber;    // mobile number of the contact

    public Contact() {
    }

    public Contact(String mFirstName, String mLastName, String mFullName, String mNumber) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mFullName = mFullName;
        this.mNumber = mNumber;
    }

    public Contact(String mFullName, String mNumber) {
        this.mFullName = mFullName;
        this.mNumber = mNumber;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }
}

package com.rabi.simplecontacts.model;

public class Message {

    private Contact contact;
    private String OTP;
    private String timestamp;

    public Message() {
    }

    public Message(Contact contact, String OTP, String timestamp) {
        this.contact = contact;
        this.OTP = OTP;
        this.timestamp = timestamp;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

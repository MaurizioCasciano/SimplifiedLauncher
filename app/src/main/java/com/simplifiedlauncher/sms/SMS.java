package com.simplifiedlauncher.sms;

import java.util.Date;

/**
 * Created by Maurizio on 20/01/2017.
 */

public class SMS {
    private Date date;
    private String number;
    private String body;
    private int type;

    public SMS(Date date, String number, String body, int type) {
        this.date = date;
        this.number = number;
        this.body = body;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SMS{" +
                "date=" + date +
                ", number='" + number + '\'' +
                ", body='" + body + '\'' +
                ", type=" + type +
                '}';
    }
}

package com.simplifiedlauncher.home;

/**
 * Created by Maurizio on 27/01/2017.
 */

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Carmine on 21/01/2017.
 */

public class Messaggio implements Serializable {

    private String nomeContatto;
    private String testo;
    private Long data;
    private Boolean inviato;
    private Boolean letto;

    public Messaggio(String nomeContatto, String testo, Long data, Boolean inviato, Boolean letto) {
        this.nomeContatto = nomeContatto;
        this.testo = testo;
        this.data = data;
        this.inviato = inviato;
        this.letto = letto;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Long getDataInMillis() {
        return data;
    }

    public void setDataInMillis(Long data) {
        this.data = data;
    }

    public Boolean getInviato() {
        return inviato;
    }

    public void setInviato(Boolean inviato) {
        this.inviato = inviato;
    }

    public String getNomeContatto() {
        return nomeContatto;
    }

    public String getData() {
        int year, month, day;
        String dataMessaggio = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data);
        year = calendar.get(Calendar.YEAR);
        month = 1 + calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dataMessaggio = Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
        return dataMessaggio;
    }

    public String getOra() {
        int hour, minutes;
        String ora = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);
        if (minutes < 10) {
            ora = Integer.toString(hour) + ":0" + Integer.toString(minutes);
        } else {
            ora = Integer.toString(hour) + ":" + Integer.toString(minutes);
        }
        return ora;

    }

    public void setNomeContatto(String nomeContatto) {
        this.nomeContatto = nomeContatto;
    }

    public Boolean isLetto() {
        return letto;
    }

    public void setLetto(Boolean letto) {
        this.letto = letto;
    }
}


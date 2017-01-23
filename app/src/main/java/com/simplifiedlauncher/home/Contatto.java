package com.simplifiedlauncher.home;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Carmine on 18/01/2017.
 */

public class Contatto implements Serializable {

    private String nome;
    private String numero;
    private ArrayList<Messaggio> elencoMessaggi;


    public Contatto(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
        this.elencoMessaggi=new ArrayList<Messaggio>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ArrayList<Messaggio> getElencoMessaggi() {
        return elencoMessaggi;
    }

    public void setElencoMessaggi(ArrayList<Messaggio> elencoMessaggi) {
        this.elencoMessaggi = elencoMessaggi;
    }

    public void aggiungiMessaggio(Messaggio messaggio){
        this.elencoMessaggi.add(messaggio);
    }
}

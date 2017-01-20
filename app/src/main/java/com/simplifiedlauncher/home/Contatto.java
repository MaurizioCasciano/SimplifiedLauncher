package com.simplifiedlauncher.home;

import java.io.Serializable;

/**
 * Created by Carmine on 18/01/2017.
 */

public class Contatto implements Serializable {

    private String nome;
    private String numero;

    public Contatto(){

    }

    public Contatto(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
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
}

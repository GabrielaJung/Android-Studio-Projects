package br.devgabriela.threatsapp;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Threat extends ArrayList<String> {

    // código identificador numérico
    private Long id;
    // endereço
    private String address;
    // data
    private String date;
    // descrição
    private String description;

    public Threat(Long id, String address, String date, String description) {
        this.id = id;
        this.address = address;
        this.date = date;
        this.description = description;
    }

    public Threat(){}

    public Long getId(){
        return id ;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString(){
        return id + " " + address + " " + date + " " + description;
    }
}

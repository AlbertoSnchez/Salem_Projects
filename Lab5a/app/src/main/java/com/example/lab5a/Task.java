package com.example.lab5a;

public class Task {
    private int id;
    private String name;
    private int isUrgent;

    public Task(String name, int isUrgent){
        this.name = name;
        this.isUrgent = isUrgent;
    }
    public Task(int id,String name, int isUrgent){
        this.id = id;
        this.name = name;
        this.isUrgent = isUrgent;
    }


    public void setName(String name){
        this.name = name;
    }
    public void setUrgent(int urgent){
        this.isUrgent = urgent;
    }
    public int getId(){ return  this.id; };
    public String getName(){
        return this.name;
    }
    public int getUrgent(){
        return this.isUrgent;
    }
}

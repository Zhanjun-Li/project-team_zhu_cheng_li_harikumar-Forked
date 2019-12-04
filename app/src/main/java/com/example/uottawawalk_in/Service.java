package com.example.uottawawalk_in;


public class Service {
    private String name, role;
    private int id;
    private double rate;
    private Student provider;

    public void setName(String name){
        this.name = name;
    }
    public void setRate(double rate){
        this.rate = rate;
    }
    public void setRole(String role){
        this.role = role;
    }
    public void setProvider(Student provider){ this.provider = provider; }

    public String getName() { return name; }
    public double getRate(){
        return rate;
    }
    public String getRole(){ return role; }
    public Student getProvider(){ return provider; }
    public int getId(){ return id;}

    public Service(String name, String role, double rate, Student student,int id){
        setName(name);
        setRate (rate);
        setRole(role);
        setProvider(student);
        this.id = id;
        this.id++;
    }
    public Service(){};
    public String toString(){
        return name + "     offered by " + provider.getFirstName();
    }
}

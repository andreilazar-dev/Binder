package com.binder.database.datamodel;

public class User {

    private String id;
    private String password;
    private String name;
    private String flag;
    private String dateexp;


    public User (String id,String password,String name,String flag,String dateexp){
        this.id=id;
        this.password= password;
        this.name = name;
        this.flag = flag;
        this.dateexp = dateexp;
    }

    public String toString(){
        return "Id: "+id+'/'+
                "Password: "+ password+'/' +
                        "Name: " + name +
                                "Flag: "+ flag + '/'+
                                    "Data: "+ dateexp ;

    }

    //_______________________________________________________________________________________________
    //getter and setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDateexp() {
        return dateexp;
    }

    public void setDateexp(String dateexp) {
        this.dateexp = dateexp;
    }
}

package com.example.c5_p25;

public class Friend {
    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public Friend ( int nid, String nfn, String nln, String nea){
        setId(nid);
        setFirstName(nfn);
        setLastName(nln);
        setEmailAddress(nea);
    }

    public void setId( int newId ) {
        this.id = newId;
    }

    public void setFirstName ( String newfN ) {
        this.firstName = newfN;
    }
    public void setLastName ( String newlN) {
        this.lastName = newlN;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getId(){
        return this.id;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

}

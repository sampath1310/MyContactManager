package com.yomeon.mycontactmanager.datamodel;

/**
 * Created by sampa on 18-09-2016.
 */
public class Datamodel {

        private String firstname;
        private String lastname;
        private String email;
        private String phonenumber;
        public Datamodel(){}
        public Datamodel(String firstname, String lastname,String email,String phonenumber){
            this.firstname=firstname;
            this.lastname=lastname;
            this.email=email;
            this.phonenumber=phonenumber;
        }
        public void setFirstname(String firstname){
            this.firstname=firstname;
        }
        public String getFirstName(){
            return firstname;
        }
        public void setLastname (String lastname){
            this.lastname=lastname;
        }
        public String getLastname(){
            return lastname;
        }
        public void setEmail(String email){
            this.email=email;
        }
        public String getEmail(){
            return email;
        }
        public void setPhonenumber(String phonenumber){
            this.phonenumber=phonenumber;
        }
        public String getPhonenumber(){
            return phonenumber;
        }


}

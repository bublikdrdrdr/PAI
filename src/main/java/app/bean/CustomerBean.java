package app.bean;

import java.io.Serializable;

public class CustomerBean implements Serializable {

    private int id;
    private String fullname;
    private String phone;
    private String email;
    private String city;

    public CustomerBean() {
    }

    public CustomerBean(int id, String fullname, String phone, String email, String city) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

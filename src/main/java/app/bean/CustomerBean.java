package app.bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class CustomerBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String fullname;

    @Column
    private String phone;

    @Column(unique = true)
    private String email;

    @Column
    private String city;

    public CustomerBean() {
    }

    public CustomerBean(Integer id, String fullname, String phone, String email, String city) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

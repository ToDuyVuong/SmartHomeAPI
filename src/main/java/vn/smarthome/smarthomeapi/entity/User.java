package vn.smarthome.smarthomeapi.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id()
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

//    @Id()
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;



    @Column(name = "user_Name", columnDefinition = "nvarchar(1111)")
    private String username;

    @Column(name = "password",columnDefinition = "nvarchar(1111)")
    private String password;

    @Column(name = "avatar", columnDefinition = "nvarchar(1111)")
    private String avatar;

    @Column(name = "gender", columnDefinition = "nvarchar(1111)")
    private String gender;

    @Column(name = "email", columnDefinition = "nvarchar(1111)")
    private String email;

    @Column(name = "phone_Number", columnDefinition = "nvarchar(1111)")
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "nvarchar(1111)")
    private String address;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Order> order;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Cart> cart;
}

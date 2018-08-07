package com.tinhcao.spring.springinactionfifthedition.request;

import com.tinhcao.spring.springinactionfifthedition.entity.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(this.getUsername())
                .password(passwordEncoder.encode(this.getPassword()))
                .fullname(this.getFullname())
                .street(this.getStreet())
                .city(this.getCity())
                .state(this.getState())
                .zip(this.getZip())
                .phoneNumber(this.getPhone())
                .build();
    }
}

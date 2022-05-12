package com.example.Kyrcach_java_Yaroslavtser.controller.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 14)
    private String login;

    @NotNull
    @Size(min = 8, max = 14)
    private String password;

    @NotNull
    @Size(min = 3, max = 14)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 14)
    private String lastName;

    @NotNull
    @Pattern(regexp = "^375[(](17|25|29|33|44)[)]([0-9]{7})$")
    private String phoneNumber;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserDTO(Long id,@NotNull String login,@NotNull String password,@NotNull String firstName,@NotNull String lastName,@NotNull String phoneNumber,String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public UserDTO(@NotNull String login,@NotNull String password,@NotNull String name,@NotNull String surname,@NotNull String phoneNumber,String role) {
        this.login = login;
        this.password = password;
        this.firstName = name;
        this.lastName = surname;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public UserDTO(String login, String password,String name, String surname, String phoneNumber) {
        this.login = login;
        this.password = password;
        this.firstName = name;
        this.lastName = surname;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(Long id) {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDTO{");
        sb.append(", id='").append(id).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
    private String role;

    public UserDTO() {
    }


}

package skillbox.com.users.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

public class UserDto implements  Cloneable {
    private Integer id;
    private String name;
    private String login;
    private String gender;
    private String email;
    private String phone;
    private boolean active;
    private String address;
    private boolean deleted;
    private Integer cityId;

    public UserDto() {
        //
    }

    public UserDto(Integer id, String name, String login, String gender, String email, String phone, boolean active, String address, boolean deleted, Integer cityId) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.address = address;
        this.deleted = deleted;
        this.cityId = cityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                ", address='" + address + '\'' +
                ", deleted=" + deleted +
                ", cityId=" + cityId +
                '}';
    }

    @Override
    public UserDto clone() {
        try {
            UserDto clone = (UserDto) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

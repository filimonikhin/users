package skillbox.com.users.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "users_scheme", catalog = "users")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "login", nullable = false)
    private String login;

    @Basic
    @Column(name = "gender", nullable = false)
    private String gender;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;

    @Basic
    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", /* referencedColumnName = "id",*/ nullable = false,
                foreignKey = @ForeignKey(name = "fk_users_cities"))
    private CityEntity cityEntity;

    @OneToMany(mappedBy = "subscriberEntity", fetch = FetchType.LAZY)
    private List<SubscriptionEntity> subscriberEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "subscribedEntity", fetch = FetchType.LAZY)
    private List<SubscriptionEntity> subscribedEntityList = new ArrayList<>();

    // CONSTRUCTORS
    public UserEntity() {
        //
    }

    public UserEntity(Integer id, String name, String login,
                      String gender, String email, String phone,
                      boolean active, String address, CityEntity cityEntity) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.address = address;
        this.cityEntity = cityEntity;
    }

    // METHODS
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

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public List<SubscriptionEntity> getSubscriberEntityList() {
        return subscriberEntityList;
    }

    public void setSubscriberEntityList(List<SubscriptionEntity> subscriberEntityList) {
        this.subscriberEntityList = subscriberEntityList;
    }

    public List<SubscriptionEntity> getSubscribedEntityList() {
        return subscribedEntityList;
    }

    public void setSubscribedEntityList(List<SubscriptionEntity> subscribedEntityList) {
        this.subscribedEntityList = subscribedEntityList;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id="            + id      +
                ", name='"       + name    + '\'' +
                ", login='"      + login   + '\'' +
                ", gender='"     + gender  + '\'' +
                ", email='"      + email   + '\'' +
                ", phone='"      + phone   + '\'' +
                ", active="      + active  +
                ", address='"    + address + '\'' +
                ", cityEntity='" + cityEntity.getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

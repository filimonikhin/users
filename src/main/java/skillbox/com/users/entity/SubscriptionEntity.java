package skillbox.com.users.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "subscriptions", schema = "users_scheme", catalog = "users")
public class SubscriptionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "subscribe_date", nullable = false)
    private Date subscribeDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscriber_id", /*referencedColumnName = "id",*/ nullable = false,
                foreignKey = @ForeignKey(name = "fk_subscriptions_users_1"))
    private UserEntity subscriberEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscribed_id", /*referencedColumnName = "id",*/ nullable = false,
                foreignKey = @ForeignKey(name = "fk_subscriptions_users_2"))
    private UserEntity subscribedEntity;

    // CONSTRUCTORS
    public SubscriptionEntity() {
        //
    }

    public SubscriptionEntity(Date subscribeDate, UserEntity subscriberEntity, UserEntity subscribedEntity) {
        this.subscribeDate = subscribeDate;
        this.subscriberEntity = subscriberEntity;
        this.subscribedEntity = subscribedEntity;
    }

    // METHODS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public UserEntity getSubscriberEntity() {
        return subscriberEntity;
    }

    public void setSubscriberEntity(UserEntity subscriberEntity) {
        this.subscriberEntity = subscriberEntity;
    }

    public UserEntity getSubscribedEntity() {
        return subscribedEntity;
    }

    public void setSubscribedEntity(UserEntity subscribedEntity) {
        this.subscribedEntity = subscribedEntity;
    }
}

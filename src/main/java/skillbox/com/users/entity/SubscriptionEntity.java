package skillbox.com.users.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "subscriptions", schema = "users_scheme", catalog = "users")
public class SubscriptionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "subscribe_date", nullable = false)
    private Date subscribeDate = Date.valueOf(LocalDate.now());

    @Basic
    @Column(name = "subscriber_id", nullable = false)
    private Integer subscriberId;

    @Basic
    @Column(name = "subscribed_id", nullable = false)
    private Integer subscribedId;

    @Basic
    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    //    @ManyToOne(fetch = FetchType.EAGER)
    //    @JoinColumn(name = "subscriber_id", /*referencedColumnName = "id",*/ nullable = false,
    //                foreignKey = @ForeignKey(name = "fk_subscriptions_users_1"))
    //    private UserEntity subscriberEntity;

    //    @ManyToOne(fetch = FetchType.EAGER)
    //    @JoinColumn(name = "subscribed_id", /*referencedColumnName = "id",*/ nullable = false,
    //                foreignKey = @ForeignKey(name = "fk_subscriptions_users_2"))
    //    private UserEntity subscribedEntity;

    // CONSTRUCTORS
    public SubscriptionEntity() {
        //
    }

    public SubscriptionEntity(Date subscribeDate,
                              Integer subscriberId,
                              Integer subscribedId,
                              boolean deleted
                              // UserEntity subscriberEntity,
                              // UserEntity subscribedEntity
                              ) {
        this.subscribeDate = subscribeDate;
        this.subscriberId = subscriberId;
        this.subscribedId = subscribedId;
        this.deleted = deleted;
        // this.subscriberEntity = subscriberEntity;
        // this.subscribedEntity = subscribedEntity;
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

    public Integer getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Integer subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Integer getSubscribedId() {
        return subscribedId;
    }

    public void setSubscribedId(Integer subscribedId) {
        this.subscribedId = subscribedId;
    }

    //    public UserEntity getSubscriberEntity() {
    //        return subscriberEntity;
    //    }

    //    public void setSubscriberEntity(UserEntity subscriberEntity) {
    //        this.subscriberEntity = subscriberEntity;
    //    }

    //    public UserEntity getSubscribedEntity() {
    //        return subscribedEntity;
    //    }

    //    public void setSubscribedEntity(UserEntity subscribedEntity) {
    //        this.subscribedEntity = subscribedEntity;
    //    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionEntity that = (SubscriptionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(subscriberId, that.subscriberId) && Objects.equals(subscribedId, that.subscribedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subscriberId, subscribedId);
    }

    @Override
    public String toString() {
        return "SubscriptionEntity{" +
                "id=" + id +
                ", subscribeDate=" + subscribeDate +
                ", subscriberId=" + subscriberId +
                ", subscribedId=" + subscribedId +
                ", deleted=" + deleted +
                '}';
    }
}

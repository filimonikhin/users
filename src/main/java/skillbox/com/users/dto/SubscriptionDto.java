package skillbox.com.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import java.time.LocalDate;

public class SubscriptionDto {
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date subscribeDate = Date.valueOf(LocalDate.now());
    private Integer subscriberId;
    private Integer subscribedId;
    private boolean deleted;

    public SubscriptionDto() {
        //
    }

    public SubscriptionDto(
            Integer id,
            Date subscribeDate,
            Integer subscriberId,
            Integer subscribedId,
            boolean deleted)
    {
        this.id = id;
        this.subscribeDate = subscribeDate;
        this.subscriberId = subscriberId;
        this.subscribedId = subscribedId;
        this.deleted = deleted;
    }

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

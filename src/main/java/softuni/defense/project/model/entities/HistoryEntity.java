package softuni.defense.project.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import softuni.defense.project.model.entities.base.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name = "shop_history")
public class HistoryEntity extends BaseEntity {

//    @ManyToOne
//    private CarEntity car;
    @ManyToOne
    private UserEntity newOwner;
    @Column(name = "old_status")
    private String oldStatus;

    @Column(name = "new_status")
    private String newStatus;
    @Column(name = "change_date")
    private LocalDate changeDate;

//    public CarEntity getCar() {
//        return car;
//    }
//
//    public void setCar(CarEntity car) {
//        this.car = car;
//    }

    public UserEntity getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(UserEntity newOwner) {
        this.newOwner = newOwner;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }
}

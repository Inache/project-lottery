package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.Participant;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "NCH_LOTTERIES")
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "title", unique = true)
    @NotBlank
    private String title;
    @Column(name = "participantslimit")
    private Long participantsLimit;
    @Column(name = "registrationIsAvailable")
    private boolean registrationIsAvailable;
    @Column(name = "startdate")
    private Date startDate;
    @Column(name = "enddate")
    private Date endDate;

//    @ManyToOne
//    @JoinColumn(name = "assigned_participant_id")
//    private Participant participants;

    public Lottery() {
    }

    public Lottery(@NotBlank String title, Long participantsLimit, boolean registrationIsAvailable, Date startDate, Date endDate) {
        this.title = title;
        this.participantsLimit = participantsLimit;
        this.registrationIsAvailable = registrationIsAvailable;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottery lottery = (Lottery) o;
        return registrationIsAvailable == lottery.registrationIsAvailable &&
                Objects.equals(id, lottery.id) &&
                Objects.equals(title, lottery.title) &&
                Objects.equals(participantsLimit, lottery.participantsLimit) &&
                Objects.equals(startDate, lottery.startDate) &&
                Objects.equals(endDate, lottery.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, participantsLimit, registrationIsAvailable, startDate, endDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getParticipantsLimit() {
        return participantsLimit;
    }

    public void setParticipantsLimit(Long participantsLimit) {
        this.participantsLimit = participantsLimit;
    }

    public boolean isRegistrationIsAvailable() {
        return registrationIsAvailable;
    }

    public void setRegistrationIsAvailable(boolean registrationIsAvailable) {
        this.registrationIsAvailable = registrationIsAvailable;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



    @Override
    public String toString() {
        return "Lottery{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", participantsLimit=" + participantsLimit +
                ", registrationIsAvailable=" + registrationIsAvailable +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}


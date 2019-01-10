package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.Participant;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "NCH_LOTTERIES")
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "title",unique = true)
    @NotBlank
    private String title;
    @Column(name = "participantslimit")
    private Long participantsLimit;
    @Column(name = "status")
    private String status;
    @Column(name = "startdate")
    private Date startDate;
    @Column(name = "enddate")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "assigned_participant_id")
    private Participant participants;

    public Lottery() {
    }

    public Participant getParticipants() {
        return participants;
    }

    public void setParticipants(Participant participant) {
        this.participants = participant;
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

    public void setParticipantsLimit(Long limit) {
        this.participantsLimit = limit;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottery lottery = (Lottery) o;
        return Objects.equals(getId(), lottery.getId()) &&
                Objects.equals(getTitle(), lottery.getTitle()) &&
                Objects.equals(getParticipantsLimit(), lottery.getParticipantsLimit()) &&
                Objects.equals(getStatus(), lottery.getStatus()) &&
                Objects.equals(getStartDate(), lottery.getStartDate()) &&
                Objects.equals(getEndDate(), lottery.getEndDate()) &&
                Objects.equals(getParticipants(), lottery.getParticipants());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getParticipantsLimit(), getStatus(), getStartDate(), getEndDate(), getParticipants());
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", limit=" + participantsLimit +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", participant=" + participants +
                '}';
    }
}

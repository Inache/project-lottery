package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.Participant;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "NCH_LOTTERIES")
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "participantsLimit")
    private Long participantsLimit;
    @Column(name = "status")
    private String status;
    @Column(name = "startDate")
    private Date startDate;
    @Column(name = "endDate")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "assigned_participant_id")
    private Participant participant;

    public Lottery() {
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
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
                Objects.equals(getParticipant(), lottery.getParticipant());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getParticipantsLimit(), getStatus(), getStartDate(), getEndDate(), getParticipant());
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
                ", participant=" + participant +
                '}';
    }
}

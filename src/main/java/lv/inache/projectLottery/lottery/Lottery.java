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
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "title")
    @NotBlank
    private String title;
    @Column(name = "participantsLimit")
    private Long participantsLimit;
    @Column(name = "registrationIsAvailable")
    private boolean registrationIsAvailable;
    @Column(name = "startdate")
    private Date startDate;
    @Column(name = "enddate")
    private Date endDate;
    @Column(name = "winnerCode")
    private String winnerCode;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private Participant participant;

    public Lottery() {
    }

    public Lottery(@NotBlank String title, Long participantsLimit, boolean registrationIsAvailable, Date startDate, Date endDate, String winnerCode, Participant participant) {
        this.title = title;
        this.participantsLimit = participantsLimit;
        this.registrationIsAvailable = registrationIsAvailable;
        this.startDate = startDate;
        this.endDate = endDate;
        this.winnerCode = winnerCode;
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

    public String getWinnerCode() {
        return winnerCode;
    }

    public void setWinnerCode(String winnerCode) {
        this.winnerCode = winnerCode;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottery lottery = (Lottery) o;
        return isRegistrationIsAvailable() == lottery.isRegistrationIsAvailable() &&
                Objects.equals(getId(), lottery.getId()) &&
                Objects.equals(getTitle(), lottery.getTitle()) &&
                Objects.equals(getParticipantsLimit(), lottery.getParticipantsLimit()) &&
                Objects.equals(getStartDate(), lottery.getStartDate()) &&
                Objects.equals(getEndDate(), lottery.getEndDate()) &&
                Objects.equals(getWinnerCode(), lottery.getWinnerCode()) &&
                Objects.equals(getParticipant(), lottery.getParticipant());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getParticipantsLimit(), isRegistrationIsAvailable(), getStartDate(), getEndDate(), getWinnerCode(), getParticipant());
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", limit=" + participantsLimit +
                ", registrationIsAvailable=" + registrationIsAvailable +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", winnerCode='" + winnerCode + '\'' +
                ", participant=" + participant +
                '}';
    }
}
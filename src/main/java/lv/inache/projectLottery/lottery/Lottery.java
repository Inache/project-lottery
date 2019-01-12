package lv.inache.projectLottery.lottery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "lottery")
    @JsonManagedReference
    @JsonIgnore
    private List<Participant> participants;
    public Lottery() {
    }

    public Lottery(@NotBlank String title, Long participantsLimit, boolean registrationIsAvailable, Date startDate, Date endDate, String winnerCode) {
        this.title = title;
        this.participantsLimit = participantsLimit;
        this.registrationIsAvailable = registrationIsAvailable;
        this.startDate = startDate;
        this.endDate = endDate;
        this.winnerCode = winnerCode;
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
                Objects.equals(endDate, lottery.endDate) &&
                Objects.equals(winnerCode, lottery.winnerCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, participantsLimit, registrationIsAvailable, startDate, endDate, winnerCode);
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
                ", winnerCode='" + winnerCode + '\'' +
                ", participants=" + participants +
                '}';
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

    public String getWinnerCode() {
        return winnerCode;
    }

    public void setWinnerCode(String winnerCode) {
        this.winnerCode = winnerCode;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
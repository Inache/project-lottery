package lv.inache.projectLottery.participant;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lv.inache.projectLottery.lottery.Lottery;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "NCH_PARTICIPANTS")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;
    @Column(name = "email", unique = true)
    @NotBlank
    private String email;
    @Column(name = "age")
    private Byte age;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "lotteryId")
    private Long lotteryId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participant")
    @JsonIgnore
    private List<Lottery> lotteries;


    public Participant() {
    }

    public Participant(@NotBlank String email, Byte age, String code, Long lotteryId, List<Lottery> lotteries) {
        this.email = email;
        this.age = age;
        this.code = code;
        this.lotteryId = lotteryId;
        this.lotteries = lotteries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id &&
                Objects.equals(email, that.email) &&
                Objects.equals(age, that.age) &&
                Objects.equals(code, that.code) &&
                Objects.equals(lotteryId, that.lotteryId) &&
                Objects.equals(lotteries, that.lotteries);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, age, code, lotteryId, lotteries);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
    }

    public List<Lottery> getLotteries() {
        return lotteries;
    }

    public void setLotteries(List<Lottery> lotteries) {
        this.lotteries = lotteries;
    }
}
package lv.inache.projectLottery.participant;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lv.inache.projectLottery.lottery.Lottery;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "NCH_PARTICIPANTS")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;
    @Column(name = "email")
    @NotBlank
    private String email;
    @Column(name = "age")
    private Byte age;
    @Column(name = "code", unique = true)
    private String code;
    private Long lotteryId;
    @ManyToOne
    @JoinColumn(name = "lotteryId", insertable = false, updatable = false)
    @JsonBackReference
    private Lottery lottery;

    public Participant() {
    }

    public Participant(@NotBlank String email, Byte age, String code, Long lotteryId, Lottery lottery) {
        this.email = email;
        this.age = age;
        this.code = code;
        this.lotteryId = lotteryId;
        this.lottery = lottery;
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
                Objects.equals(lottery, that.lottery);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, age, code, lotteryId, lottery);
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

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", code='" + code + '\'' +
                ", lotteryId=" + lotteryId +
                ", lottery=" + lottery +
                '}';
    }
}


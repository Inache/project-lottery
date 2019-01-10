package lv.inache.projectLottery.participant;

import lv.inache.projectLottery.lottery.Lottery;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "NCH_PARTICIPANTS")
public class Participant {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "email", unique = true)
    @NotBlank
    private String email;
    @Column(name = "age")
    private Byte age;
    @Column(name = "code")
    private String code;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participants")
    private List<Lottery> lotteries;


    public Participant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Lottery> getLotteries() {
        return lotteries;
    }

    public void setLotteries(List<Lottery> lotteries) {
        this.lotteries = lotteries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getAge(), that.getAge()) &&
                Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getLotteries(), that.getLotteries());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getEmail(), getAge(), getCode(), getLotteries());
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", code='" + code + '\'' +
                ", lotteries=" + lotteries +
                '}';
    }
}

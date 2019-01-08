package lv.inache.projectLottery.participant;

import java.util.Objects;

public class Participant {

    private Long id;
    private String email;
    private Byte age;
    private String code;

    public Participant() {
    }

    public Participant(Long id, String email, Byte age, String code) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.code = code;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getAge(), that.getAge()) &&
                Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getEmail(), getAge(), getCode());
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", code='" + code + '\'' +
                '}';
    }
}

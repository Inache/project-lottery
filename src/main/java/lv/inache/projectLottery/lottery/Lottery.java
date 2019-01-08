package lv.inache.projectLottery.lottery;

import java.util.Objects;

public class Lottery {

    private Long id;
    private String title;
    private Long limit;
    private Long addedParticipants;

    public Lottery() {
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

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }


    public Long getAddedParticipants() {
        return addedParticipants;
    }

    public void setAddedParticipants(Long addedParticipants) {
        this.addedParticipants = addedParticipants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottery lottery = (Lottery) o;
        return Objects.equals(getId(), lottery.getId()) &&
                Objects.equals(getTitle(), lottery.getTitle()) &&
                Objects.equals(getLimit(), lottery.getLimit()) &&
                Objects.equals(getAddedParticipants(), lottery.getAddedParticipants());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getLimit(), getAddedParticipants());
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", limit=" + limit +
                ", addedParticipants='" + addedParticipants + '\'' +
                '}';
    }

}

package lv.inache.projectLottery.participant;

import lv.inache.projectLottery.BaseDaoImplementation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ParticipantDaoImplementation extends BaseDaoImplementation<Participant> {
    @Autowired
    protected ParticipantDaoImplementation(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Participant> getAll() {
        return super.getAll(Participant.class);
    }

    public Optional<Participant> getById(Long id) {
        return super.getById(id, Participant.class);
    }

    public void delete(Long id) {
        super.delete(id, Participant.class);
    }
}

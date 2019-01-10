package lv.inache.projectLottery.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantService {
    private final ParticipantDaoImplementation participantDaoImplementation;

    @Autowired
    public ParticipantService(ParticipantDaoImplementation participantDaoImplementation) {
        this.participantDaoImplementation = participantDaoImplementation;
    }

    public Long add(Participant participant) {
        return participantDaoImplementation.insert(participant);
    }


    public List<Participant> participants() {
        List<Participant> participants = participantDaoImplementation.getAll();
        return participants;
    }

    public Optional<Participant> get(Long id) {
        return participantDaoImplementation.getById(id);
    }

    public void delete(Long id) {
        participantDaoImplementation.delete(id);
    }

    public void update(Long id, Participant participant) {
        participant.setId(id);
        participantDaoImplementation.update(participant);
    }
}


package lv.inache.projectLottery.participant;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParticipantService {
    private Map<Long, Participant> participantMap = new HashMap<>();
    private Long lastId = 1L;

    public Long add(Participant participant) {
        lastId++;
        participant.setId(lastId);
        participantMap.put(lastId, participant);
        return lastId;
    }

    public boolean participantExists(Long id) {
        return participantMap.containsKey(id);
    }

    public List<Participant> participants() {
        return new ArrayList<>(participantMap.values());
    }
}

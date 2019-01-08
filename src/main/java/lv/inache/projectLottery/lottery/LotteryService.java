package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LotteryService {
    private Map<Long, Lottery> lotteryStrorage = new HashMap<>();
    private Long lastId = 0L;

    private final ParticipantService participantService;

    @Autowired
    public LotteryService(ParticipantService participantService) {
        this.participantService = participantService;
    }

    public Long createLottery(Lottery lottery) {
        lastId++;
        lottery.setId(lastId);
        lotteryStrorage.put(lastId, lottery);
        return lastId;
    }

    public List<Lottery> get() {
        return new ArrayList<>(lotteryStrorage.values());
    }

    public void addParticipant(Long lotteryId, Long participantId) {
        if (participantService.participantExists(participantId)) {
            lotteryStrorage.get(lotteryId).setAddedParticipants(participantId);
        }

    }
}

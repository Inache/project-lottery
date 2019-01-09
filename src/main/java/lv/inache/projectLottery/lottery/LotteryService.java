package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.Participant;
import lv.inache.projectLottery.participant.ParticipantDaoImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LotteryService {
    private final LotteryDaoImplementation lotteryDao;
    private final ParticipantDaoImplementation participantDao;

    @Autowired
    public LotteryService(LotteryDaoImplementation lotteryDao, ParticipantDaoImplementation participantDao) {
        this.lotteryDao = lotteryDao;
        this.participantDao = participantDao;
    }


    public Long addLottery(Lottery lottery) {
        lottery.setStartDate(new Date());
        return lotteryDao.insert(lottery);
    }

    public List<Lottery> get() {
        return lotteryDao.getAll();
    }

    public Optional<Lottery> get(Long id) {
        return lotteryDao.getById(id);
    }

    public boolean update(Lottery newLottery) {
        lotteryDao.update(newLottery);
        return true;
    }

    public boolean addParticipant(Long lotteryId, Long participantId) {
        Optional<Lottery> wrappedLottery = this.get(lotteryId);
        Optional<Participant> wrappedParticipant = participantDao.getById(participantId);

        if (wrappedLottery.isPresent() && wrappedParticipant.isPresent()) {
            Lottery lottery = wrappedLottery.get();
            lottery.setParticipant(wrappedParticipant.get());

            this.update(lottery);
            return true;
        }

        return false;
    }

}

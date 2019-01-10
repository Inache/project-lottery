package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.Participant;
import lv.inache.projectLottery.participant.ParticipantDaoImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LotteryService {
    private final LotteryDaoImplementation lotteryDao;

    @Autowired
    public LotteryService(LotteryDaoImplementation lotteryDao) {
        this.lotteryDao = lotteryDao;
    }


    public Long addLottery(Lottery lottery){
        lottery.setStartDate(new Date());
        return lotteryDao.insert(lottery);
    }

    public boolean deleteLottery(Long id) {
        if (lotteryDao.getById(id, Lottery.class).isPresent()) {
            lotteryDao.delete(id, Lottery.class);
            return true;
        }

        return false;
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

    public boolean assign(Long lotteryId, Long participantId) {
        return false;
    }

//    public boolean addParticipant(Long lotteryId, Long participantId) {
//        Optional<Lottery> wrappedLottery = this.get(lotteryId);
//        Optional<Participant> wrappedParticipant = participantDao.getById(participantId);
//
//        if (wrappedLottery.isPresent() && wrappedParticipant.isPresent()) {
//            Lottery lottery = wrappedLottery.get();
//            lottery.setParticipants(wrappedParticipant.get());
//
//            this.update(lottery);
//            return true;
//        }
//
//        return false;
//    }

}

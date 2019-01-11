package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.ParticipantDaoImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
// TODO:Разобраться отдельно или нет создается и запускается лоттерея.
//    public void createLottery(Lottery lottery) {
//        lotteryDao.insert(lottery);
//    }

    public void startRegistration(Lottery lottery) {
        lottery.setStartDate(new Date());
        lottery.setRegistrationIsAvailable(true);
        lotteryDao.insert(lottery);
    }

    public void stopRegistration(Lottery lottery){
        lottery.setEndDate(new Date());
        lottery.setRegistrationIsAvailable(false);
        lotteryDao.update(lottery);
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

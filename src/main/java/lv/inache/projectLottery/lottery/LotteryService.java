package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.ParticipantDaoImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    public LotteryResponse startRegistration(Lottery lottery) {
        if (lottery.getTitle().isEmpty()) {
            return new LotteryResponse("Fail", "Title cant be empty");
        } else if (lottery.getParticipantsLimit() <= 0) {
            return new LotteryResponse("Fail", "Participants limit must be > 0");
        } else
            lottery.setStartDate(new Date());
        lottery.setRegistrationIsAvailable(true);
        lotteryDao.insert(lottery);
        return new LotteryResponse("OK", lottery.getId());


    }

    public LotteryResponse stopRegistration(Long id) {
        Optional<Lottery> wrappedLottery = lotteryDao.getById(id);
        Lottery lottery;
        if (wrappedLottery.isPresent()) {
            lottery = wrappedLottery.get();
            if (!lottery.isRegistrationIsAvailable()) {
                return new LotteryResponse("Fail", "Registration for lottery with id: " + id + " is already closed ");
            } else if (lottery.getParticipants().isEmpty()) {
                return new LotteryResponse("Fail", "Cant stop lottery with no participants");
            }
            lottery.setRegistrationIsAvailable(false);
            lottery.setEndDate(new Date());
            lotteryDao.update(lottery);
        } else {
            return new LotteryResponse("Fail", "Lottery with id: " + id + " doesn't exist");
        }
        return new LotteryResponse("Ok");
    }
    //TODO: перепроверить этот метод всеми путями!
    public LotteryResponse chooseWinnerCode(Long id) {
        Random random = new Random();
        Lottery lottery;
        String winnersCode;
        Integer randomWinner;
        Optional<Lottery> wrappedLottery = lotteryDao.getById(id);
        if (wrappedLottery.isPresent()) {
            lottery = wrappedLottery.get();

            if (lottery.isRegistrationIsAvailable()) {
                return new LotteryResponse("Fail", "Lottery is not stopped");
            }
            //TODO тут что-то ненравится nullpointerexception
//            else if (lottery.getWinnerCode().isEmpty() || lottery.getWinnerCode() !=null){
//                return new LotteryResponse("Fail", "Lottery already have a winner");
//            }
            randomWinner = random.nextInt(lottery.getParticipants().size()) + 1;
            winnersCode = lottery.getParticipants().get(randomWinner - 1).getCode();
            lottery.setWinnerCode(winnersCode);
            lotteryDao.update(lottery);
            return new LotteryResponse("OK", lottery.getWinnerCode(), true);
        } else {
            return new LotteryResponse("Fail", "Lottery with id: " + id + " does't exist");
        }

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


}

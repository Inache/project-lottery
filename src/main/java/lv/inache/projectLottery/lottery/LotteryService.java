package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.ParticipantDaoImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LotteryService {
    private final LotteryDaoImplementation lotteryDao;
    private LotteryTitleValidator lotteryTitleValidator;
    private SimpleDateFormat simpleDateFormat;

    @Autowired
    public LotteryService(LotteryDaoImplementation lotteryDao) {
        this.lotteryDao = lotteryDao;
    }
//
//    public void createLottery(Lottery lottery) {
//        lotteryDao.insert(lottery);
//    }

    public LotteryResponse startRegistration(Lottery lottery) {
        lotteryTitleValidator = new LotteryTitleValidator();
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date formattedStartDate = new Date();

        if (null == lottery.getTitle() || lottery.getTitle().isEmpty()) {
            return new LotteryResponse("Fail", "Title cant be empty");
        } else if (lotteryTitleValidator.checkIfTitleIsAlreadyUsed(lotteryDao, lottery)) {
            return new LotteryResponse("Fail", "This title is already used");
        } else if (null == lottery.getParticipantsLimit() || lottery.getParticipantsLimit() <= 0) {
            return new LotteryResponse("Fail", "Participants limit must be > 0");
        } else

            lottery.setStartDate(simpleDateFormat.format(formattedStartDate));
        lottery.setRegistrationIsAvailable(true);
        lotteryDao.insert(lottery);
        return new LotteryResponse("OK", lottery.getId());
    }

    public LotteryResponse stopRegistration(Long id) {
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date formattedEndDate = new Date();
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
            lottery.setEndDate(simpleDateFormat.format(formattedEndDate));
            lotteryDao.update(lottery);
        } else {
            return new LotteryResponse("Fail", "Lottery with id: " + id + " doesn't exist");
        }
        return new LotteryResponse("Ok");
    }

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
            } else if (null != lottery.getWinnerCode() && !lottery.getWinnerCode().isEmpty()) {
                return new LotteryResponse("Fail", "Lottery already have a winner");
            } else {
                randomWinner = random.nextInt(lottery.getParticipants().size()) + 1;
                winnersCode = lottery.getParticipants().get(randomWinner - 1).getCode();
                lottery.setWinnerCode(winnersCode);
                lotteryDao.update(lottery);
                return new LotteryResponse("OK", lottery.getWinnerCode(), true);
            }
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

    public void getStats(Lottery lottery) {
        List<Integer> listForOutput = Collections.singletonList(lottery.getParticipants().size());

    }

    public Optional<Lottery> get(Long id) {
        return lotteryDao.getById(id);
    }
}

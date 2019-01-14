package lv.inache.projectLottery.participant.validators;

import lv.inache.projectLottery.lottery.Lottery;
import lv.inache.projectLottery.lottery.LotteryDaoImplementation;

import java.util.Optional;

public class ParticipantExistenceValidator {
    public boolean participantRegisteredToLottery(LotteryDaoImplementation lotteryDao, String email, String code, Long lotteryId) {
        Optional<Lottery> wrappedLottery = lotteryDao.getById(lotteryId);
        Lottery lottery = wrappedLottery.get();
        if (lottery.getParticipants() != null)
            if (lottery.getParticipants().size() != 0) {
                if (lottery.getParticipants().stream().anyMatch(p -> p.getEmail().equals(email) && p.getCode().equals(code)))
                    return true;
                else return false;
            }
        return false;
    }
}

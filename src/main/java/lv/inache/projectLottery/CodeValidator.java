package lv.inache.projectLottery;

import lv.inache.projectLottery.lottery.Lottery;
import lv.inache.projectLottery.lottery.LotteryDaoImplementation;
import lv.inache.projectLottery.participant.Participant;
import lv.inache.projectLottery.participant.ParticipantDaoImplementation;

import java.util.List;
import java.util.Optional;

public class CodeValidator {
    //    Код должен состоять из 16 цифр. Любые лругие символы не подходят
//    Первые 6 цифр - дата стратра лотереи по формату DDMMYY. Т.е. если лотерея стартовала 27.12.2018 - первые 6 цифр должны быть 271218
//            7 и 8 цифры - кол-во символов в емейле регистрирующегося. Т.е. если емейл as@df.gh, то должно быть 08. Если емейл test@mail.com - 13.
//    Остальные 8 цифр, случайные
//    Каждый код должен быть уникальным
    public boolean checkLength(Integer length) {
        return length == 8;
    }

    public boolean checkIfCodeIsAlreadyUsed(ParticipantDaoImplementation partDao, Participant participant) {
        List<Participant> participants = partDao.getAll();
        for (int i = 0; i < participants.size(); i++) {
            Participant p = participants.get(i);
            if (participant.getCode().equals(p.getCode())) {
                return true;
            }
        }
        return false;
    }
    public boolean ifCodeIsValid(Participant participant, LotteryDaoImplementation lotteryDao){
        Optional<Lottery> wrappedLottery = lotteryDao.getById(participant.getLottery().getId());
        if (wrappedLottery.isPresent() && participant.getCode().length() == 16){
            Lottery lottery = wrappedLottery.get();

        }
        return true;
    }

}

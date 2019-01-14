package lv.inache.projectLottery.participant.validators;

import lv.inache.projectLottery.lottery.Lottery;
import lv.inache.projectLottery.lottery.LotteryDaoImplementation;
import lv.inache.projectLottery.participant.Participant;
import lv.inache.projectLottery.participant.ParticipantDaoImplementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CodeValidator {
    //    Код должен состоять из 16 цифр. Любые лругие символы не подходят
//    Первые 6 цифр - дата стратра лотереи по формату DDMMYY. Т.е. если лотерея стартовала 27.12.2018 - первые 6 цифр должны быть 271218
//            7 и 8 цифры - кол-во символов в емейле регистрирующегося. Т.е. если емейл as@df.gh, то должно быть 08. Если емейл test@mail.com - 13.
//    Остальные 8 цифр, случайные
//    Каждый код должен быть уникальным
    public boolean checkLength(Integer length) {
        return length == 16;
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

    public boolean ifCodeIsValid(Participant participant, LotteryDaoImplementation lotteryDao) {
        String regex = "\\d+";
        Optional<Lottery> wrappedLottery = lotteryDao.getById(participant.getLottery().getId());
        if (wrappedLottery.isPresent() && participant.getCode().length() == 16 && participant.getCode().matches(regex)) {
            Lottery lottery = wrappedLottery.get();
            String email = participant.getEmail();
            String tmpDate = lottery.getStartDate();
            String tmpDate2 = tmpDate.replaceAll("[^0-9]", "");
            //Не проверил что будет если день будет начинаться не с 2ух цифр...
            StringBuilder dateBuilder = new StringBuilder(tmpDate2);
            dateBuilder.delete(4, 6);
            dateBuilder.delete(6, 10);
            String datePart = dateBuilder.toString();
            String emailPart;
            if (participant.getEmail().length() < 10) {
                emailPart = "0" + email.length();
            } else {
                emailPart = "" + email.length();
            }
            String validFirstPartOfTheCode = datePart + emailPart;
            String participantsFirstPathOfTheCode = participant.getCode().substring(0, 8);
            return validFirstPartOfTheCode.equals(participantsFirstPathOfTheCode);
        }
        return false;
    }

}

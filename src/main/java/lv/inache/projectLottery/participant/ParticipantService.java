package lv.inache.projectLottery.participant;

import lv.inache.projectLottery.lottery.Lottery;
import lv.inache.projectLottery.lottery.LotteryDaoImplementation;
import lv.inache.projectLottery.participant.validators.CodeValidator;
import lv.inache.projectLottery.participant.validators.EmailValidator;
import lv.inache.projectLottery.participant.validators.ParticipantExistenceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantService {
    private final ParticipantDaoImplementation participantDao;
    private final LotteryDaoImplementation lotteryDao;
    private CodeValidator codeValidator;
    private EmailValidator emailValidator;
    private ParticipantExistenceValidator existenceValidator;

    @Autowired
    public ParticipantService(ParticipantDaoImplementation participantDao, LotteryDaoImplementation lotteryDao) {
        this.participantDao = participantDao;
        this.lotteryDao = lotteryDao;
    }

    public Long add(Participant participant) {
        return participantDao.insert(participant);
    }

    public List<Participant> get() {
        return participantDao.getAll();
    }

    public Optional<Participant> get(Long id) {
        return participantDao.getById(id);
    }

    public void delete(Long id) {
        participantDao.delete(id);
    }


    public ParticipantResponse registerParticipant(Participant participant) {
        codeValidator = new CodeValidator();
        emailValidator = new EmailValidator();

        Optional<Lottery> wrappedLottery = lotteryDao.getById(participant.getLotteryId());
        wrappedLottery.ifPresent(participant::setLottery);

        if (participant.getAge() == null) {
            return new ParticipantResponse("Fail", "Age can't be blank");
        } else if (participant.getAge() < 21) {
            return new ParticipantResponse("Fail", "Age must be >= 21 ");
        } else if (participant.getEmail().isEmpty()) {
            return new ParticipantResponse("Fail", "Mail cant be blank");
        } else if (!emailValidator.checkEmail(participant.getEmail())) {
            return new ParticipantResponse("Fail", "Invalid email");
        } else if (!codeValidator.checkLength(participant.getCode().length()) || participant.getCode().isEmpty()) {
            return new ParticipantResponse("Fail", "Your code must be from 16 digits");
        } else if (codeValidator.checkIfCodeIsAlreadyUsed(participantDao, participant)) {
            return new ParticipantResponse("Fail", "This code is already registered");
        } else if (wrappedLottery.get().getParticipants().size() >= wrappedLottery.get().getParticipantsLimit()) {
            return new ParticipantResponse("Fail", "Limit = participants,no more free places");
        } else if (!codeValidator.ifCodeIsValid(participant, lotteryDao)) {
            return new ParticipantResponse("Fail", "First part of the code dont match");
        }

        participantDao.insert(participant);
        return new ParticipantResponse("OK");
    }

    //WIN (если данный код выиграл), LOOSE (если не выиграл), PENDING (если выбор победителя ещё не проведён)
    // или ERROR (если на вход поданы не верные данные)
    public ParticipantResponse getStatus(Long id, String email, String code) {
        existenceValidator = new ParticipantExistenceValidator();
        Optional<Lottery> wrappedLottery = lotteryDao.getById(id);
        if (!wrappedLottery.isPresent()) {
            System.out.println("LOTTERY WITH THIS ID DONT EXIST");
            return new ParticipantResponse("ERROR");
        } else if (!existenceValidator.participantRegisteredToLottery(lotteryDao, email, code, id)) {
            System.out.println("WRONG DATA, OR PARTICIPANT DONT EXIST IN THIS LOTTERY WITH GIVEN INPUT");
            return new ParticipantResponse("ERROR");
        } else {
            Lottery lottery = wrappedLottery.get();
            if (lottery.isRegistrationIsAvailable() || !(lottery.getWinnerCode() != null && !lottery.getWinnerCode().isEmpty())) {
                return new ParticipantResponse("PENDING");
            } else if (lottery.getWinnerCode().equals(code)) {
                return new ParticipantResponse("WIN");
            } else {
                return new ParticipantResponse("LOOSE");
            }
        }
    }
}


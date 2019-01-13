package lv.inache.projectLottery.participant;

import lv.inache.projectLottery.CodeValidator;
import lv.inache.projectLottery.EmailValidator;
import lv.inache.projectLottery.lottery.Lottery;
import lv.inache.projectLottery.lottery.LotteryDaoImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantService {
    private final ParticipantDaoImplementation participantDao;
    private final LotteryDaoImplementation lotteryDao;
    private CodeValidator codeValidator;
    private EmailValidator emailValidator;

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

    //TODO: доделать этот сервис и сделать нормальный валидатор кода
    public ParticipantResponse registerParticipant(Participant participant) {
        codeValidator = new CodeValidator();
        emailValidator = new EmailValidator();


        Optional<Lottery> wrappedLottery = lotteryDao.getById(participant.getLotteryId());
        wrappedLottery.ifPresent(participant::setLottery);

        if (participant.getAge() == null){
            return new ParticipantResponse("Fail","Age can't be blank");
        }
        else if (participant.getAge() < 21){
            return new ParticipantResponse("Fail","Age must be >= 21 ");
        }

        else if (participant.getEmail().isEmpty()){
            return new ParticipantResponse("Fail","Mail cant be blank");
        }
        else if (!emailValidator.checkEmail(participant.getEmail())){
            return new ParticipantResponse("Fail","Invalid email");
        }
        else if (!codeValidator.checkLength(participant.getCode().length()) || participant.getCode().isEmpty())
        {
            return new ParticipantResponse("Fail","Your code must be from 8 digits");
        }
        else if (codeValidator.checkIfCodeIsAlreadyUsed(participantDao,participant)){
            return new ParticipantResponse("Fail","This code is already registered");
        }
        participantDao.insert(participant);
        return new ParticipantResponse("OK");
    }

    public void update(Long id, Participant participant) {
        participant.setId(id);
        participantDao.update(participant);
    }

}


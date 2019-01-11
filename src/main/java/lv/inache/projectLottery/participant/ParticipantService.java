package lv.inache.projectLottery.participant;

import lv.inache.projectLottery.lottery.Lottery;
import lv.inache.projectLottery.lottery.LotteryDaoImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantService {
    private final ParticipantDaoImplementation participantDao;
    private final LotteryDaoImplementation lotteryDao;

    @Autowired
    public ParticipantService(ParticipantDaoImplementation participantDao, LotteryDaoImplementation lotteryDao) {
        this.participantDao = participantDao;
        this.lotteryDao = lotteryDao;
    }

    public Long add(Participant participant) {
        return participantDao.insert(participant);
    }


    public List<Participant> participants() {
        List<Participant> participants = participantDao.getAll();
        return participants;
    }

    public Optional<Participant> get(Long id) {
        return participantDao.getById(id);
    }

    public void delete(Long id) {
        participantDao.delete(id);
    }


    public void registerParticipant(Participant participant) {
        Optional<Lottery> wrappedLottery = lotteryDao.getById(participant.getLotteryId());


        participantDao.insert(participant);
    }
//    public void assignTask(Long userId, Long taskId) {
//        Optional<User> wrappedUser = this.get(userId);
//        Optional<Task> wrappedTask = tasksDAOImplementation.getById(taskId);
//
//        if (wrappedTask.isPresent() && wrappedUser.isPresent()) {
//            User user = wrappedUser.get();
//            user.getTasks().add(wrappedTask.get());
//
//            userDaoImplementation.update(user);
//        }
//    }

    public void update(Long id, Participant participant) {
        participant.setId(id);
        participantDao.update(participant);
    }
}


package lv.inache.projectLottery.participant;

import lv.inache.projectLottery.lottery.LotteryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ParticipantController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    final
    ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity add(@RequestBody Participant participant) {
        LOGGER.info("Adding participant");
        if (participant.getAge() <= 21 || participant.getAge().equals(null)) {
            LOGGER.info("Adding participant: failed");
            return ResponseEntity.badRequest().body("status:Fail,\n" +
                    "reason: Age must be > 21");
        } else {
            LOGGER.info("Adding participant: success");
            participantService.add(participant);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/participants")
    public Collection<Participant> participants() {
        LOGGER.info("Getting participants");
        return participantService.participants();
    }
}

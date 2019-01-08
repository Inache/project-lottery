package lv.inache.projectLottery.participant;

import lv.inache.projectLottery.lottery.LotteryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/participants")
public class ParticipantController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    @Autowired
    ParticipantService participantService;

    @PostMapping
    public void add(@RequestBody Participant participant) {
        LOGGER.info("Adding participant");
        participantService.add(participant);
    }

    @GetMapping
    public Collection<Participant> participants() {
        LOGGER.info("Getting participants");
        return participantService.participants();
    }
}

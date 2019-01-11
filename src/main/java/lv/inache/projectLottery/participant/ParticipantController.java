package lv.inache.projectLottery.participant;


import lv.inache.projectLottery.lottery.LotteryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParticipantController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ParticipantController.class);
    private final LotteryService lotteryService;
    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(LotteryService lotteryService, ParticipantService participantService) {
        this.lotteryService = lotteryService;
        this.participantService = participantService;
    }

    @PostMapping(value = "/register")
    public void registerParticipant(@RequestBody Participant participant) {
        LOGGER.info("register participant");
        participantService.registerParticipant(participant);
    }
    @GetMapping(value = "/status")
    public void getStatus(@RequestParam Long id, @RequestParam String email, @RequestParam String code){

    }
}

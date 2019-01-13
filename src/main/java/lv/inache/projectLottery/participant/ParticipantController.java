package lv.inache.projectLottery.participant;


import lv.inache.projectLottery.lottery.LotteryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


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
        LOGGER.info("Registering participant");
        participantService.registerParticipant(participant);
    }
    @GetMapping(value = "/status")
    public void getStatus(@RequestParam Long id, @RequestParam String email, @RequestParam String code){

    }

    @GetMapping(value = "/participants")
    public Collection<Participant> get(){
        LOGGER.info("Getting all participants");
        return participantService.get();
    }

    @GetMapping(value = "/participants/{id}")
    public Optional<Participant> getById(@PathVariable Long id){
        LOGGER.info("Getting single participant with id: " + id);
        return participantService.get(id);
    }
}

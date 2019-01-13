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
    public ParticipantResponse registerParticipant(@RequestBody Participant participant) {
        LOGGER.info("Registering participant");
        return participantService.registerParticipant(participant);
    }

    //Пример запроса: GET localhost:8080/status?id=1&email=test@mail.com&code=1234567812345678
    @GetMapping(value = "/status")
    public ParticipantResponse getStatus(@RequestParam("id") Long id,
                                         @RequestParam("email") String email,
                                         @RequestParam ("code") String code) {
        LOGGER.info("Getting status for lotteryID:" + id + ",email:" + email + ",participantsCode:"+code);
        return participantService.getStatus(id, email, code);
    }

    @GetMapping(value = "/participants")
    public Collection<Participant> get() {
        LOGGER.info("Getting all participants");
        return participantService.get();
    }

    @GetMapping(value = "/participants/{id}")
    public Optional<Participant> getById(@PathVariable Long id) {
        LOGGER.info("Getting single participant with id: " + id);
        return participantService.get(id);
    }
}

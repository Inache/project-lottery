package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.ParticipantController;
import lv.inache.projectLottery.participant.ParticipantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LotteryController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ParticipantController.class);
    private final LotteryService lotteryService;
    private final ParticipantService participantService;

    @Autowired
    public LotteryController(LotteryService lotteryService, ParticipantService participantService) {
        this.lotteryService = lotteryService;
        this.participantService = participantService;
    }

    //    @PostMapping(value = "/create-lottery")
//    public void createLottery(@RequestBody Lottery lottery){
//        LOGGER.info("Creating lottery");
//        lotteryService.createLottery(lottery);
//        LOGGER.info("Lottery" + lottery.getTitle() + "created.");
//    }
    @PostMapping(value = "/start-registration")
    public LotteryResponse startRegistration(@RequestBody Lottery lottery) {
        LOGGER.info("start-registration");
        lotteryService.startRegistration(lottery);
        return new LotteryResponse("OK", lottery.getId());
    }
    @PostMapping(value = "/stop-registration")
    public void stopRegistration(@RequestBody Long id){
        LOGGER.info("stop-registration");
        //TODO
    }
    @PostMapping(value = "/choose-winner")
    public void chooseWinner(@RequestBody Long id){
        LOGGER.info("choose-winner");
        //TODO
    }
    @GetMapping(value = "/stats")
    public void statistics(@RequestBody Lottery lottery){
        LOGGER.info("Getting stats");
    }
}

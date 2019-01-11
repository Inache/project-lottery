package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.participant.ParticipantController;
import lv.inache.projectLottery.participant.ParticipantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

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
    @PutMapping(value = "/stop-registration/{id}")
    public void stopRegistration(@PathVariable Long id){
        LOGGER.info("stop-registration for lottery with id: " + id);
        lotteryService.stopRegistration(id);
    }
    @PutMapping(value = "/choose-winner/{id}")
    public void chooseWinner(@PathVariable Long id){
        LOGGER.info("choose-winner");
        lotteryService.chooseWinnerCode(id);
    }
    @GetMapping(value = "/stats")
    public void statistics(@RequestBody Lottery lottery){
        LOGGER.info("Getting stats");

    }

    @GetMapping(value = "/lotteries")
    public Collection<Lottery> getAllLotteries(){
        LOGGER.info("Getting all lotteries");
        return lotteryService.get();
    }

    @GetMapping(value = "/lotteries/{id}")
    public Optional<Lottery> getById(@PathVariable Long id){
        System.out.println(lotteryService.get(id));
        return lotteryService.get(id);
    }
}

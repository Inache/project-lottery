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
        LOGGER.info("Starting lottery");
        return lotteryService.startRegistration(lottery);
    }

    @PostMapping(value = "/stop-registration")
    public LotteryResponse stopRegistration(@RequestBody Lottery lottery) {
        LOGGER.info("Stopping registration for lottery with id: " + lottery.getId());
        return lotteryService.stopRegistration(lottery.getId());
    }

    @PostMapping(value = "/choose-winner")
    public LotteryResponse chooseWinner(@RequestBody Lottery lottery) {
        LOGGER.info("Choosing winner for lottery with id: " + lottery.getId());
        return lotteryService.chooseWinnerCode(lottery.getId());
    }

    @GetMapping(value = "/stats")
    public Collection<Lottery> statistics() {
        LOGGER.info("Getting stats");
        return lotteryService.get();

    }

    @GetMapping(value = "/lotteries")
    public Collection<Lottery> getAllLotteries() {
        LOGGER.info("Getting all lotteries");
        return lotteryService.get();
    }

    @GetMapping(value = "/lotteries/{id}")
    public Optional<Lottery> getById(@PathVariable Long id) {
        LOGGER.info("Getting single lottery with id: " + id);
        return lotteryService.get(id);
    }
}

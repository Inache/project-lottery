package lv.inache.projectLottery.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/lotteries")
public class LotteryController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    @Autowired
    LotteryService lotteryService;

    @GetMapping
    public Collection<Lottery> get() {
        LOGGER.info("Get lottery request");
        return lotteryService.get();
    }

    @PostMapping
    public void create(@RequestBody Lottery lottery) {
        LOGGER.info("Creating lottery");
        lotteryService.createLottery(lottery);
    }

    @PutMapping
    public void assign(@RequestParam Long lotteryId, @RequestParam Long participantId) {
        LOGGER.info("Adding participant to lottery");
        lotteryService.addParticipant(lotteryId, participantId);
    }


}

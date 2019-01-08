package lv.inache.projectLottery.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class LotteryController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    @Autowired
    LotteryService lotteryService;

    @GetMapping(value = "/lotteries")
    public Collection<Lottery> get() {
        LOGGER.info("Get lottery request");
        return lotteryService.get();
    }

    @PostMapping(value = "/start-registration")
    public ResponseEntity create(@RequestBody Lottery lottery) {
        LOGGER.info("Creating lottery");
        lotteryService.createLottery(lottery);
        if (lotteryService.lotteryExist(lottery.getId())){
            LOGGER.info("Lottery created successfully");
            return ResponseEntity.ok().body("status:OK,\n"+
            "id:"+lottery.getId());
        }
        else {
            LOGGER.info("Lottery creation FAILED");
            return ResponseEntity.badRequest().body("FAIL,\n"+
            "reason:");
        }

    }

    @PutMapping
    public void assign(@RequestParam Long lotteryId, @RequestParam Long participantId) {
        LOGGER.info("Adding participant to lottery");
        lotteryService.addParticipant(lotteryId, participantId);
    }


}

package lv.inache.projectLottery.lottery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/lotteries")
public class LotteryController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    @Autowired
    LotteryService lotteryService;

    @GetMapping
    public Collection<Lottery> get() {
        LOGGER.info("Get task request");
        return lotteryService.get();
    }

    @GetMapping(value = "/{id}")
    public Optional<Lottery> getById(@PathVariable Long id) {
        return lotteryService.get(id);
    }

    @PostMapping
    public void create(@RequestBody Lottery lottery) {
        lotteryService.addLottery(lottery);
    }

    @DeleteMapping
    public boolean delete(@RequestParam(value = "lotteryId") Long id) {
        return lotteryService.deleteLottery(id);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody Lottery lottery) {
        lottery.setId(id);
        return lotteryService.update(lottery);
    }

    @PutMapping(value = "/assign")
    public boolean assign(@RequestParam Long lotteryId, @RequestParam Long participantId) {
        return lotteryService.assign(lotteryId, participantId);
    }
//    @PutMapping(value = "/assign", produces = MediaType.APPLICATION_JSON_VALUE)
//    public boolean assign(@RequestParam(name = "lotteryId") Long lotteryId, @RequestParam Long userId){
//        return lotteryService.addParticipant(lotteryId, userId);
//    }

//    @PostMapping(value = "/createLottery")
//    public void createLottery(@RequestBody Lottery lottery) {
//        LOGGER.info("Creating lottery");
//        lotteryService.addLottery(lottery);
//    }
//
//    @PostMapping(value = "/start-registration")
//    public ResponseEntity startRegistration(@RequestBody Lottery lottery){
//        if (lottery.getStatus().equals("Started")) {
//            LOGGER.info("Lottery start: SUCCESS" +" Lottery status: "+ lottery.getStatus());
//            return ResponseEntity.ok().body("status:OK,\n" +
//                    "id:" + lottery.getId());
//        } else {
//            LOGGER.info("Lottery start: FAILED" +" Lottery status: "+ lottery.getStatus());
//            return ResponseEntity.badRequest().body("status:FAIL,\n" +
//                    "reason:");
//        }
//
//    }

//    @PutMapping
//    public void assign(@RequestParam Long lotteryId, @RequestParam Long participantId) {
//        LOGGER.info("Adding participant to lottery");
//        lotteryService.addParticipant(lotteryId, participantId);
//    }


}

package lv.inache.projectLottery.participant;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/participants")
public class ParticipantController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ParticipantController.class);

    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

//    @PostMapping(value = "/register")
//    public ResponseEntity add(@RequestBody Participant participant) {
//        LOGGER.info("Adding participant");
//        if (participant.getAge() <= 21 || participant.getAge().equals(null)) {
//            LOGGER.info("Adding participant: failed");
//            return ResponseEntity.badRequest().body("status:Fail,\n" +
//                    "reason: Age must be > 21");
//        } else {
//            LOGGER.info("Adding participant: success");
//            participantService.add(participant);
//            return ResponseEntity.ok(HttpStatus.OK);
//        }
//    }

    @PostMapping
    public void add(@RequestBody Participant participant) {
        participantService.add(participant);
    }

    @GetMapping
    public Collection<Participant> users() {
        return participantService.participants();
    }

    @DeleteMapping(value = "/{id}")
    public void delete (@PathVariable Long id) {
        participantService.delete(id);
    }

    @PutMapping
    public void update(@RequestParam Long id, @RequestBody Participant participant) {
        participantService.update(id, participant);
    }
}

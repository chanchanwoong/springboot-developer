package me.chanwoong.springbootdeveloper.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    @GetMapping("/quiz")
    public ResponseEntity<String> quiz(@RequestParam("code") int code) {
        switch (code) {
            case 1:
                return ResponseEntity.created(null).body("message >>> Created");
            case 2:
                return ResponseEntity.badRequest().body("message >>> Bad Request");
            default:
                return ResponseEntity.ok().body("message >>> OK");
        }
    }

    @PostMapping("/quiz")
    public ResponseEntity<String> quiz2(@RequestBody Code code) {
        switch (code.value()) {
            case 1:
                return ResponseEntity.status(403).body("message >>> Forbidden");
            default:
                return ResponseEntity.ok().body("message >>> OK");
        }
    }
}

record Code(int value) {}
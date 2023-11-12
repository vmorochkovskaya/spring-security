package org.training;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
@Api(value = "Service Management", tags = {"Service Management Tag"})
public class ServiceController {

    @ApiOperation(value = "Get info")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successfully get")})
    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok().body("MVC Authentication Info");
    }

    @GetMapping("/about")
    public ResponseEntity<String> about() {
        return ResponseEntity.ok().body("MVC Authentication About");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok().body("MVC Authentication Admin");
    }
}

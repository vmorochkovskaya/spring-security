package jmp.service.rest2.main;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jmp.cloud.service.api.main.service.SubscriptionService;
import jmp.dto.main.dto.SubscriptionRequestDto;
import jmp.dto.main.dto.SubscriptionResponseDto;
import jmp.service.rest2.main.exception.SubscriptionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@Api(value = "Service Management", tags = {"Service Management Tag"})
public class ServiceController {
    @Autowired
    private SubscriptionService subscriptionService;

    @ApiOperation(value = "Create new subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 201, message = "Successfully created", response = SubscriptionResponseDto.class)})
    @PostMapping("/")
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        var createdSubs = subscriptionService.createSubscription(subscriptionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubs);

    }

    @ApiOperation(value = "Update existing subscription or create new subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successfully updated")})
    @PutMapping("/")
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subs) {
        var updatedSubs = subscriptionService.updateSubscription(subs);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSubs);
    }

    @ApiOperation(value = "Delete subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successfully deleted")})
    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<String> deleteSubscription(@PathVariable("subscriptionId") Long subId) {
        subscriptionService.deleteSubscription(subId);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
    }

    @ApiOperation(value = "Get subscription by id")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @GetMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable("subscriptionId") Long id) {
        var subscriptionResponseDto = subscriptionService.getSubscription(id);
        var subs = subscriptionResponseDto.orElseThrow(SubscriptionNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(subs);
    }

    @ApiOperation(value = "Get all subscriptions")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @GetMapping("/")
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription() {
        List<SubscriptionResponseDto> allUsers = subscriptionService.getAllSubscriptions();
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleSubsNotFoundException(SubscriptionNotFoundException exc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cant find subscription by specified id");
    }
}

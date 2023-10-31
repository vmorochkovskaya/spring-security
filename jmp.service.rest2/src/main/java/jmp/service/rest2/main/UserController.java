package jmp.service.rest2.main;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jmp.cloud.service.api.main.service.UserService;
import jmp.dto.main.dto.UserRequestDto;
import jmp.dto.main.dto.UserResponseDto;
import jmp.service.rest2.main.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@Api(value = "Users Management", tags = {"Users Management Tag"})
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 201, message = "Successfully created", response = UserResponseDto.class)})
    @PostMapping(value = "/")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        var createdUser = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }

    @ApiOperation(value = "Update existing user or create new user")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successfully updated")})
    @PutMapping("/")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto) {
        var updatedUser = userService.updateUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successfully deleted")})
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("Successfully deleted");
    }

    @ApiOperation(value = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("userId") Long id) {
        var userResponseDto = userService.getUser(id);
        var user = userResponseDto.orElseThrow(UserNotFoundException::new);
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        List<UserResponseDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cant find user by specified id");
    }
}

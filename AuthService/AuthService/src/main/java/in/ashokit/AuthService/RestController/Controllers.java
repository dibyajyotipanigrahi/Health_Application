package in.ashokit.AuthService.RestController;

import in.ashokit.AuthService.ApiResponse.ApiResponse;
import in.ashokit.AuthService.Service.UserService;
import in.ashokit.AuthService.dto.UserDto;
import in.ashokit.AuthService.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class Controllers {

    @Autowired
    private UserService userService;

    @Autowired
    private ApiResponse apiResponse;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> registerUser(@RequestBody UserDto userDto)  {
        UserResponseDto user = userService.saveUser(userDto);

        if (user != null) {
            apiResponse.setMessage("UserSuccessfully registerd");
            apiResponse.setStatus(201);
            apiResponse.setData(user);
        } else {

            apiResponse.setStatus(500);
            apiResponse.setMessage("User Registration failed");
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@RequestBody UserDto userDto) {

        String token = userService.loginUser(userDto);
        if (token != null) {
            apiResponse.setMessage("User Logged in Successfully");
            apiResponse.setStatus(201);
            apiResponse.setData(token);
        } else {

            apiResponse.setStatus(500);
            apiResponse.setMessage("Login failed ");
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUser() {

        List<UserResponseDto> allUser = userService.getAllUser();
        if (allUser != null) {
            apiResponse.setMessage("User Retrive Successfully");
            apiResponse.setStatus(201);
            apiResponse.setData(allUser);
        } else {

            apiResponse.setStatus(500);
            apiResponse.setMessage("User Retrive successfully failed ");
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getById(@PathVariable Integer id){
        UserResponseDto user = userService.getById(id);
        if(user !=null){
            apiResponse.setData(user);

        }



        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }






    @PatchMapping("/updateUser/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        UserResponseDto user = userService.updateUser(id, userDto);

        if (user != null) {
            apiResponse.setMessage("User update Successfully");
            apiResponse.setStatus(201);
            apiResponse.setData(user);
        } else {

            apiResponse.setStatus(500);
            apiResponse.setMessage("User   failed to update ");
        }


        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

@DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer id) {


        try {
            userService.deleteUser(id);
            apiResponse.setMessage("User deleted successfully");
            apiResponse.setStatus(200);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setMessage("User deletion failed: " + e.getMessage());
            apiResponse.setStatus(500);

            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

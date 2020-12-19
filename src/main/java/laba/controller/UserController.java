package laba.controller;


import laba.entity.User;
import laba.service.TokenService;
import laba.service.UserDetailService;
import laba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailsService;
    private final TokenService tokenUtil;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager,
                          UserDetailService userDetailsService, TokenService tokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> createAuthToken(@RequestBody User user) {
        System.out.println(123123213);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Неверный логин или пароль ^-^", HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());
        final String jwt = tokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/api/registration")
    public ResponseEntity<String> register(@RequestBody @Valid User user) {
        if (userService.findByUsername(user.getUsername()) == null) {
            userService.save(user);
            return new ResponseEntity<>("Вы зарагистрировались!! Поздравляем!!!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Такой пользователь уже есть!!", HttpStatus.CONFLICT);
    }


}

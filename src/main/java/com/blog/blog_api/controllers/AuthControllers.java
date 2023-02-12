package com.blog.blog_api.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_api.dtos.LoginDTO;
import com.blog.blog_api.dtos.RegisterDTO;
import com.blog.blog_api.models.Rol;
import com.blog.blog_api.models.User;
import com.blog.blog_api.repositories.RolRepository;
import com.blog.blog_api.repositories.UserRepository;
import com.blog.blog_api.security.JWTAuthResponseDTO;
import com.blog.blog_api.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthControllers {
   @Autowired
   private AuthenticationManager authenticationManager;
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private RolRepository rolRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;
   //@Autowired
  //private JwtTokenProvider jwtTokenProvider; 

   @PostMapping("/login")
   public ResponseEntity<String> authenticationUser(@RequestBody LoginDTO loginDTO) {
      Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);

      //String token = jwtTokenProvider.CreateToken(authentication);
      return  new  ResponseEntity<>("init sucesfully!!!",HttpStatus.OK);
      //ResponseEntity.ok(new JWTAuthResponseDTO(token));

   }

   @PostMapping("/register")
   // registerUser(@RequestBody RegisterDTO registerDTO)
   public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
      if (userRepository.existsByUsername(registerDTO.getUsername())) {
         return new ResponseEntity<>("this username alreay exist", HttpStatus.BAD_REQUEST);
      }
      if (userRepository.existsByEmail(registerDTO.getEmail())) {
         return new ResponseEntity<>("this email alreay exist", HttpStatus.BAD_REQUEST);
      }
      User user = new User();
      user.setName(registerDTO.getName());
      user.setUsername(registerDTO.getUsername());
      user.setEmail(registerDTO.getEmail());
      user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

      Rol roles = rolRepository.findByName("ROLE_ADMIN").get();
      user.setRoles(Collections.singleton(roles));

      userRepository.save(user);
      return new ResponseEntity<>("User Register Successfully", HttpStatus.OK);
   }
}

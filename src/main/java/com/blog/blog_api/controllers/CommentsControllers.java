package com.blog.blog_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_api.dtos.CommentsDTO;
import com.blog.blog_api.services.CommensServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentsControllers {
    @Autowired
     private CommensServices commensServices;

     @GetMapping("/publications/{publicationsId}/comments")
     public List<CommentsDTO> listCommentsByPublication(@PathVariable(value = "publicationsId") Long publicationsId){
            return commensServices.getCommentsForPublicationsId(publicationsId);
     }
     @PostMapping("/publications/{publicationsId}/comments")
     public ResponseEntity<CommentsDTO> saveComment( @PathVariable(value = "publicationsId") long publicationsId,@Valid @RequestBody CommentsDTO commentsDTO){
         return new ResponseEntity<>(commensServices.createComments(publicationsId, commentsDTO),HttpStatus.CREATED);
     }
     @GetMapping("/publications/{publicationsId}/comments/{commentsId}")
     public ResponseEntity<CommentsDTO> getCommentsById(@PathVariable(value = "publicationsId") Long publicationsId,
     @PathVariable(value = "commentsId") Long commentsId){
       CommentsDTO commentsDTO = commensServices.getCommentsById(publicationsId, commentsId);
        return new ResponseEntity<>(commentsDTO,HttpStatus.OK); 
     }
     @PutMapping("/publications/{publicationsId}/comments/{commentsId}")
     public ResponseEntity<CommentsDTO> updateCommentsBy( @PathVariable(value = "publicationsId") Long publicationsId,
     @PathVariable(value = "commentsId") Long commentsId,@Valid @RequestBody CommentsDTO commentsDTO){
        CommentsDTO commentsUpdate = commensServices.updateComments(publicationsId, commentsId, commentsDTO);
        return new ResponseEntity<>(commentsUpdate,HttpStatus.OK);

     }
     @DeleteMapping("/publications/{publicationsId}/comments/{commentsId}")
     public ResponseEntity<String> deleteComments(@PathVariable(value = "publicationsId") Long publicationsId,
     @PathVariable(value = "commentsId") Long commentsId){
        commensServices.deleteComments(publicationsId, commentsId);
        return new ResponseEntity<>("Comments Delete succesfully",HttpStatus.OK);
     }
}

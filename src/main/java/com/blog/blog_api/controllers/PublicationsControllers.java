package com.blog.blog_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_api.dtos.PublicationDTO;
import com.blog.blog_api.dtos.PublicationsResponse;
import com.blog.blog_api.services.PublicationServices;
import com.blog.blog_api.utils.AppsCons;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/publications")
public class PublicationsControllers {
    @Autowired
    private PublicationServices publicationServices;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PublicationDTO> savePublication(@Valid @RequestBody PublicationDTO publicationDTO) {
        return new ResponseEntity<>(publicationServices.createPublication(publicationDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public PublicationsResponse listarPublications(
        @RequestParam(value = "pageNo",defaultValue = AppsCons.pageNumber_for_default,required = false) int pageNumber,
        @RequestParam(value ="pageSize",defaultValue = AppsCons.pageSize_for_default,required = false) int pageSize,
        @RequestParam(value ="sortBy",defaultValue = AppsCons.sortBy_for_default,required = false) String sortBy,
        @RequestParam(value ="sortDir",defaultValue = AppsCons.dirBy_for_default,required = false) String sortDir) {

                return publicationServices.getAllPublications(pageNumber,pageSize,sortBy,sortDir);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublicationForId(@PathVariable(name = "id") long id){
            return ResponseEntity.ok(publicationServices.getPublicationForID(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PublicationDTO> updatePublication(@Valid @RequestBody PublicationDTO publicationDTO,@PathVariable(name = "id") long id ){
        PublicationDTO publicationResponse = publicationServices.updatePublication(publicationDTO,id);
        return new ResponseEntity<>(publicationResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable(name = "id") long id){
        publicationServices.deletePublication(id);
        return new ResponseEntity<>("Publications delete successfully",HttpStatus.OK);
    }
}

package com.blog.blog_api.services;


import com.blog.blog_api.dtos.PublicationDTO;
import com.blog.blog_api.dtos.PublicationsResponse;

public interface PublicationServices {
    public PublicationDTO createPublication(PublicationDTO publicationDTO);
    public PublicationsResponse getAllPublications(int  pageNumber, int pageSize, String sortBy, String sortDir);
    public PublicationDTO getPublicationForID(long id);
    public PublicationDTO updatePublication(PublicationDTO publicationDTO,long id);
    public void deletePublication(long id);


}

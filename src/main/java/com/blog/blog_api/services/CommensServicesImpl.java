package com.blog.blog_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.blog_api.dtos.CommentsDTO;
import com.blog.blog_api.exceptions.BlogAppException;
import com.blog.blog_api.exceptions.ResourceNotFoundEception;
import com.blog.blog_api.models.Comments;
import com.blog.blog_api.models.Publications;
import com.blog.blog_api.repositories.CommentsRepository;
import com.blog.blog_api.repositories.PublicationRepository;
@Service
public class CommensServicesImpl implements CommensServices {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentsDTO createComments(long publicationId, CommentsDTO commentsDTO) {
        Comments comments = mapearComments(commentsDTO);
        Publications publications = publicationRepository.findById(publicationId)
        .orElseThrow(() -> new ResourceNotFoundEception(" it's Publication", "id", publicationId));
        comments.setPublications(publications);
        Comments newComments = commentsRepository.save(comments);
        return mapearDTO(newComments);
    }
    private CommentsDTO mapearDTO(Comments comments){
 
        return modelMapper.map(comments, CommentsDTO.class);
    }
    private Comments mapearComments(CommentsDTO commentsDTO){
        
        return modelMapper.map(commentsDTO, Comments.class);
    }
    @Override
    public List<CommentsDTO> getCommentsForPublicationsId(long publicationsId) {
    List<Comments> comments = commentsRepository.findByPublicationsId(publicationsId);
        return comments.stream().map(comment -> mapearDTO(comment)).collect(Collectors.toList());
    }
    @Override
    public CommentsDTO getCommentsById(Long id, Long commentId) {
        Publications publications = publicationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundEception(" it's Publication", "id",id));
        Comments comments = commentsRepository.findById(commentId).
        orElseThrow(() -> new ResourceNotFoundEception(" it's Publication", "id",commentId));        
        if(!comments.getPublications().getId().equals(publications.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comments does not belong for publication");

        }
        return mapearDTO(comments);
    }
    @Override
    public CommentsDTO updateComments(Long publicationsId, Long commentsId, CommentsDTO commentsDTO) {
        Publications publications = publicationRepository.findById(publicationsId)
        .orElseThrow(() -> new ResourceNotFoundEception(" it's Publication", "id",publicationsId));
        Comments comments = commentsRepository.findById(commentsId).
        orElseThrow(() -> new ResourceNotFoundEception(" it's Publication", "id",commentsId));        
        if(!comments.getPublications().getId().equals(publications.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comments does not belong for publication");

        }
        comments.setName(commentsDTO.getName());
        comments.setEmail(commentsDTO.getEmail());
        comments.setBody(commentsDTO.getBody());

        Comments commentsUpdate = commentsRepository.save(comments);
        return mapearDTO(commentsUpdate);
    }
    @Override
    public void deleteComments(Long publicationsId, Long commentsId) {
        Publications publications = publicationRepository.findById(publicationsId)
        .orElseThrow(() -> new ResourceNotFoundEception(" it's Publication", "id",publicationsId));
        Comments comments = commentsRepository.findById(commentsId).
        orElseThrow(() -> new ResourceNotFoundEception(" it's Publication", "id",commentsId));        
        if(!comments.getPublications().getId().equals(publications.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comments does not belong for publication");

        }
        commentsRepository.delete(comments);
    }
}

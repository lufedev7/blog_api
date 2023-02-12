package com.blog.blog_api.services;

import java.util.List;

import com.blog.blog_api.dtos.CommentsDTO;

public interface CommensServices {
    public CommentsDTO createComments(long publicationId, CommentsDTO commentsDTO);
    public List<CommentsDTO> getCommentsForPublicationsId(long publicationsId);
    public CommentsDTO getCommentsById(Long id, Long commentId);
    public CommentsDTO updateComments(Long publicationsId, Long commentsId, CommentsDTO commentsDTO);
    public void deleteComments(Long publicationsId,Long commentsId);
}

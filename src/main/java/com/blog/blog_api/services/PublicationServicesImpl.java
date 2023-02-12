package com.blog.blog_api.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog_api.dtos.PublicationDTO;
import com.blog.blog_api.exceptions.ResourceNotFoundEception;
import com.blog.blog_api.models.Publications;
import com.blog.blog_api.repositories.PublicationRepository;
import com.blog.blog_api.dtos.PublicationsResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServicesImpl implements PublicationServices {
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {
        Publications publications = mapearEntity(publicationDTO);
        Publications newPublications = publicationRepository.save(publications);
        return mapearDTO(newPublications);
    }

    @Override
    public PublicationsResponse getAllPublications(int  pageNumber, int pageSize, String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending() : 
        Sort.by(sortBy).descending();


        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort );
        Page<Publications> publications = publicationRepository.findAll(pageable);
        List<Publications> listPublications = publications.getContent();
         listPublications.stream().map(publication -> mapearDTO(publication)).collect(Collectors.toList());
         List<PublicationDTO> contenido = listPublications.stream().map(publication -> mapearDTO(publication)).collect(Collectors.toList());
        PublicationsResponse publicationsResponse = new PublicationsResponse();
        publicationsResponse.setContent(contenido);
        publicationsResponse.setPageNumber(publications.getNumber());
        publicationsResponse.setPageSize(publications.getSize());
        publicationsResponse.setTotalElement(publications.getTotalElements());
        publicationsResponse.setTotalPage(publications.getTotalPages());
        publicationsResponse.setLatest(publications.isLast());
        return publicationsResponse;

    }

    private PublicationDTO mapearDTO(Publications publications) {
        PublicationDTO publicationDTO = modelMapper.map(publications, PublicationDTO.class);
        return publicationDTO;
    }

    private Publications mapearEntity(PublicationDTO publicationDTO) {
        Publications publications = modelMapper.map(publicationDTO,Publications.class);
        return publications;
    }

    @Override
    public PublicationDTO getPublicationForID(long id) {
        Publications publications = publicationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundEception(" it's Publication", "id", id));
return mapearDTO(publications);
    }

    @Override
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, long id) {
        Publications publications = publicationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundEception(" it's Publication", "id", id));

        publications.setTittle(publicationDTO.getTittle());
        publications.setDescription(publicationDTO.getDescription());
        publications.setContent(publicationDTO.getContent());
        Publications updatePublication = publicationRepository.save(publications);

        return mapearDTO(updatePublication);
    }

    @Override
    public void deletePublication(long id) {
             Publications publications = publicationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundEception(" it's Publication", "id", id));
        publicationRepository.delete(publications);
    }
}

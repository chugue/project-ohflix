package com.project.ohflix.domain.content;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public List<ContentResponse.LatestContentDTO> findLatestContent() {
        List<Content> latestContentList = contentRepository.findLatestContent();
        System.out.println(latestContentList);

        return latestContentList.stream().map(content
                -> new ContentResponse.LatestContentDTO(content)).toList();
    }
}






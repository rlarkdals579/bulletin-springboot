package com.kangmin.book.springboot.service;

import com.kangmin.book.springboot.domain.posts.Posts;
import com.kangmin.book.springboot.domain.posts.PostsRepository;
import com.kangmin.book.springboot.web.dto.PostsListResponseDto;
import com.kangmin.book.springboot.web.dto.PostsResponseDto;
import com.kangmin.book.springboot.web.dto.PostsSaveRequestDto;
import com.kangmin.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();

    }

    // there is no functions sending queries to database, because of JPAs' Persistence Context
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("There is no corresponding post. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;

    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("There is no corresponding post. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}

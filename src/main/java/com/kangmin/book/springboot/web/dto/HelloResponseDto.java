package com.kangmin.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // create get method in all field which declared, do not need to declare get method manually
@RequiredArgsConstructor // Create Constructor automatically
public class HelloResponseDto {

    private final String name;
    private final int amount;

}

package com.App_Boot.Application.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDto> content;
    private Integer pageNumber;
    private  Integer pageSize;
    private  long totalElement;
    private Integer totalPages;

    private boolean lastPage;


}

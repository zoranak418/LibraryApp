package com.example.library.dto;

import com.example.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCopyRequest {
    private Long book;
    private Long count;
}

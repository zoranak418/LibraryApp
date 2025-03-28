package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaseResponse {
    private Long id;
    private Date fromDate;
    private Date toDate;
    private String comments;
    private String title;
    private String author;
    private Long copy;
    private String user;
}

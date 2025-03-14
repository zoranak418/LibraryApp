package com.example.library.dto;

import com.example.library.model.Copy;
import com.example.library.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLeaseRequest {

    private Date fromDate;
    private Date toDate;
    private String comments;
    private Long copy;
    private Long user;
}

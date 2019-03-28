package com.aryzhkov.movieland.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SessionDto {

    private String uuid;
    private String nickname;
}

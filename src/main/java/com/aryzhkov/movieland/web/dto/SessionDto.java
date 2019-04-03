package com.aryzhkov.movieland.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionDto {

    private String uuid;
    private String nickname;
}

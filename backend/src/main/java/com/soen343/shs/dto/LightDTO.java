package com.soen343.shs.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Builder
public class LightDTO {
    private Long id;
    private boolean isLightOn;
}
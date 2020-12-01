package com.soen343.shs.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseTemperatureStatusDTO {
    private Boolean success;
    private String alertMessage = "";

}

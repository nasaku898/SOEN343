package com.soen343.shs.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoadDoorDTO {
    private boolean open;
    private Set<String> rooms;
}

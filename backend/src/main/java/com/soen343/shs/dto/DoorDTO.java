package com.soen343.shs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DoorDTO {
    private long id;
    private boolean open;
    private boolean locked;
    private Set<String> rooms;
}

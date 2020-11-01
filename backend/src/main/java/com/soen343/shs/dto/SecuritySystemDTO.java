package com.soen343.shs.dto;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SecuritySystemDTO {
    private long id;
    private long houseId;
    private Set<RoomDTO> rooms;
    private boolean away;
    private boolean auto;
}

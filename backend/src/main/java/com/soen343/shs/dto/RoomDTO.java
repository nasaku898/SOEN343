package com.soen343.shs.dto;

import com.soen343.shs.dal.model.Door;
import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.model.Window;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RoomDTO {
    private String name;
    private List<Door> doors;
    private List<Window> windows;
    private List<Light> lights;
    private double temperature;
    private Set<Long> userIds;
}

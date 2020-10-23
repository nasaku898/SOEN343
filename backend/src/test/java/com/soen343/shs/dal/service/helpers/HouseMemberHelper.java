package com.soen343.shs.dal.service.helpers;

import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.model.UserRole;
import com.soen343.shs.dto.HouseMemberDTO;

import java.util.Collections;
import java.util.HashSet;

public class HouseMemberHelper {
    public final static long MOCK_ROOM_ID = 1;
    public final static String MOCK_ROOM_NAME = "SAUNA";
    public final static long MOCK_HOUSE_MEMBER_ID = 1;
    public final static long MOCK_ID = 1;
    public final static String MOCK_HOUSE_MEMBER_NAME = "John";
    public final static long MOCK_HOUSE_WINDOW_ID = 1;

    public static HouseMember buildMockHouseMember() {
        return HouseMember.builder()
                .id(MOCK_HOUSE_MEMBER_ID)
                .username(MOCK_HOUSE_MEMBER_NAME)
                .role(UserRole.PARENT)
                .location(buildMockRoom())
                .build();
    }

    private static Room buildMockRoom() {
        return Room.builder()
                .id(MOCK_ROOM_ID)
                .name("MockRoom")
                .temperature(0)
                .doors(new HashSet<>())
                .lights(new HashSet<>())
                .userIds(new HashSet<>())
                .houseWindows(new HashSet<>())
                .build();
    }

    public static HouseMemberDTO getHouseMemberDTO() {
        return HouseMemberDTO.builder()
                .id(MOCK_HOUSE_MEMBER_ID)
                .username(MOCK_HOUSE_MEMBER_NAME)
                .role((UserRole.PARENT))
                .roomId(Collections.singletonMap(MOCK_ROOM_ID, MOCK_ROOM_NAME))
                .build();
    }

}

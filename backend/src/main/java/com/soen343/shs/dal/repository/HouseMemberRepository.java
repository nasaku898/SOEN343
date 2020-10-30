package com.soen343.shs.dal.repository;

import com.soen343.shs.dal.model.HouseMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseMemberRepository extends JpaRepository<HouseMember, Long> {
}

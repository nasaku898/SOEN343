package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.SHSSecurity;
import com.soen343.shs.dal.repository.SHSSecurityRepository;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SHSSecurityService {

    private final SHSSecurityRepository repository;

    public SHSSecurity getSHSSecurity(final long id) {
        return repository.findById(id).orElseThrow(() -> new SHSNotFoundException("Security service with id " + id + " was not found "));
    }
}

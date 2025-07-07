package com.girao.async_order.adapters.outbound.persistence;

import com.girao.async_order.domain.repositories.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator implements IdGenerator {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();

    }

}

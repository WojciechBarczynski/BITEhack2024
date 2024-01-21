package com.example.backend.friend.responses;

import com.example.backend.addiction.dtos.AddictionDto;

import java.util.List;

public record AllAddictsResponse(Integer addictId, String name, List<AddictionDto> addictions) {
}

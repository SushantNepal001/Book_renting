package com.rastapi.brs.service;

import com.rastapi.brs.Dto.RentDto;

import java.util.List;

public interface RentService {
    List<RentDto> findAllRent();
    RentDto saveRent(RentDto rentDto);
    RentDto findById(Integer id);
    void deleteRentById(Integer id);
}

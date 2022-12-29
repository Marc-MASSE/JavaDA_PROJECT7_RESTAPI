package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.DTO.BidListDTO;
import com.nnk.springboot.domain.BidList;

import javassist.NotFoundException;

public interface IBidListService {
	
    BidList addBidList(BidListDTO bidListDTO);

    List<BidListDTO> getBidLists();

    BidListDTO getBidListById(Integer id) throws NotFoundException;

    BidList updateBidList(Integer id, BidListDTO bidListDTO) throws NotFoundException;

    void deleteBidList(Integer id) throws NotFoundException;


}

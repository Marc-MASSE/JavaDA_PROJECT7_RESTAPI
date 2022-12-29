package com.nnk.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.DTO.BidListDTO;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.IBidListService;

import javassist.NotFoundException;

public class BidListServiceImpl implements IBidListService {
	
	private BidListRepository bidListRepository;
	
	@Autowired
	public BidListServiceImpl(BidListRepository bidListRepository) {
		this.bidListRepository = bidListRepository;
	}

	@Override
	public BidList addBidList(BidListDTO bidListDTO) {
		BidList bidList = BidList.builder()
				.account(bidListDTO.getAccount())
				.type(bidListDTO.getType())
				.bidQuantity(bidListDTO.getBidQuantity())
				.build();
		return bidListRepository.save(bidList);
	}

	@Override
	public List<BidListDTO> getBidLists() {
		List<BidList> bidListList = bidListRepository.findAllByOrderByBidListIdDesc();
		return bidListList.stream()
				.map(b -> bidListToDTOConverter(b))
				.collect(Collectors.toList());
	}

	@Override
	public BidListDTO getBidListById(Integer id) throws NotFoundException {
		// TODO Verify NotFoundException method
		if (bidListRepository.existsById(id)) {
			return bidListToDTOConverter(bidListRepository.getById(id));
		}
		throw new NotFoundException("There is no BidList with id = "+id);
		//return new BidListDTO();

	}

	@Override
	@Transactional
	public BidList updateBidList(Integer id, BidListDTO bidListDTO) throws NotFoundException {
		// TODO Verify NotFoundException method
		if (bidListRepository.existsById(id)) {
			BidList bidList = bidListRepository.getById(id);
			bidList.setAccount(bidListDTO.getAccount());
			bidList.setType(bidListDTO.getType());
			bidList.setBidQuantity(bidListDTO.getBidQuantity());
			return bidListRepository.save(bidList);
		}
		throw new NotFoundException("There is no BidList with id = "+id);
	}

	@Override
	@Transactional
	public void deleteBidList(Integer id) throws NotFoundException {
		// TODO Verify NotFoundException method
		if (bidListRepository.existsById(id)) {
			bidListRepository.deleteById(id);
		}
		throw new NotFoundException("There is no BidList with id = "+id);
	}
	
	public BidListDTO bidListToDTOConverter(BidList bidList) {
		return BidListDTO.builder()
				.bidListId(bidList.getBidListId())
				.account(bidList.getAccount())
				.type(bidList.getType())
				.bidQuantity(bidList.getBidQuantity())
				.build();
	}

}

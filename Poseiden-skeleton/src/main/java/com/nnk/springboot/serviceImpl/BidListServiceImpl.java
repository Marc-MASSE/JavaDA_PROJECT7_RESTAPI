package com.nnk.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.DTO.BidListDTO;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.IBidListService;

import javassist.NotFoundException;

@Service
public class BidListServiceImpl implements IBidListService {
	
	static Logger log = LogManager.getLogger(BidListServiceImpl.class.getName());
	
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
		log.info("BidList list = {}",bidListList);
		return bidListList.stream()
				.map(b -> bidListToDTOMapper(b))
				.collect(Collectors.toList());
	}

	@Override
	public BidListDTO getBidListById(Integer id) {
		if (bidListRepository.existsById(id)) {
			return bidListToDTOMapper(bidListRepository.getById(id));
		}
		return new BidListDTO();

	}

	@Override
	public BidList updateBidList(Integer id, BidListDTO bidListDTO) {
		if (bidListRepository.existsById(id)) {
			BidList bidList = bidListRepository.getById(id);
			bidList.setAccount(bidListDTO.getAccount());
			bidList.setType(bidListDTO.getType());
			bidList.setBidQuantity(bidListDTO.getBidQuantity());
			return bidListRepository.save(bidList);
		}
		return new BidList();
	}

	@Override
	public void deleteBidList(Integer id) {
		if (bidListRepository.existsById(id)) {
			bidListRepository.deleteById(id);
		}
	}
	
	@Override
	public BidListDTO bidListToDTOMapper(BidList bidList) {
		return BidListDTO.builder()
				.bidListId(bidList.getBidListId())
				.account(bidList.getAccount())
				.type(bidList.getType())
				.bidQuantity(bidList.getBidQuantity())
				.build();
	}

}

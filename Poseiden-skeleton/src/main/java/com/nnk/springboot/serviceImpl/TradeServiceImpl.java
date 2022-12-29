package com.nnk.springboot.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.ITradeService;

public class TradeServiceImpl implements ITradeService {

	private TradeRepository tradeRepository;
	
	@Autowired
	public TradeServiceImpl(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}
	
}

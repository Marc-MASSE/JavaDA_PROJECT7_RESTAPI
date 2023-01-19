package com.nnk.springboot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.DTO.RuleNameDTO;
import com.nnk.springboot.DTO.TradeDTO;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.ITradeService;

@Service
public class TradeServiceImpl implements ITradeService {

	private TradeRepository tradeRepository;
	
	@Autowired
	public TradeServiceImpl(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

	@Override
	public Trade addTrade(TradeDTO tradeDTO) {
		Trade trade = Trade.builder()
				.account(tradeDTO.getAccount())
				.type(tradeDTO.getType())
				.buyQuantity(tradeDTO.getBuyQuantity())
				.build();
		return tradeRepository.save(trade);
	}

	@Override
	public List<TradeDTO> getTrades() {
		List<Trade> tradeList = tradeRepository.findAllByOrderByTradeIdDesc();
		return tradeList.stream()
				.map(t -> tradeToDTOMapper(t))
				.collect(Collectors.toList());
	}

	@Override
	public TradeDTO getTradeById(Integer id) {
		if (tradeRepository.existsById(id)) {
			return tradeToDTOMapper(tradeRepository.getById(id));
		}
		return new TradeDTO();
	}

	@Override
	public Trade updateTrade(Integer id, TradeDTO tradeDTO) {
		if (tradeRepository.existsById(id)) {
			Trade trade = tradeRepository.getById(id);
			trade.setAccount(tradeDTO.getAccount());
			trade.setType(tradeDTO.getType());
			trade.setBuyQuantity(tradeDTO.getBuyQuantity());
			return tradeRepository.save(trade);
		}
		return new Trade();
	}

	@Override
	public void deleteTrade(Integer id) {
		if (tradeRepository.existsById(id)) {
			tradeRepository.deleteById(id);
		}
	}
	
	@Override
	public TradeDTO tradeToDTOMapper(Trade trade) {
		return TradeDTO.builder()
				.tradeId(trade.getTradeId())
				.account(trade.getAccount())
				.type(trade.getType())
				.buyQuantity(trade.getBuyQuantity())
				.build();
	}
	
}

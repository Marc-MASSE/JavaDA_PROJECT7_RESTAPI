package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.DTO.TradeDTO;
import com.nnk.springboot.domain.Trade;

public interface ITradeService {
	
    Trade addTrade(TradeDTO tradeDTO);

    List<TradeDTO> getTrades();

    TradeDTO getTradeById(Integer id);

    Trade updateTrade(Integer id, TradeDTO tradeDTO);

    void deleteTrade(Integer id);

}

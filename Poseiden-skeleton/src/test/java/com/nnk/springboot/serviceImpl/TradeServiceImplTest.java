package com.nnk.springboot.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.DTO.TradeDTO;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.ITradeService;

@ExtendWith(MockitoExtension.class)
public class TradeServiceImplTest {
	
	private ITradeService tradeService;
	
	@Mock
	private TradeRepository tradeRepository;
	
	private Trade trade1;
	private Trade trade2;
	private TradeDTO tradeDTO1;
	private TradeDTO tradeDTO2;
	
	@Captor
	ArgumentCaptor<Trade> tradeCaptor;
	@Captor
	ArgumentCaptor<Integer> idCaptor;
	
	@BeforeEach
	public void init() {
		tradeService = new TradeServiceImpl(tradeRepository);
		trade1 = Trade.builder()
				.tradeId(1)
				.account("111")
				.type("type1")
				.buyQuantity(11.0)
				.build();
		trade2 = Trade.builder()
				.tradeId(2)
				.account("222")
				.type("type2")
				.buyQuantity(22.0)
				.build();
		tradeDTO1 = TradeDTO.builder()
				.tradeId(1)
				.account("111")
				.type("type1")
				.buyQuantity(11.0)
				.build();
		tradeDTO2 = TradeDTO.builder()
				.tradeId(2)
				.account("222")
				.type("type2")
				.buyQuantity(22.0)
				.build();
	}
	
	// Test addTrade
	@Test
	public void addTrade_success() {
		TradeDTO tradeDTO = TradeDTO.builder()
				.account("444")
				.type("type")
				.buyQuantity(44.0)
				.build();
		Trade trade = Trade.builder()
				.account("444")
				.type("type")
				.buyQuantity(44.0)
				.build();
		when(tradeRepository.save(any(Trade.class)))
			.thenReturn(trade);
		tradeService.addTrade(tradeDTO);
		verify(tradeRepository).save(tradeCaptor.capture());
		assertThat(tradeCaptor.getValue()).isEqualTo(trade);
		verify(tradeRepository).save(trade);
	}
	
	// Test getTrades
	@Test
	public void getTrades_success() {
		when(tradeRepository.findAllByOrderByTradeIdDesc())
			.thenReturn(Arrays.asList(trade2,trade1));
		assertThat(tradeService.getTrades())
			.contains(tradeDTO1)
			.contains(tradeDTO2);
		verify(tradeRepository).findAllByOrderByTradeIdDesc();
	}
	
	// Test getTradeById
	@Nested
	class GetTradeById {
		@Test
		public void success() {
			when(tradeRepository.existsById(1))
				.thenReturn(true);
			when(tradeRepository.getById(1))
				.thenReturn(trade1);
			assertThat(tradeService.getTradeById(1)
				.equals(tradeDTO1));
			verify(tradeRepository).existsById(1);
			verify(tradeRepository).getById(1);
		}
		@Test
		public void no_trade() {
			when(tradeRepository.existsById(15))
				.thenReturn(false);
			assertThat(tradeService.getTradeById(15))
				.isEqualTo(new TradeDTO());
			verify(tradeRepository).existsById(15);
		}
	}
	
	// Test updateTrade
	@Nested
	class UpdateTradeById {
		@Test
		public void success() {
			TradeDTO tradeDTOToUpdate = TradeDTO.builder()
					.account("444")
					.type("type")
					.buyQuantity(44.0)
					.build();
			Trade trade1Updated = Trade.builder()
					.tradeId(1)
					.account("444")
					.type("type")
					.buyQuantity(44.0)
					.build();
			when(tradeRepository.existsById(1))
				.thenReturn(true);
			when(tradeRepository.getById(1))
				.thenReturn(trade1);
			when(tradeRepository.save(trade1Updated))
				.thenReturn(trade1Updated);
			assertThat(tradeService.updateTrade(1,tradeDTOToUpdate)
				.equals(trade1Updated));
			verify(tradeRepository).existsById(1);
			verify(tradeRepository).getById(1);
			verify(tradeRepository).save(trade1Updated);
		}
		@Test
		public void no_trade() {
			TradeDTO tradeDTOToUpdate = TradeDTO.builder()
					.account("444")
					.type("type")
					.buyQuantity(44.0)
					.build();
			when(tradeRepository.existsById(15))
				.thenReturn(false);
			assertThat(tradeService.updateTrade(15,tradeDTOToUpdate))
				.isEqualTo(new Trade());
			verify(tradeRepository).existsById(15);
		}
	}
	
	// Test deleteTrade
	@Nested
	class DeleteTradeById {
		@Test
		public void success() {
			when(tradeRepository.existsById(1))
				.thenReturn(true);
			tradeService.deleteTrade(1);
			verify(tradeRepository).existsById(1);
			verify(tradeRepository).deleteById(idCaptor.capture());
			assertThat(idCaptor.getValue()).isEqualTo(1);
			verify(tradeRepository).deleteById(1);
		}
		@Test
		public void no_trade() {
			when(tradeRepository.existsById(15))
				.thenReturn(false);
			tradeService.deleteTrade(15);
			verify(tradeRepository).existsById(15);
		}
	}
	
	// Test tradeToDTOMapper
	@Test
	public void mapper_success() {
		assertThat(tradeService.tradeToDTOMapper(trade1))
			.isEqualTo(tradeDTO1);
	}

}

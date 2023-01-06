package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeRepositoryTest {

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void tradeTest() {
		Trade trade = new Trade("Trade Account", "Type");

		// Save
		trade = tradeRepository.save(trade);
		//Assert.assertNotNull(trade.getTradeId());
		assertThat(trade.getTradeId()).isNotNull();
		//Assert.assertTrue(trade.getAccount().equals("Trade Account"));
		assertThat(trade.getAccount()).isEqualTo("Trade Account");

		// Update
		trade.setAccount("Trade Account Update");
		trade = tradeRepository.save(trade);
		//Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));
		assertThat(trade.getAccount()).isEqualTo("Trade Account Update");

		// Find
		//List<Trade> listResult = tradeRepository.findAll();
		//Assert.assertTrue(listResult.size() > 0);
		assertThat(tradeRepository.findAll()).isNotEmpty();

		// Delete
		Integer id = trade.getTradeId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		//Assert.assertFalse(tradeList.isPresent());
		assertThat(tradeList).isEmpty();
	}
}

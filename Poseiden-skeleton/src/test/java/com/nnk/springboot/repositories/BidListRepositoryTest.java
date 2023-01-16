package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BidListRepositoryTest {

	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void bidListTest() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);

		// Save
		bid = bidListRepository.save(bid);
		assertThat(bid.getBidListId()).isNotNull();
		assertThat(bid.getBidQuantity()).isEqualTo(10d);
		

		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		assertThat(bid.getBidQuantity()).isEqualTo(20d);

		// Find
		assertThat(bidListRepository.findAll()).isNotEmpty();

		// Delete
		Integer id = bid.getBidListId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		assertThat(bidList).isEmpty();
	}
}

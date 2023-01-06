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
		//Assert.assertNotNull(bid.getBidListId());
		assertThat(bid.getBidListId()).isNotNull();
		//Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);
		assertThat(bid.getBidQuantity()).isEqualTo(10d);
		

		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		//Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);
		assertThat(bid.getBidQuantity()).isEqualTo(20d);

		// Find
		//List<BidList> listResult = bidListRepository.findAll();
		//Assert.assertTrue(listResult.size() > 0);
		assertThat(bidListRepository.findAll()).isNotEmpty();

		// Delete
		Integer id = bid.getBidListId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		//Assert.assertFalse(bidList.isPresent());
		assertThat(bidList).isEmpty();
	}
}

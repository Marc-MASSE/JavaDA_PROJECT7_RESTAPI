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

import com.nnk.springboot.DTO.BidListDTO;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.IBidListService;

@ExtendWith(MockitoExtension.class)
public class BidListServiceImplTest {
	
	private IBidListService bidListService;
	
	@Mock
	private BidListRepository bidListRepository;
	
	private BidList bidList1;
	private BidList bidList2;
	private BidListDTO bidListDTO1;
	private BidListDTO bidListDTO2;
	
	@Captor
	ArgumentCaptor<BidList> bidListCaptor;
	@Captor
	ArgumentCaptor<Integer> idCaptor;
	
	@BeforeEach
	public void init() {
		bidListService = new BidListServiceImpl(bidListRepository);
		bidList1 = BidList.builder()
				.bidListId(1)
				.account("Account1")
				.type("Type1")
				.bidQuantity(1.0)
				.build();
		bidList2 = BidList.builder()
				.bidListId(2)
				.account("Account2")
				.type("Type2")
				.bidQuantity(2.0)
				.build();
		bidListDTO1 = BidListDTO.builder()
				.bidListId(1)
				.account("Account1")
				.type("Type1")
				.bidQuantity(1.0)
				.build();
		bidListDTO2 = BidListDTO.builder()
				.bidListId(2)
				.account("Account2")
				.type("Type2")
				.bidQuantity(2.0)
				.build();
	}
	
	// Test addBidList
	@Test
	public void addBidList_success() {
		BidListDTO bidListDTO = BidListDTO.builder()
				.account("Account")
				.type("Type")
				.bidQuantity(4.0)
				.build();
		BidList bidList = BidList.builder()
				.account("Account")
				.type("Type")
				.bidQuantity(4.0)
				.build();
		when(bidListRepository.save(any(BidList.class)))
			.thenReturn(bidList);
		bidListService.addBidList(bidListDTO);
		verify(bidListRepository).save(bidListCaptor.capture());
		assertThat(bidListCaptor.getValue()).isEqualTo(bidList);
		verify(bidListRepository).save(bidList);
	}
	
	// Test getBidLists
	@Test
	public void getBidLists_success() {
		when(bidListRepository.findAllByOrderByBidListIdDesc())
			.thenReturn(Arrays.asList(bidList2,bidList1));
		assertThat(bidListService.getBidLists())
			.contains(bidListDTO1)
			.contains(bidListDTO2);
		verify(bidListRepository).findAllByOrderByBidListIdDesc();
	}
	
	// Test getBidListById
	@Nested
	class GetBidListById {
		@Test
		public void success() {
			when(bidListRepository.existsById(1))
				.thenReturn(true);
			when(bidListRepository.getById(1))
				.thenReturn(bidList1);
			assertThat(bidListService.getBidListById(1)
				.equals(bidListDTO1));
			verify(bidListRepository).existsById(1);
			verify(bidListRepository).getById(1);
		}
		@Test
		public void no_bidList() {
			when(bidListRepository.existsById(15))
				.thenReturn(false);
			assertThat(bidListService.getBidListById(15))
				.isEqualTo(new BidListDTO());
			verify(bidListRepository).existsById(15);
		}
	}
	
	// Test updateBidList
	@Nested
	class UpdateBidListById {
		@Test
		public void success() {
			BidListDTO bidListDTOToUpdate = BidListDTO.builder()
					.account("Account4")
					.type("Type4")
					.bidQuantity(4.0)
					.build();
			BidList bidList1Updated = BidList.builder()
					.bidListId(1)
					.account("Account4")
					.type("Type4")
					.bidQuantity(4.0)
					.build();
			when(bidListRepository.existsById(1))
				.thenReturn(true);
			when(bidListRepository.getById(1))
				.thenReturn(bidList1);
			when(bidListRepository.save(bidList1Updated))
				.thenReturn(bidList1Updated);
			assertThat(bidListService.updateBidList(1,bidListDTOToUpdate)
				.equals(bidList1Updated));
			verify(bidListRepository).existsById(1);
			verify(bidListRepository).getById(1);
			verify(bidListRepository).save(bidList1Updated);
		}
		@Test
		public void no_bidList() {
			BidListDTO bidListDTOToUpdate = BidListDTO.builder()
					.account("Account4")
					.type("Type4")
					.bidQuantity(4.0)
					.build();
			when(bidListRepository.existsById(15))
				.thenReturn(false);
			assertThat(bidListService.updateBidList(15,bidListDTOToUpdate))
				.isEqualTo(new BidList());
			verify(bidListRepository).existsById(15);
		}
	}
	
	// Test deleteBidList
	@Nested
	class DeletBidListById {
		@Test
		public void success() {
			when(bidListRepository.existsById(1))
				.thenReturn(true);
			bidListService.deleteBidList(1);
			verify(bidListRepository).existsById(1);
			verify(bidListRepository).deleteById(idCaptor.capture());
			assertThat(idCaptor.getValue()).isEqualTo(1);
			verify(bidListRepository).deleteById(1);
		}
		@Test
		public void no_bidList() {
			when(bidListRepository.existsById(15))
				.thenReturn(false);
			bidListService.deleteBidList(15);
			verify(bidListRepository).existsById(15);
		}
	}
	
	// Test bidListToDTOMapper
	@Test
	public void mapper_success() {
		assertThat(bidListService.bidListToDTOMapper(bidList1))
			.isEqualTo(bidListDTO1);
	}

}

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

import com.nnk.springboot.DTO.CurvePointDTO;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.ICurvePointService;

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceImplTest {
	
	private ICurvePointService curvePointService;
	
	@Mock
	private CurvePointRepository curvePointRepository;
	
	private CurvePoint curvePoint1;
	private CurvePoint curvePoint2;
	private CurvePointDTO curvePointDTO1;
	private CurvePointDTO curvePointDTO2;
	
	@Captor
	ArgumentCaptor<CurvePoint> curvePointCaptor;
	@Captor
	ArgumentCaptor<Integer> idCaptor;
	
	@BeforeEach
	public void init() {
		curvePointService = new CurvePointServiceImpl(curvePointRepository);
		curvePoint1 = CurvePoint.builder()
				.id(1)
				.curveId(1)
				.term(1.0)
				.value(11.0)
				.build();
		curvePoint2 = CurvePoint.builder()
				.id(2)
				.curveId(2)
				.term(2.0)
				.value(22.0)
				.build();
		curvePointDTO1 = CurvePointDTO.builder()
				.id(1)
				.curveId(1)
				.term(1.0)
				.value(11.0)
				.build();
		curvePointDTO2 = CurvePointDTO.builder()
				.id(2)
				.curveId(2)
				.term(2.0)
				.value(22.0)
				.build();
	}
	
	// Test addCurvePoint
	@Test
	public void addCurvePoint_success() {
		CurvePointDTO curvePointDTO = CurvePointDTO.builder()
				.curveId(3)
				.term(3.0)
				.value(33.0)
				.build();
		CurvePoint curvePoint = CurvePoint.builder()
				.curveId(3)
				.term(3.0)
				.value(33.0)
				.build();
		when(curvePointRepository.save(any(CurvePoint.class)))
			.thenReturn(curvePoint);
		curvePointService.addCurvePoint(curvePointDTO);
		verify(curvePointRepository).save(curvePointCaptor.capture());
		assertThat(curvePointCaptor.getValue()).isEqualTo(curvePoint);
		verify(curvePointRepository).save(curvePoint);
	}
	
	// Test getCurvePoints
	@Test
	public void getCurvePoints_success() {
		when(curvePointRepository.findAllByOrderByIdDesc())
			.thenReturn(Arrays.asList(curvePoint2,curvePoint1));
		assertThat(curvePointService.getCurvePoints())
			.contains(curvePointDTO1)
			.contains(curvePointDTO2);
		verify(curvePointRepository).findAllByOrderByIdDesc();
	}
	
	// Test getCurvePointById
	@Nested
	class GetCurvePointById {
		@Test
		public void success() {
			when(curvePointRepository.existsById(1))
				.thenReturn(true);
			when(curvePointRepository.getById(1))
				.thenReturn(curvePoint1);
			assertThat(curvePointService.getCurvePointById(1)
				.equals(curvePointDTO1));
			verify(curvePointRepository).existsById(1);
			verify(curvePointRepository).getById(1);
		}
		@Test
		public void no_curvePoint() {
			when(curvePointRepository.existsById(15))
				.thenReturn(false);
			assertThat(curvePointService.getCurvePointById(15))
				.isEqualTo(new CurvePointDTO());
			verify(curvePointRepository).existsById(15);
		}
	}
	
	// Test updateCurvePoint
	@Nested
	class UpdateCurvePointById {
		@Test
		public void success() {
			CurvePointDTO curvePointDTOToUpdate = CurvePointDTO.builder()
					.curveId(4)
					.term(4.0)
					.value(44.0)
					.build();
			CurvePoint curvePoint1Updated = CurvePoint.builder()
					.id(1)
					.curveId(4)
					.term(4.0)
					.value(44.0)
					.build();
			when(curvePointRepository.existsById(1))
				.thenReturn(true);
			when(curvePointRepository.getById(1))
				.thenReturn(curvePoint1);
			when(curvePointRepository.save(curvePoint1Updated))
				.thenReturn(curvePoint1Updated);
			assertThat(curvePointService.updateCurvePoint(1,curvePointDTOToUpdate)
				.equals(curvePoint1Updated));
			verify(curvePointRepository).existsById(1);
			verify(curvePointRepository).getById(1);
			verify(curvePointRepository).save(curvePoint1Updated);
		}
		@Test
		public void no_curvePoint() {
			CurvePointDTO curvePointDTOToUpdate = CurvePointDTO.builder()
					.curveId(4)
					.term(4.0)
					.value(44.0)
					.build();
			when(curvePointRepository.existsById(15))
				.thenReturn(false);
			assertThat(curvePointService.updateCurvePoint(15,curvePointDTOToUpdate))
				.isEqualTo(new CurvePoint());
			verify(curvePointRepository).existsById(15);
		}
	}
	
	// Test deleteCurvePoint
	@Nested
	class DeletCurvePointById {
		@Test
		public void success() {
			when(curvePointRepository.existsById(1))
				.thenReturn(true);
			curvePointService.deleteCurvePoint(1);
			verify(curvePointRepository).existsById(1);
			verify(curvePointRepository).deleteById(idCaptor.capture());
			assertThat(idCaptor.getValue()).isEqualTo(1);
			verify(curvePointRepository).deleteById(1);
		}
		@Test
		public void no_curvePoint() {
			when(curvePointRepository.existsById(15))
				.thenReturn(false);
			curvePointService.deleteCurvePoint(15);
			verify(curvePointRepository).existsById(15);
		}
	}
	
	// Test curvePointToDTOMapper
	@Test
	public void mapper_success() {
		assertThat(curvePointService.curvePointToDTOMapper(curvePoint1))
			.isEqualTo(curvePointDTO1);
	}
	
}

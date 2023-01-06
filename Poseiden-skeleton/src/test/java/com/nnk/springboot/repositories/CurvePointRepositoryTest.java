package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointRepositoryTest {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

		// Save
		curvePoint = curvePointRepository.save(curvePoint);
		//Assert.assertNotNull(curvePoint.getId());
		assertThat(curvePoint.getId()).isNotNull();
		//Assert.assertTrue(curvePoint.getCurveId() == 10);
		assertThat(curvePoint.getCurveId()).isEqualTo(10);

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		//Assert.assertTrue(curvePoint.getCurveId() == 20);
		assertThat(curvePoint.getCurveId()).isEqualTo(20);

		// Find
		//List<CurvePoint> listResult = curvePointRepository.findAll();
		//Assert.assertTrue(listResult.size() > 0);
		assertThat(curvePointRepository.findAll()).isNotEmpty();

		// Delete
		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		//Assert.assertFalse(curvePointList.isPresent());
		assertThat(curvePointList).isEmpty();
	}

}

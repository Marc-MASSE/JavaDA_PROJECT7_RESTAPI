package com.nnk.springboot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.DTO.CurvePointDTO;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.ICurvePointService;

@Service
public class CurvePointServiceImpl implements ICurvePointService {
	
	static Logger log = LogManager.getLogger(CurvePointServiceImpl.class.getName());
	
	private CurvePointRepository curvePointRepository;
	
	@Autowired
	public CurvePointServiceImpl(CurvePointRepository curvePointRepository) {
		this.curvePointRepository = curvePointRepository;
	}

	@Override
	public CurvePoint addCurvePoint(CurvePointDTO curvePointDTO) {
		CurvePoint curvePoint = CurvePoint.builder()
				.curveId(curvePointDTO.getCurveId())
				.term(curvePointDTO.getTerm())
				.value(curvePointDTO.getValue())
				.build();
		return curvePointRepository.save(curvePoint);
	}

	@Override
	public List<CurvePointDTO> getCurvePoints() {
		List<CurvePoint> curvePointList = curvePointRepository.findAllByOrderByIdDesc();
		log.info("CurvePoint list = {}",curvePointList);
		return curvePointList.stream()
				.map(c -> curvePointToDTOMapper(c))
				.collect(Collectors.toList());
	}

	@Override
	public CurvePointDTO getCurvePointById(Integer id) {
		if (curvePointRepository.existsById(id)) {
			return curvePointToDTOMapper(curvePointRepository.getById(id));
		}
		return new CurvePointDTO();
	}

	@Override
	public CurvePoint updateCurvePoint(Integer id, CurvePointDTO curvePointDTO) {
		if (curvePointRepository.existsById(id)) {
			CurvePoint curvePoint = curvePointRepository.getById(id);
			curvePoint.setCurveId(curvePointDTO.getCurveId());
			curvePoint.setTerm(curvePointDTO.getTerm());
			curvePoint.setValue(curvePointDTO.getValue());
			return curvePointRepository.save(curvePoint);
		}
		return new CurvePoint();
	}

	@Override
	public void deleteCurvePoint(Integer id) {
		if (curvePointRepository.existsById(id)) {
			curvePointRepository.deleteById(id);
		}
	}
	
	public CurvePointDTO curvePointToDTOMapper(CurvePoint curvePoint) {
		return CurvePointDTO.builder()
				.id(curvePoint.getId())
				.curveId(curvePoint.getCurveId())
				.term(curvePoint.getTerm())
				.value(curvePoint.getValue())
				.build();
	}

}

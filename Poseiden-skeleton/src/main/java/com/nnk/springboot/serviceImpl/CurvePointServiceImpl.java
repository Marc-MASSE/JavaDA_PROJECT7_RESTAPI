package com.nnk.springboot.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.ICurvePointService;

public class CurvePointServiceImpl implements ICurvePointService {
	
	private CurvePointRepository curvePointRepository;
	
	@Autowired
	public CurvePointServiceImpl(CurvePointRepository curvePointRepository) {
		this.curvePointRepository = curvePointRepository;
	}

}

package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.DTO.CurvePointDTO;
import com.nnk.springboot.domain.CurvePoint;

public interface ICurvePointService {
	
    CurvePoint addCurvePoint(CurvePointDTO curvePointDTO);

    List<CurvePointDTO> getCurvePoints();

    CurvePointDTO getCurvePointById(Integer id);

    CurvePoint updateCurvePoint(Integer id, CurvePointDTO curvePointDTO);

    void deleteCurvePoint(Integer id);

}

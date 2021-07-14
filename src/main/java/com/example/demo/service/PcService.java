package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.model.PcModel;
import com.example.demo.model.PcType;
import com.example.demo.respository.IPcRespository;
import com.example.demo.utils.CommonConst;

///@Component – Sử dụng để định nghĩa một khuôn mẫu chung cho toàn bộ dự án
@Component
public class PcService {
	@Autowired
	IPcRespository pcRespository;
// gọi ra tất cả dữ liệu
	public List<PcModel> getAll(String name) {
		List<PcModel> list = new ArrayList<PcModel>();
		// isEmpty check trống và check dấu "cách"
		if(StringUtils.isEmpty(name)) {
			list = pcRespository.findByQuantity();
		}else {
			list = pcRespository.findByNameContaining(name);
		}
		return list;
	}
	public List<PcModel> getData(){
		return pcRespository.findAll();
		
	}


	public Page<PcModel> getPages(int currentPage, int pageSize) {
		Pageable page = (Pageable) PageRequest.of(currentPage, pageSize);
		return pcRespository.findAll(page);
	}
	public List<PcModel> getByType(PcType type) {
		return pcRespository.findByType(type);
	}
	public Page<PcModel> getByType(PcType type ,int currentPage, int pageSize ) {
		Pageable page = (Pageable) PageRequest.of(currentPage, pageSize);
		return pcRespository.findByType(type , page);
	}

	public PcModel getById(Long pcId) {
//	Optional<PcModel> opt = pcRespository.findById(id.toString());
		Optional<PcModel> otp = pcRespository.findById(pcId);
		if (otp.isPresent()) {
			return otp.get();
		}
		return null;
	}

	public int add(PcModel pc) {
		try {
			pcRespository.save(pc);
			return CommonConst.SUCCESS;
		} catch (Exception e) {
			return CommonConst.ERROR;
		}
	}

	public int update(PcModel pc) {
		try {
			pcRespository.save(pc);
			return CommonConst.SUCCESS;
		} catch (Exception e) {
			return CommonConst.ERROR;
		}
	}

	public int deleteById(Long deletepcId) {
		try {
			pcRespository.deleteById(deletepcId);
			return CommonConst.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return CommonConst.ERROR;
		}
	}

	public Long save(PcModel pc) {
		try {
			// Thêm thì đối tượng không có ID
			// PK không tồn tại thì thêm mới
			return pcRespository.save(pc).getPcId();
		} catch (Exception ex) {
			return (long) CommonConst.ERROR;
		}
	}
}

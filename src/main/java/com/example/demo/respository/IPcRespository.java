package com.example.demo.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PcModel;
import com.example.demo.model.PcType;
//@Repository – Dùng để đánh dấu các class thuộc tầng giao tiếp với database.
@Repository
public interface IPcRespository extends JpaRepository<PcModel, Long> {
	List<PcModel> findAll();

	Page<PcModel> findAll(org.springframework.data.domain.Pageable page);


	@Query(value = "SELECT b FROM PcModel b WHERE b.type =:type")
	List<PcModel> findByType(@Param("type") PcType type);
	
	Page<PcModel> findByType(@Param("type") PcType type, Pageable page);
// tạo ra 1 list các thông tin có trong PcModel  và truyền vào 1 biến name.
	List<PcModel> findByNameContaining(String name);
	@Query(value = "SELECT b FROM PcModel b WHERE b.quantity >0")
	List<PcModel> findByQuantity();
}

package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PcModel;
import com.example.demo.model.PcType;
import com.example.demo.service.PcService;

@RestController
//@RequestMapping("/computer")
@RequestMapping("/api/computer")
public class PcController {
@Autowired
private PcService pcService;
@PostMapping()
public int add(@RequestBody PcModel pc) {
	return pcService.add(pc);
}
//
//@GetMapping("/name/{name}")
//public List<PcModel> getByName(@PathVariable String name) {
//	return pcService.getByName(name);
//}
//@GetMapping("/get")
//	public List<PcModel> getById2(@RequestParam String name){
//		return pcService.getByName(name);
//	}
@GetMapping("/{pcId}")
public PcModel getPc(@PathVariable Long pcId) {
	return pcService.getById(pcId);
}
@PutMapping("/{pcId}")
public int update(@RequestBody PcModel pc) {
	return pcService.update(pc);
}
@DeleteMapping("/{pcId}")
public int delete(@PathVariable Long pcId ) {
	return pcService.deleteById(pcId);
}
@GetMapping("/type")
public List<PcModel> getName(@RequestParam PcType typename){
	return pcService.getByType(typename);
}
}

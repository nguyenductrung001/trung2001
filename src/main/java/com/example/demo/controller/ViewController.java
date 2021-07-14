package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.PcModel;
import com.example.demo.model.PcType;
import com.example.demo.respository.IPcRespository;
import com.example.demo.service.PcService;

@Controller
@RequestMapping("/pc")
public class ViewController {

	@Autowired
	private PcService pcService;
	@Autowired
	IPcRespository pcRespository;

// oke co hoi gi thi hoi di :D
	// còn cái phân trang a có ghép luôn vào đc k a
	// nó cũng tương tự thôi xử lý phân trang thì chỗ list kia thay bằng page. hàm
	// thì thêm Pageable page vào
	// thêm bên interface rồi thì chỉnh thêm service một tí chuyển cái List thành
	// Page thôi. chú ý là
	// import org.springframework.data.domain.Page;
	@GetMapping
	public String getListPage(Model model, @RequestParam(required = false) PcType selectedType) {
		List<PcModel> pcs = selectedType == null ? pcService.getData() : pcService.getByType(selectedType);
		model.addAttribute("pcs", pcs);
		model.addAttribute("pcTypes", PcType.values());
		model.addAttribute("selectedType", selectedType);
		return "pc/list";
	}

	@GetMapping("/home")
	//@RequestParam để lấy dữ liệu từ ng dùng gửi vào 
	public String homeUi(@RequestParam(value = "name", required = false) String name, Model model
			) {
	
//		List<PcModel> pcs = pcService.getAll("name");
		model.addAttribute("pcs", pcService.getAll(name));
	
		return "pc/home";

	}

	@GetMapping("/delete")
	public String deleteMulti(@RequestParam(required = false) Long[] ids) {
		for (Long pcId : ids) {
			try {
				pcService.deleteById(pcId);
			} catch (Exception e) {
			}
		}
		return "redirect:/pc/pagination";
	}

//xóa được rồi đó. Với cả a xem hộ e cái tích hợp lopcj theo type vào cái list với e làm nó toàn báo lỗi method
	@GetMapping("/type")
	// @RequestParam để lấy dữu liệu sau dấu ? 
	//required = false không bắt buộc phải có thông tin 
	public String getByType(Model model, @RequestParam(required = false) PcType typeName) {

		model.addAttribute("pcType", PcType.values());
		model.addAttribute("curType", typeName);
		List<PcModel> pcs;
		if (typeName == null) {
			pcs = pcService.getData();
		} else {
			pcs = pcService.getByType(typeName);
		}
		model.addAttribute("pcs", pcs);
		return "pc/type";
	}

	private final int PAGE_SIZE = 4;

	@GetMapping("/pagination")
	//@RequestParam lấy giá trị ng dùng nhập trên trình duyệt 
	public String getListPageWithPagination(Model model, @RequestParam(defaultValue = "1") int currentPage,  PcType selectedType) {
		List<PcModel> pcs = selectedType == null ? pcService.getData() : pcService.getByType(selectedType);
		Page<PcModel> page;
		// lấy cả 
		if(selectedType == null) {
			if (currentPage < 0)
				currentPage = 1;
		 page = pcService.getPages(currentPage - 1, PAGE_SIZE);
		 //getTotalPages: tất cả các trang
			if (page.getTotalPages() < currentPage)
				page = pcService.getPages(page.getTotalPages() - 1, PAGE_SIZE);
		}else {
			if (currentPage < 0)
				currentPage = 1;
		 page = pcService.getByType(selectedType, currentPage - 1, PAGE_SIZE);
			if (page.getTotalPages() < currentPage)
				page = pcService.getByType(selectedType,page.getTotalPages() - 1, PAGE_SIZE);
		}
		
		model.addAttribute("pcs", page);
		model.addAttribute("pcTypes", PcType.values());
		model.addAttribute("selectedType", selectedType);
		return "pc/listWithPagination";
	}

	@GetMapping("/{pcId}")
	public String getDetailPage(@PathVariable Long pcId, Model model) {
		PcModel pcModel = pcService.getById(pcId);
		model.addAttribute("pcModel", pcModel);
		model.addAttribute("pcTypes", PcType.values());
		return "pc/detail";
	}

	@GetMapping("/new")
	public String getNewPage(Model model) {
		model.addAttribute("pcModel", new PcModel());
		model.addAttribute("pcTypes", PcType.values());
		return "pc/new";
	}

	@PostMapping("/insert")
	//@ModelAttribute chuyển pcModel thành 1 object có đầy đủ thông tin 
	public String insert(@ModelAttribute("pcModel") PcModel pcModel, Model model) {
		Long pcId = (long) pcService.save(pcModel);
		// tạo sẵn 1 object đầy đủ thông tin 
		model.addAttribute("pcModel", pcModel);
		System.out.println("Insert");
		return "redirect:/pc/home" ;
	}

	@PutMapping("/update") 
	public String update(@ModelAttribute("pcModel") PcModel pcModel, Model model) {
		pcService.save(pcModel);
		model.addAttribute("pcModel", pcModel);
		System.out.println("Update");
		return "redirect:/pc/home";
	}

	@GetMapping("/delete/{pcId}")
	public String delete(@PathVariable Long pcId) {
		pcService.deleteById(pcId);
		return "redirect:/pc/home";
	}
	
}

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.OrderDetailId;
import com.example.demo.model.PcModel;
import com.example.demo.respository.IOrderDetailRepository;
import com.example.demo.respository.IOrderRespository;
import com.example.demo.respository.IPcRespository;

@Component
public class OrderService {

	@Autowired
	private IOrderRespository _orderRepo;
	@Autowired
	private IPcRespository _pcRepo;
	@Autowired
	private IOrderDetailRepository _orderDetailRepo;
	
	@Transactional
	public int newOrder(Order order) throws Exception {
		// Normal flow: Transaction
		// COMMIT
		// 1. Insert thông tin hóa đơn
		// => Save(Order)
		Long orderId = _orderRepo.save(order).getOrderId();
		order.setOrderId(orderId);
		// 2. Insert thông tin các mặt hàng trong hóa đơn
		// => Duyệt các mặt hàng trong hóa đơn (List)
		//	 	=> Thêm từng mặt hàng = Save(OrderDetail)
		for(OrderDetail detail : order.getOrderDetails()) {
			//		Trong quá trình thêm, cần phải kiểm tra
			//			mặt hàng có còn đủ hay không?
			//		Ví dụ: 
			//		Kho: 10 cuốn, order 6 -> còn 4 cuốn
			//		Kho: 10 cuốn sách, order 20 -> lỗi
			//	=> Lỗi thì hóa đơn sẽ không được thêm vào DB
			
			PcModel pc = _pcRepo.findById(
					detail.getPc().getPcId()).get();
			if (pc.getQuantity() < detail.getQuantity()) {
				throw new Exception("Order thất bại!");
			} else {
				// Trừ số lượng sản phẩm trong kho
				// = lượng hiện tại - lượng bán
				int pcLeft = pc.getQuantity() - detail.getQuantity();
				pc.setQuantity(pcLeft);
				_pcRepo.save(pc);
				
				// Lưu Detail
				OrderDetailId detailId = OrderDetailId.builder()
					.orderId(orderId)
					.pcId(pc.getPcId())
					.build();
				detail.setId(detailId);
//				detail.setPrice(pc.getPrice());
				detail.setPrice(pc.getPrice());
				_orderDetailRepo.save(detail);
			}
			
		}
		// => Thành công, không có lỗi -> Save
		// ...
		return 1;
	}

	public Iterable<Order> getAll() {
		return _orderRepo.findAll();
	}
}

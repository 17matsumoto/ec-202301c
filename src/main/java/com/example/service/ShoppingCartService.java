package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.User;
import com.example.form.ShoppingCartForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

import jakarta.servlet.http.HttpSession;

/**
 * カート関連サービス.
 * 
 * @author matsumotoyuyya
 *
 */

@Service
//@Transactional
public class ShoppingCartService {

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderitemRepository;

	@Autowired
	private HttpSession session;

	/**
	 * 注文情報インサート業務処理.
	 * 
	 * @param form   ショッピングカートフォーム
	 * @param userId ユーザーID
	 */
	public void insertCat(ShoppingCartForm form, Integer userId) {
		Order orderList = orderRepository.findByUserIdAndStatus(userId, 1);
		if (orderList == null) {
			// 注文
			Order order = new Order();
			order.setUserId(1);
			order.setStatus(0);
			order.setTotalPrice(0);
			Order orderInfo = orderRepository.insert(order);
			System.out.println(orderInfo);
			// 注文商品
			OrderItem orderItem = new OrderItem();
			BeanUtils.copyProperties(form, orderItem);
			System.out.println(orderInfo.getId());
			orderItem.setOrderId(orderInfo.getId());
			OrderItem orderItemInfo = orderitemRepository.insert(orderItem);

			// 注文トッピング
			OrderTopping orderTopping = new OrderTopping();
			System.out.println(orderItemInfo.getId());
				orderTopping.setToppingId(1);
				orderTopping.setOrderItemId(orderItemInfo.getId());
				orderToppingRepository.insert(orderTopping);
			
		}

	}

	/**
	 * 注文商品削除.
	 * 
	 * @param orderItemId 注文商品ID
	 */
	public void deleteCartContents(Integer orderItemId) {
		orderitemRepository.deleteByOrderId(orderItemId);
	}

	public OrderItem showCart() {
		User user = (User) session.getAttribute("User");
		Order order = orderRepository.load(3);
         System.out.println("111111");
		OrderItem orderItemList = orderitemRepository.findByOrderId(order.getId());
		return orderItemList;

	}
}

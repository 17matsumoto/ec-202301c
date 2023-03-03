package com.example.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.ShoppingCartForm;
import com.example.repository.ItemRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;
import com.example.repository.ToppingRepository;

import jakarta.servlet.http.HttpSession;

/**
 * カート関連サービス.
 * 
 * @author matsumotoyuyya
 *
 */

@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderitemRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ToppingRepository toppingpository;

	@Autowired
	private HttpSession session;

	/**
	 * 注文情報インサート業務処理.
	 * 
	 * @param form   ショッピングカートフォーム
	 * @param userId ユーザーID
	 */
	public void insertCat(ShoppingCartForm form, Integer userId) {
		Order order = orderRepository.findByUserIdAndStatus(userId, 0);
		System.out.println(order);
		Order orderObject = new Order();
		if (order == null) {
			// 注文 ユーザidは仮で１
			orderObject.setUserId(1);
			orderObject.setStatus(0);
			orderObject.setTotalPrice(0);
			orderRepository.insert(orderObject);
		}

		// 注文商品 ユーザidは仮で１
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(form, orderItem);

		if (order != null) {
			// ユーザーidがあれば合計金額を更新

			orderItem.setOrderId(order.getId());

		} else {
			orderItem.setOrderId(orderObject.getId());
		}
		
		OrderItem orderItemInfo = orderitemRepository.insert(orderItem);
		// 注文トッピング //nullの時は何もしない
		OrderTopping orderTopping = new OrderTopping();
		if (form.getToppingIdList() != null) {
			for (Integer t : form.getToppingIdList()) {
				orderTopping.setToppingId(t);
				orderTopping.setOrderItemId(orderItemInfo.getId());
				orderToppingRepository.insert(orderTopping);
			}
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

	/**
	 * カートに中身表示.
	 * 
	 * @param userId
	 * @return
	 */
	public Order showCart(Integer userId) {
		return orderRepository.findByUserIdAndStatus(userId, 0);

	}

	/**
	 * 削除.
	 * @param orderItemId
	 * @return
	 */
	public OrderItem confirmCart(Integer orderItemId) {
		OrderItem orderItem = orderitemRepository.findByOrderId(orderItemId);
		return orderItem;
	}
}
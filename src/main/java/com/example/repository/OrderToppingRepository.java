package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;

/**
 * 注文トッピング関連リポジトリー.
 * 
 * @author matsumotoyuyya
 *
 */
@Repository
public class OrderToppingRepository {

	private static final RowMapper<OrderTopping> OrderTopping_ROW_MAPPER = new BeanPropertyRowMapper<>(
			OrderTopping.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文トッピングを登録します.
	 * 
	 * @param employee 従業員情報
	 * @return インサートした従業員情報
	 */
	synchronized public OrderTopping  insert(OrderTopping orderTopping) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);

		// インサート処理
		String insertSql = "INSERT INTO order_toppings(topping_id,order_item_id) "
				+ " VALUES(:toppingId,:orderItemId)";
		template.update(insertSql, param);
return orderTopping;
	}

}
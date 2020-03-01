package com.kgregorczyk.library.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name="tb_commodity")
public class Commodity implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 9139731878706649050L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commodityId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="parent_id",referencedColumnName = "commodityId")
	private Commodity parentId;

	private String commodityName;

	private String commodityDesc;

	//商品售价
	@Column(precision=10, scale=2)
	private BigDecimal commodityPrice;

	//成本
	@Column(precision=10, scale=2)
	private BigDecimal costPrice;

	private Integer commodityStock;

	private String commodityIcon;

	//private List<String> comodityImages;

	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="merchant_account",referencedColumnName = "account")
	private User merchantId;*/

	@ColumnDefault(value = "false")
	private boolean commodity;

	//商品折扣率
	@ColumnDefault(value="1.00")
	private BigDecimal commodityDiscountRate;
	
	//商品图片
	private String commodityImage;


	//库存，加单位
}

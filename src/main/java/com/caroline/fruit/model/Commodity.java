package com.caroline.fruit.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name="t_commodity")
public class Commodity implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 9139731878706649050L;

	@Id
	@Column(length = 38)
	private String commodityId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="parent_id",referencedColumnName = "commodityId")
	private Commodity parentId;

	private String commodityName;

	private String commodityDesc;

	//商品规格
	private String commoditySpecifications;

	//产地/分发地
	private String originDispatch;

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
	private String commodityUrl;


	//库存，加单位

	//快递公司
	private String expressCompany;

	//说明
	private String comments;

	//邮费
    @Column(precision=10, scale=2)
    private BigDecimal freight;
}

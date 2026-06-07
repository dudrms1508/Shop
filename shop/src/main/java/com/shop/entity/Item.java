package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

import com.shop.dto.ItemFormDto;

import com.shop.exception.OutOfStockException;

@Entity
@Table(name="item")
@ToString
@Getter
@Setter
public class Item extends BaseEntity{

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    // 상품코드

    @Column(nullable = false, length = 50)
    private String itemName;    //상품명

    @Column(name="price", nullable = false)
    private int price;  //가격

    @Column(nullable = false)
    private int stockNumber;    // 재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  // 상품 판매 상태

    private LocalDateTime regTime;  //등록 시간

    private LocalDateTime updateTime;   // 수정 시간

    public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

//  상품의 재고를 감소시키는 로직을 담당하는 클래스
//  비즈니스 로직을 메소드로 작성하면 코드의 재사용과 데이터의 변경 포인트를 한군데로 모을 수 있다는 장점이 있다.
    public void removeStock(int stockNumber){

        int restStock = this.stockNumber - stockNumber;
//        상품의 재고 수량에서 주문 후 남은 재고 수량을 구합니다.
        if(restStock < 0){
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량 : " + this.stockNumber + ")");
//            상품의 재고가 주문 수량보다 작을 경우재고 부족 예외를 발생시킨다.
        }
        this.stockNumber = restStock;
//        주문 후 남은 재고 수량을 현재 재고 값으로 할당
    }

//    상품의 재고를 더해주기 위한 addStock 메소드
    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }
}

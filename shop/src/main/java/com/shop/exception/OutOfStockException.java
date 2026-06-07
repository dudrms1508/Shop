package com.shop.exception;

// 상품의 주문수량보다 재고의 수가 적을 때 발생시킬 exception을 정의하는 java클래스
public class OutOfStockException extends RuntimeException{

    public OutOfStockException(String message) {
        super(message);
    }
}

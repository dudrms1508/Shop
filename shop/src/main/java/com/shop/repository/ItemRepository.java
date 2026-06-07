package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

    List<Item> findByItemName(String itemName);

    //or조건 처리
    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    //lessthan조건 처리
    List<Item> findByPriceLessThan(Integer price);

    //Order by로 정렬
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    //@Query를 이용한 검색 처리
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc ")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
    //@Query 어노테이션 안에 JPQL로 작성한 쿼리문을 넣고, from뒤에는 엔티티 클래스로 작성한 item을 지정해주며, item으로 부터 데이터를 select하겠다는 것을 의미
    //파라미터에 @Param 어노테이션을 사용하여 파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정한다.
    //현재는 itemDetail변수를 "like % %" 사이에 "itemDetail"로 값이 들어가도록 작성

    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc",nativeQuery = true)
    List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);
}

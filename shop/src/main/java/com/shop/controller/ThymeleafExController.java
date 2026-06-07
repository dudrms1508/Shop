package com.shop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.shop.dto.ItemDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafExController {

    @GetMapping(value = "/ex01")
    public String thymeleafEx01(Model model) {
        model.addAttribute("data","타임리프 예제입니다.");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping(value = "/ex02")
    public String thymeleafEx02(Model model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemDetail("상품 상세 설명");
        itemDto.setItemName("테스트 상품1");
        itemDto.setPrice(10000);
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto",itemDto);
        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping(value = "/ex03")
    public String thymeleafEx03(Model model) {

        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+ i);
            itemDto.setItemName("테스트 상품"+ i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList",itemDtoList);
        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping(value = "/ex04")
    public String thymeleafEx04(Model model) {

        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+ i);
            itemDto.setItemName("테스트 상품"+ i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);

        }
        model.addAttribute("itemDtoList",itemDtoList);
        return "thymeleafEx/thymeleafEx04";

    }

    @GetMapping(value = "/ex05")
    public String thymeleafEx05(Model model) {
        return "thymeleafEx/thymeleafEx05";
    }

    @GetMapping(value = "/ex06")
    public String thymeleafEx06(String param1,String param2, Model model) {
//        전달했던 매개 변수와 같은 이름의 String 변수 param1, param2를 파라미터로 설정하면 자동으로 데이터가
//        바인딩됩니다. 매개변수를 model에 담아서 View로 전달

        model.addAttribute("param1",param1);
        model.addAttribute("param2",param2);
        return "thymeleafEx/thymeleafEx06";

    }

    @GetMapping(value = "/ex07")
    public String thymeleafEx07() {
        return "thymeleafEx/thymeleafEx07";
    }

}

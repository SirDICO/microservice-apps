package com.dico.Orderservice.Controller;

import com.dico.Orderservice.Service.OrderSevice;
import com.dico.Orderservice.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderSevice orderSevice;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  String placeOrder(@RequestBody OrderRequest orderRequest){
        orderSevice.placeOrder(orderRequest);
        return "order successfully";
    }
}

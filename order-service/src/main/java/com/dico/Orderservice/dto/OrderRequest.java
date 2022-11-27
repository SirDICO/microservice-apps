package com.dico.Orderservice.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderRequest {

    private List<OrderLineItemsDto> orderLineItemsDtoList;
}

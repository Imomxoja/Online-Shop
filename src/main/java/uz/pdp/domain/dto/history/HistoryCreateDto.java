package uz.pdp.domain.dto.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryCreateDto {
   UUID userId;
   UUID productId;
   String productName;
   Integer amount;
   Double totalPrice;
}

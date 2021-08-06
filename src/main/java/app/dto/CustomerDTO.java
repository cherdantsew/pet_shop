package app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private Integer id;
    private String name;
    private String isBlocked;
}

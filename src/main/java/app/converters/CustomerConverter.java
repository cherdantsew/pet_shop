package app.converters;

import app.dto.CustomerDTO;
import app.entities.Customer;

    public class CustomerConverter {

    public CustomerDTO toDto(Customer customer){
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }

}

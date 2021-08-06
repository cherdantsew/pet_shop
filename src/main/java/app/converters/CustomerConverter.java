package app.converters;

import app.dto.CustomerDTO;
import app.entities.Customer;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerConverter {
    public CustomerDTO toDto(Customer customer){
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .isBlocked(customer.getIsBlocked())
                .type(customer.getType())
                .build();
    }
    public List<CustomerDTO> toCustomerDTOList(List<Customer> customerList){
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(customerList)){
            for (Customer customer: customerList){
                customerDTOList.add(new CustomerConverter().toDto(customer));
            }
        }
        return customerDTOList;
    }
}

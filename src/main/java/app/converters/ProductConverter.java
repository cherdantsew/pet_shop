package app.converters;

import app.dto.ProductDTO;
import app.entities.Product;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductConverter {
    private ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getProductId())
                .name(product.getProductName())
                .price(product.getProductPrice())
                .description(product.getProductDescription())
                .build();
    }
    public List<ProductDTO> toProductDTOList(List<Product> productList){
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(productList)){
            for(Product product: productList){
                productDTOList.add(toDTO(product));
            }
        }
        return productDTOList;
    }
}

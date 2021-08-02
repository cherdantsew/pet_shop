package app.converters;

import app.dto.ProductDTO;
import app.entities.Product;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductConverter {
    private ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productDescription(product.getProductDescription())
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

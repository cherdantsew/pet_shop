package app.converters;

import app.dto.ProductCategoryDTO;
import app.dto.ProductDTO;
import app.entities.Product;
import app.entities.ProductCategory;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryConverter {
    private ProductCategoryDTO toDTO (ProductCategory productCategory){
        return ProductCategoryDTO.builder()
                .categoryName(productCategory.getCategoryName())
                .build();
    }
    public List<ProductCategoryDTO> toProductCategoryDTOList (List<ProductCategory> productCategoryList){
        List<ProductCategoryDTO> productCategoryDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(productCategoryList)){
            for(ProductCategory productCategory: productCategoryList){
                productCategoryDTOList.add(toDTO(productCategory));
            }
        }
        return productCategoryDTOList;
    }
}

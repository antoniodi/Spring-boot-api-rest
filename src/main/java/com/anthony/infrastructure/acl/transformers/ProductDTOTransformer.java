package com.anthony.infrastructure.acl.transformers;

import com.anthony.infrastructure.acl.http.dtos.ProductDTO;
import com.anthony.domain.entities.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProductDTOTransformer {

    private final ModelMapper modelMapper;

    public ProductDTOTransformer( ModelMapper modelMapper ) {
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        modelMapper.addMappings( new PropertyMap<Product, ProductDTO>() {

            @Override
            protected void configure() {
                map().setCategoryName( source.getCategory().getName() );
            }
        });
    }

    public ProductDTO productToProductDto( Product product ) {
        return modelMapper.map( product, ProductDTO.class );

    }

}

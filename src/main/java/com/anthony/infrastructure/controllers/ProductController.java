package com.anthony.infrastructure.controllers;

import com.anthony.infrastructure.acl.http.dtos.CreateProductDTO;
import com.anthony.infrastructure.acl.http.dtos.ProductDTO;
import com.anthony.infrastructure.acl.transformers.ProductDTOTransformer;
import com.anthony.domain.entities.Category;
import com.anthony.domain.entities.Product;
import com.anthony.infrastructure.persistance.repositories.CategoryRepository;
import com.anthony.infrastructure.persistance.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private ProductRepository productRepository;
    private ProductDTOTransformer productDTOTransformer;
    private CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, ProductDTOTransformer productDTOTransformer,
                             CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productDTOTransformer = productDTOTransformer;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Get all products
     *
     * @return all products
     */
    @CrossOrigin( origins = "http://localhost:9080" )
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAll() {
        final List<Product> products = productRepository.findAll();

        if (products.isEmpty()) return ResponseEntity.notFound().build();
        else {
            List<ProductDTO> productsDTO = products.stream().map(productDTOTransformer::productToProductDto).collect(Collectors.toList());
            return ResponseEntity.ok(productsDTO);
        }
    }

    /**
     * Get product by ID
     *
     * @param productId id of the product
     * @return return a product otherwise nul
     */
    @GetMapping("/product/{id}")
    public Product getById(@PathVariable Long productId) {
        final Optional<Product> product = productRepository.findById(productId);

        return product.orElseThrow(() -> new Product.ProductNotFoundException(productId));
    }

    /**
     * Insertamos un nuevo producto
     *
     * @param productDTO
     * @return producto insertado
     */
    @PostMapping("/product")
    public ResponseEntity<Product> newProduct(@RequestBody CreateProductDTO productDTO) {
        return categoryRepository.findById(productDTO.getCategoryId()).map(category -> {
            final Product product = new Product();
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setCategory(category);
            Product createdProduct = productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        }).orElseThrow(() -> new Category.CategoryNotFoundException(productDTO.getCategoryId()));
    }

    /**
     * @param edit
     * @param id
     * @return
     */
    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody Product edit, @PathVariable Long id) {
        final Optional<Product> product = productRepository.findById(id);

        return product.map(foundProduct -> {
            final Product updateProduct = new Product(id, edit.getName(), edit.getPrice(), foundProduct.getCategory());
            return productRepository.save(updateProduct);
        }).orElseThrow(() -> new Product.ProductNotFoundException(id));
    }

    /**
     * Borra un producto del catálogo en base a su id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id).map(product -> {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new Product.ProductNotFoundException(id));
    }
}

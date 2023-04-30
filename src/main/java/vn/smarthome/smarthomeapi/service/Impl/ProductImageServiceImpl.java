package vn.smarthome.smarthomeapi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.smarthome.smarthomeapi.entity.ProductImage;
import vn.smarthome.smarthomeapi.repository.ProductImageRepository;
import vn.smarthome.smarthomeapi.service.IProductImageService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements IProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    public ProductImageServiceImpl(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Override
    public List<ProductImage> findAll() {
        return productImageRepository.findAll();
    }

    @Override
    public <S extends ProductImage> S save(S entity) {
        return productImageRepository.save(entity);
    }

    @Override
    public Optional<ProductImage> findById(Integer integer) {
        return productImageRepository.findById(integer);
    }
}

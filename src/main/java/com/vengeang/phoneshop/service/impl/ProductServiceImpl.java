package com.vengeang.phoneshop.service.impl;

import com.vengeang.phoneshop.dto.PriceDTO;
import com.vengeang.phoneshop.dto.ProductImportDTO;
import com.vengeang.phoneshop.entities.Product;
import com.vengeang.phoneshop.entities.ProductImportHistory;
import com.vengeang.phoneshop.exception.ApiException;
import com.vengeang.phoneshop.mapper.ProductMapper;
import com.vengeang.phoneshop.repositories.ProductImportHistoryRepository;
import com.vengeang.phoneshop.repositories.ProductRepository;
import com.vengeang.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;
    @Override
    public Product save(Product product) {
        String name="%s %s".formatted(product.getModel().getName(),product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Product with id : %d not found ".formatted(id)));
    }

    @Override
    public void importProduct(ProductImportDTO importDTO) {
//       update available  product unit
        Product product = getById(importDTO.getProductId());
        Integer availableUnit=product.getAvailableUnit()!=null? product.getAvailableUnit()+ importDTO.getImportUnit() : importDTO.getImportUnit();
        product.setAvailableUnit(availableUnit);
        productRepository.save(product);
//        save product import history
        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(importDTO, product);
        productImportHistoryRepository.save(productImportHistory);
    }

    @Override
    public void setSalePrice(Long productId, PriceDTO priceDTO) {
        Product product = getById(productId);
        BigDecimal price = priceDTO.getPrice();
        product.setSalePrice(price);
        productRepository.save(product);
    }

    @Override
    public Map<Integer,String> uploadProduct(MultipartFile file) {
        Map<Integer,String> map=new HashMap<>();
        try {
            Workbook workbook=new XSSFWorkbook(file.getInputStream());
            Sheet products = workbook.getSheet("products");
            Iterator<Row> rowIterator = products.iterator();
            rowIterator.next();// @TODO improve checking error
            while (rowIterator.hasNext()) {
                Integer rowNumber = 0;
                try {
                    Row row = rowIterator.next();
                    int cellIndex=0;
                    Cell cellNo=row.getCell(cellIndex++);
                    rowNumber = (int) cellNo.getNumericCellValue();
                    Cell cellModelId = row.getCell(cellIndex++);
                    Long modelId = (long) cellModelId.getNumericCellValue();
                    Cell cellColorId = row.getCell(cellIndex++);
                    Long colorId = (long) cellColorId.getNumericCellValue();
                    Cell cellImportPrice = row.getCell(cellIndex++);
                    Double importPrice = cellImportPrice.getNumericCellValue();
                    Cell cellImportUint = row.getCell(cellIndex++);
                    Integer importUnit = (int) cellImportUint.getNumericCellValue();
                    if(importUnit<1){
                        throw new ApiException(HttpStatus.BAD_REQUEST,"Import Unit must be greater than 0!!");
                    }
                    Cell cellImportDate = row.getCell(cellIndex++);
                    LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();


                    Product product = getByModelIdAndColorId(modelId, colorId);
                    Integer availableUnit = product.getAvailableUnit() != null ? product.getAvailableUnit() + importUnit : importUnit;
                    product.setAvailableUnit(availableUnit);
                    productRepository.save(product);
                    ProductImportDTO productImportDTO = new ProductImportDTO();
                    productImportDTO.setDateImport(importDate);
                    productImportDTO.setImportPrice(BigDecimal.valueOf(importPrice));
                    productImportDTO.setImportUnit(importUnit);
                    ProductImportHistory importHistory = productMapper.toProductImportHistory(productImportDTO, product);
                    productImportHistoryRepository.save(importHistory);

                } catch (Exception e) {
                  map.put(rowNumber,e.getMessage());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Product getByModelIdAndColorId(Long modelId, Long colorId) {
        String msg="product with model id: %d and color id:%d not found";
        Product product = productRepository.findByModelIdAndColorId(modelId, colorId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, String.format(msg, modelId, colorId))
        );
        return product;
    }
}

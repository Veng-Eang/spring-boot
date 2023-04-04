package com.vengeang.phoneshop.controller;

import com.vengeang.phoneshop.dto.PriceDTO;
import com.vengeang.phoneshop.dto.ProductDTO;
import com.vengeang.phoneshop.dto.ProductImportDTO;
import com.vengeang.phoneshop.entities.Product;
import com.vengeang.phoneshop.mapper.ProductMapper;
import com.vengeang.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ProductDTO productDTO){
        Product product = productService.save(productMapper.toProduct(productDTO));
        return ResponseEntity.ok(product);
    }
    @PostMapping("import")
    public ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDTO importDTO){
        productService.importProduct(importDTO);
        return ResponseEntity.ok("Import success");
    }
    @PostMapping("{id}/salePrice")
    public ResponseEntity<?> setSalePrice(@PathVariable("id") Long productId, @RequestBody @Valid PriceDTO priceDTO){
        productService.setSalePrice(productId,priceDTO);
        return ResponseEntity.ok("Set price success.");
    }
    @PostMapping("uplaodProduct")
    public ResponseEntity<?> uploadProduct(@RequestParam("file")MultipartFile file) throws IOException {
        Workbook workbook= new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet("products");
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();//@TODO improve checking error
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Cell cellModelId = row.getCell(0);
            Long modelId =(long)cellModelId.getNumericCellValue();
        }
        return null;
    }

}


















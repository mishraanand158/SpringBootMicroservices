package com.anand.ecommerce.config;

import com.anand.ecommerce.entity.Product;
import com.anand.ecommerce.entity.ProductCategory;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(@NotNull RepositoryRestConfiguration config) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        // disable http method for Product class Methods are put , post , delete

            config.getExposureConfiguration()
                    .forDomainType(Product.class)
                    .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                    .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));


        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));


    }
}

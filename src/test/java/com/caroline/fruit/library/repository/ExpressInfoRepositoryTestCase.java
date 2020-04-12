package com.caroline.fruit.library.repository;

import com.caroline.fruit.library.LibraryApplicationTests;
import com.caroline.fruit.repository.ExpressInfoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExpressInfoRepositoryTestCase extends LibraryApplicationTests {

    @Autowired
    private ExpressInfoRepository expressInfoRepository;

    @Test
    public void delete(){
        expressInfoRepository.deleteByOrderExpressId("8e8c61fc-0591-4744-9205-39f0bac23d4f");
    }
}

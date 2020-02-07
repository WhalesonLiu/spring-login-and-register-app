package com.kgregorczyk.library.repository;

import com.kgregorczyk.library.model.NCoV;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NCoVRepository extends JpaRepository<NCoV, Long> {
}

package com.anup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anup.entity.GenericTemp;

public interface GenericTempRepository extends JpaRepository<GenericTemp, Long> {

}

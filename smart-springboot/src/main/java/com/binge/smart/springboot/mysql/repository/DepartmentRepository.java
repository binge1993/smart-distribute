package com.binge.smart.springboot.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binge.smart.springboot.mysql.entity.Department;

/**
 * @author binge
 * @datetime 2017年10月14日
 * @version
 * @encoding UTF8
 * @Description
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

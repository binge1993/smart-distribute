package com.binge.smart.springboot.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binge.smart.springboot.mysql.entity.Role;

/**
 * @author binge
 * @datetime 2017年10月14日
 * @version
 * @encoding UTF8
 * @Description
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
}

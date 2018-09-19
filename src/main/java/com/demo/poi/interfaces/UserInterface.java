package com.demo.poi.interfaces;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.demo.poi.model.UserEntity;

public interface UserInterface extends JpaRepository<UserEntity, Long>,JpaSpecificationExecutor<UserEntity>,Serializable {

}

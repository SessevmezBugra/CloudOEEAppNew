package com.oee.repository;

import com.oee.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

    @Query("select u.id, u.username, u.firstName, u.lastName from UserEntity u inner join u.responsibleAreas ra where ra.id  in (:areaIds)")
    List<UserEntity> findByResponsibleAreasAreaIdIn(@Param("areaIds")List<Long> areaIds);
}

package com.oee.repository;

import com.oee.dto.UserEntityOnly;
import com.oee.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

//    @Query("select u from UserEntity u inner join u.responsibleAreas ra where ra.id  in (:areaIds)")
//    List<UserEntityOnly> findByResponsibleAreasAreaIdIn(@Param("areaIds")List<Long> areaIds);
    @Query(value="SELECT DISTINCT u.ID as id, u.USERNAME as username, u.FIRST_NAME as firstName, u.LAST_NAME as lastName  FROM USER_ENTITY u INNER JOIN RESPONSIBLE_AREA ra ON u.ID = ra.USER_ID WHERE ra.AREA_ID IN (?1) AND ra.AREA_TYPE = ?2", nativeQuery=true)
    List<UserEntityOnly> findByResponsibleAreasAreaIdIn(Iterable<Long> areaIds, String areaType);
}

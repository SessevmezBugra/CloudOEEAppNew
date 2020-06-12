package com.oee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.UserInvitationInfo;


@Repository
public interface UserInvitationInfoRepository extends JpaRepository<UserInvitationInfo, Long>{

}

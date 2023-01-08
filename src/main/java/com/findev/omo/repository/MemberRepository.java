package com.findev.omo.repository;

import com.findev.omo.model.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Member findByMemberId(String memeberId);
}

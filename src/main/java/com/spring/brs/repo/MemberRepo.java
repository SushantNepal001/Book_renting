package com.spring.brs.repo;

import com.spring.brs.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Integer> {

}

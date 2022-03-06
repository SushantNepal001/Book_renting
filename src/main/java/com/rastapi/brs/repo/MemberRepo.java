package com.rastapi.brs.repo;

import com.rastapi.brs.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Integer> {

}

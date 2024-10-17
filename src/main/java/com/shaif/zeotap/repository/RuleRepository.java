package com.shaif.zeotap.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaif.zeotap.model.Node;

@Repository
public interface RuleRepository extends JpaRepository<Node, Long> {
}


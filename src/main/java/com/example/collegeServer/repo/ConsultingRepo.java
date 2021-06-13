package com.example.collegeServer.repo;

import com.example.collegeServer.model.buisness.Consulting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultingRepo extends JpaRepository<Consulting, Long> {
}

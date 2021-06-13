package com.example.collegeServer.repo;

import com.example.collegeServer.model.buisness.MasterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterClassRepo extends JpaRepository<MasterClass, Long> {
}

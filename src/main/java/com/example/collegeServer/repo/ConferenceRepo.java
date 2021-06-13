package com.example.collegeServer.repo;

import com.example.collegeServer.model.buisness.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepo extends JpaRepository<Conference, Long> {
}

package com.example.collegeServer.repo;

import com.example.collegeServer.model.buisness.Consulting;
import com.example.collegeServer.model.buisness.JoinConsultingUser;
import com.example.collegeServer.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinConsultingUserRepo extends JpaRepository<JoinConsultingUser, Long> {
    void deleteByUserAndConsulting(User user, Consulting consulting);
    boolean existsByUserAndConsulting(User user, Consulting consulting);
}

package com.example.collegeServer.repo;

import com.example.collegeServer.model.buisness.JoinMasterClassUser;
import com.example.collegeServer.model.buisness.MasterClass;
import com.example.collegeServer.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinMasterClassUserRepo extends JpaRepository<JoinMasterClassUser, Long> {
    void deleteByUserAndMasterClass(User user, MasterClass masterClass);
    boolean existsByUserAndMasterClass(User user, MasterClass masterClass);
}

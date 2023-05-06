package com.innup.startupinvest.repositories;

import com.innup.startupinvest.models.StartUp;
import com.innup.startupinvest.models.StartUpComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StartUpCommentRepository extends JpaRepository<StartUpComment, Long> {

    List<StartUpComment> findByStartUp(StartUp startUp);
}

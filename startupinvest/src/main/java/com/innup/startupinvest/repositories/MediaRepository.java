package com.innup.startupinvest.repositories;

import com.innup.startupinvest.models.Media;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{
    @Transactional
    Media findMediaById(Long id);
}

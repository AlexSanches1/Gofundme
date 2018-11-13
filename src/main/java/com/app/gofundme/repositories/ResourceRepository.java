package com.app.gofundme.repositories;

import com.app.gofundme.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findByNameInBase64(String nameInBase64);
}

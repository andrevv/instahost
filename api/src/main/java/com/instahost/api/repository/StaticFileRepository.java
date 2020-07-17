package com.instahost.api.repository;

import com.instahost.api.domain.StaticFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaticFileRepository extends CrudRepository<StaticFile, String> { }

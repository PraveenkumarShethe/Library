package com.praveen.shethe.repository;

import com.praveen.shethe.model.DigitalFileInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalFileInfoRepository extends CrudRepository<DigitalFileInfo, Long> {
}

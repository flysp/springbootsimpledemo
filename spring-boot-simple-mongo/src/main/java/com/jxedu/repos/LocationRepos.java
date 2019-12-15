package com.jxedu.repos;

import com.jxedu.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepos extends MongoRepository<Location,String> {
}

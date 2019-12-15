package com.jxedu.service;

import com.jxedu.entity.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LocationBiz {

     private final MongoTemplate mongoTemplate;

    /**
     * 按圆形查找
     * @param point
     * @param maxDistance
     * @return
     */
     public List<Location> findCircleNear(Point point, double maxDistance){
         Query query = new Query(Criteria.where("postion").near(point).maxDistance(maxDistance));
         return mongoTemplate.find(query,Location.class);
     }

    /**
     * 按方形查找
     * @param lowerLeft
     * @param upperRigth
     * @return
     */
     public List<Location> findBoxWithin(Point lowerLeft,Point upperRigth){
         Query query = new Query(Criteria.where("postion").within(new Box(lowerLeft, upperRigth)));
         return mongoTemplate.find(query,Location.class);
     }
}

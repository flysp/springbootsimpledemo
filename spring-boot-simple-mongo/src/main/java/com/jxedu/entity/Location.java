package com.jxedu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "location")
public class Location {

     @Id
     private String id;
    /**
     * 地点名称
     */
     private String name;
    /**
     *位置信息
     */
    @GeoSpatialIndexed
     private Double[] postion;

      public Location(String name,double  latitude ,double  longitude){
          this.name = name;
          this.postion = new Double[]{longitude,latitude};
      }
}

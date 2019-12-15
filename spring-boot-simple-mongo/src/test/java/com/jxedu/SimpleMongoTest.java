package com.jxedu;

import com.jxedu.entity.Location;
import com.jxedu.repos.LocationRepos;
import com.jxedu.service.LocationBiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SimpleMongoMain.class})
public class SimpleMongoTest {

     @Autowired
     private MongoTemplate mongoTemplate;

     @Autowired
     private LocationBiz locationBiz;
     @Test
     public void testinit(){
         // 等同db.location.ensureIndex( {position: "2d"} )
         mongoTemplate.indexOps(Location.class).ensureIndex(new GeospatialIndex("position"));
         // 初始化数据
         mongoTemplate.save(new Location("天安门", 116.4041602659, 39.9096215780));
         mongoTemplate.save(new Location("东单", 116.4244857287, 39.9144951360));
         mongoTemplate.save(new Location("王府井", 116.4177807251, 39.9175129885));
         mongoTemplate.save(new Location("西单", 116.3834863095, 39.9133467579));
         mongoTemplate.save(new Location("复兴门", 116.3631701881, 39.9129554253));
         mongoTemplate.save(new Location("复兴门", 116.3631701881, 39.9129554253));
         mongoTemplate.save(new Location("西四", 116.3799838526, 39.9299098531));
         mongoTemplate.save(new Location("菜市口", 116.3809950293, 39.8952009239));
         mongoTemplate.save(new Location("东四", 116.4239387439, 39.9306126797));
     }

     @Test
     public void testinit2(){
         for (int x = 100; x < 131; x++) {
             for (int y = 30; y < 61; y++) {
                 Double loca[] = new Double[]{Double.valueOf(x), Double.valueOf(y)};
                 Location chargePoi = new Location();
                 chargePoi.setName("vini" + Arrays.toString(loca));
                 chargePoi.setPostion(loca);
                 mongoTemplate.insert(chargePoi);
             }
         }
     }

    /**
     * 这里面116.4041602659, 39.9096215780是天安门的经纬度,3/111是3公里以内的数据。111是每一个经度代表的公里数
     */
    @Test
     public void findCircleNearTest(){
         List<Location> locations = locationBiz.findCircleNear(new Point(116.4041602659, 39.9096215780), 3 / 111);
         locations.forEach(location -> {
             System.out.println(location.toString());
         });
     }

    /**
     * 查找左下角是菜市口，右上角是东四，这个方形区域内的所有点
     */
    @Test
    public void findBoxNearTest() {
        Point point1 = new Point(116.3809950293, 39.8952009239);
        Point point2 = new Point(116.4239387439, 39.9306126797);
        List<Location> locations = locationBiz.findBoxWithin(point1, point2);
        locations.forEach(location -> {
            System.out.println(location.toString());
        });
    }

    /**
     * circle
     */
    @Test
    public void test2() {
        Circle circle = new Circle(30, 20, 20);
        List<Location> find = mongoTemplate.find(new Query(Criteria.where("position").within(circle)), Location.class);
        System.out.println(find);
        System.out.println(find.size());
    }

    /**
     * spherical
     */
    @Test
    public void test3() {
        Circle circle = new Circle(30,20, 20);
        List<Location> find = mongoTemplate.find(new Query(Criteria.where("position").withinSphere(circle)), Location.class);
        System.out.println(find.size());
        System.out.println(find);
    }

    /**
     * box
     */
    @Test
    public void test4() {
        Box box = new Box(new Point(10, 11), new Point(10, 20));
        List<Location> find =
                mongoTemplate.find(new Query(Criteria.where("position").within(box)), Location.class);
        System.out.println(find.size());
        System.out.println(find);
    }

    /**
     * near
     */
    @Test
    public void test5() {
        Point point = new Point(12, 12);
        List<Location> venues =
                mongoTemplate.find(new Query(Criteria.where("position").near(point).maxDistance(20)), Location.class);
        System.out.println(venues.size());
        System.out.println(venues);
    }

    /**
     * nearSphere
     */
    @Test
    public void test6() {
        Point point = new Point(12, 12);
        List<Location> venues =
                mongoTemplate.find(new Query(Criteria.where("position").nearSphere(point).maxDistance(20)), Location.class);
        System.out.println(venues.size());
        System.out.println(venues);
    }

    @Test
    public void test7() {
        Point location = new Point(12, 12);
        NearQuery query = NearQuery.near(location).maxDistance(new Distance(100000, Metrics.KILOMETERS));
        GeoResults<Location> result = mongoTemplate.geoNear(query, Location.class);
        System.out.println(result);
    }
}

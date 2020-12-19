package laba.repository;

import laba.entity.Point;
import laba.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> getAllByUser(User user);
}

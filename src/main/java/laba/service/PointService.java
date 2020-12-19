package laba.service;


import laba.entity.Point;
import laba.entity.User;
import laba.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class PointService {

    private final PointRepository pointRepository;

    @Autowired
    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public Point savePoint(Point point) {
        return pointRepository.save(point);
    }
    @Transactional
    public List<Point> getPointsByUsername(User user) {
        return pointRepository.getAllByUser(user);
    }
}

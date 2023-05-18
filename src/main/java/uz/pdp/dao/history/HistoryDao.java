package uz.pdp.dao.history;

import org.springframework.stereotype.Repository;
import uz.pdp.dao.BaseDao;
import uz.pdp.domain.entity.history.History;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryDao extends BaseDao<History, UUID> {
    List<History> getUserHistories(UUID id);
}

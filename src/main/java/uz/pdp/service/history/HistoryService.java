package uz.pdp.service.history;

import org.springframework.stereotype.Service;
import uz.pdp.domain.dto.history.HistoryCreateDto;
import uz.pdp.domain.dto.history.HistoryReadDto;
import uz.pdp.service.BaseService;

import java.util.List;
import java.util.UUID;

@Service
public interface HistoryService extends BaseService<HistoryCreateDto, HistoryReadDto> {

    List<HistoryReadDto> getUserHistories(UUID id);
}

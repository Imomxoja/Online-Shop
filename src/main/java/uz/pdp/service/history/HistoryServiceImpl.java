package uz.pdp.service.history;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.dao.history.HistoryDao;
import uz.pdp.dao.product.ProductDao;
import uz.pdp.dao.user.UserDao;
import uz.pdp.domain.dto.history.HistoryCreateDto;
import uz.pdp.domain.dto.history.HistoryReadDto;
import uz.pdp.domain.entity.history.History;
import uz.pdp.domain.entity.product.Product;
import uz.pdp.domain.entity.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryDao dao;
    private final ProductDao productDao;
    private final UserDao userDao;
    private final ModelMapper mapper;
    @Override
    public HistoryReadDto create(HistoryCreateDto historyCreateDto) {
        Product product = productDao.findById(historyCreateDto.getProductId()).get();
        User user = userDao.findById(historyCreateDto.getUserId()).get();
        History map = mapper.map(historyCreateDto, History.class);
        map.setUser(user);
        map.setProduct(product);
        return mapper.map(dao.create(map), HistoryReadDto.class);
    }

    @Override
    public HistoryReadDto getById(UUID id) {
        return mapper.map(dao.findById(id).get(), HistoryReadDto.class);
    }

    @Override
    public int delete(UUID id) {
       return dao.deleteById(id);
    }

    @Override
    public List<HistoryReadDto> getAll() {
        List<History> all = dao.getAll();
        List<HistoryReadDto> historyReadDtos = new ArrayList<>();

        for (History history : all) {
            historyReadDtos.add(mapper.map(history, HistoryReadDto.class));
        }
        return historyReadDtos;
    }

    @Override
    public List<HistoryReadDto> getUserHistories(UUID id) {
        List<History> userHistories = dao.getUserHistories(id);
        List<HistoryReadDto> histories = new ArrayList<>();

        for (History userHistory : userHistories) {
            histories.add(mapper.map(userHistory, HistoryReadDto.class));
        }
        return histories;
    }
}

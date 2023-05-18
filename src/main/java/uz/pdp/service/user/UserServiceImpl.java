package uz.pdp.service.user;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.dao.user.UserDao;
import uz.pdp.domain.dto.user.UserCreateDto;
import uz.pdp.domain.dto.user.UserReadDto;
import uz.pdp.domain.entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao dao;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public UserReadDto create(UserCreateDto userCreateDto) {
        User user = mapper.map(userCreateDto, User.class);
        try {
            user = dao.create(user);
            return mapper.map(user, UserReadDto.class);
        } catch (Exception e) {
            throw new RuntimeException("You already saved this user");
        }
    }

    @Override
    public UserReadDto getById(UUID id) {
        return mapper.map(dao.findById(id).get(), UserReadDto.class);
    }

    @Override
    public int delete(UUID id) {
        return dao.deleteById(id);
    }

    @Override
    public List<UserReadDto> getAll() {
        List<User> all = dao.getAll();
        List<UserReadDto> users = new ArrayList<>();
        for (User user : all) {
            users.add(mapper.map(user, UserReadDto.class));
        }
        return users;
    }

    @Override
    public UserReadDto checkUser(UserCreateDto dto) {
        User mappingUser = mapper.map(dto, User.class);
        try {
            User user = dao.checkUser(mappingUser);
            if (user != null) {
                return mapper.map(user, UserReadDto.class);
            }
        } catch (NoResultException | NullPointerException e) {
            e.getMessage();
        }
        return null;
    }
}

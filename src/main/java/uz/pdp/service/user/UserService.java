package uz.pdp.service.user;

import org.springframework.stereotype.Service;
import uz.pdp.domain.dto.user.UserCreateDto;
import uz.pdp.domain.dto.user.UserReadDto;
import uz.pdp.service.BaseService;

@Service
public interface UserService extends BaseService<UserCreateDto, UserReadDto> {

    UserReadDto checkUser(UserCreateDto dto);

}

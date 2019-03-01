package converter;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDto;
import com.laptrinhjavaweb.entity.UserEntity;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Date;

public class TestUserConverter {

//    @Inject
//    private UserConverter injectUserConverter;

    @Inject
    private UserConverter userConverter;

//    @PostConstruct
//    public void init(){
//        userConverter = injectUserConverter;
//    }

    @Test
    public void entityToDto() {
        UserDto dto = new UserDto();
        dto.setCreatedDate(new Date());
        dto.setId(1);
        dto.setFullName("Vo thanh tai");

        UserEntity entity = userConverter.dtoToEntity(dto);
    }
}

package converter;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDto;
import com.laptrinhjavaweb.entity.UserEntity;
import org.testng.annotations.Test;

import java.util.Date;

public class TestUserConverter {
    private UserConverter userConverter;

    @Test
    public void entityToDto() {
        UserDto dto = new UserDto();
        dto.setCreatedDate(new Date());
        dto.setId(1);
        dto.setFullName("Vo thanh tai");

        UserEntity entity = userConverter.dtoToEntity(dto);
    }
}

package user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author marshio
 * @desc
 * @create 2023-03-31 17:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long id;
    private String sex;
    private String name;
}

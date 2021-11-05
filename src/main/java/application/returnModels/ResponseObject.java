package application.returnModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ResponseObject<T> {

    private boolean success;
    private HttpStatus statusCode;
    private T object;


}

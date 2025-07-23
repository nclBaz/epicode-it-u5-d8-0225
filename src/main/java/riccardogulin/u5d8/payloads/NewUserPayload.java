package riccardogulin.u5d8.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewUserPayload {
	private String name;
	private String surname;
	private String email;
	private String password;
}

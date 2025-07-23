package riccardogulin.u5d8.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import riccardogulin.u5d8.payloads.ErrorsPayload;

import java.time.LocalDateTime;

@RestControllerAdvice // <-- ANNOTAZIONE OBBLIGATORIA per la gestione delle eccezioni
// Questa annotazione serve per creare questo speciale "controller", un controller che non gestisce richieste ma gestisce eccezioni in maniera centralizzata
// Non potrò quindi inviargli delle richieste ad un url specifico, tramite un metodo specifico, ecc ecc bensì qua dovrò stabilire quali eccezioni gestire
// In pratica, i metodi di questo controller non sarannò degli endpoint, bensì saranno dei metodi ognuno contenente una logica per indicare
// il tipo di risposta da inviare nel caso si verifichi una specifica eccezione

// Questa classe serve per evitare di dover fare la gestione delle eccezioni a livello di singolo endpoint, quindi invece di fare per ogni endpoint un
// try-catch, tutte le eccezioni le facciamo arrivare a questa classe, la quale si occuperà di rispondere in maniera opportuna

// Ogni metodo avrà un'annotazione specifica che si chiama @ExceptionHandler, grazie a questa stabilisco quale eccezione venga gestita dallo specifico metodo
public class ExceptionsHandler {

	@ExceptionHandler(BadRequestException.class) // Tra le parentesi indico quale eccezione dovrà gestire questo metodo
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	public ErrorsPayload handleBadRequest(BadRequestException ex) {
		return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	public ErrorsPayload handleNotFound(NotFoundException ex) {
		return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(Exception.class) // Tutte le eccezioni non gestite dagli altri metodi risulteranno essere un Server Error (500)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	public ErrorsPayload handleServerError(Exception ex) {
		ex.printStackTrace(); // E' importante avere il print dello stack trace per avere un'indicazione utile per debuggare/fixare l'errore
		return new ErrorsPayload("C'è stato un errore generico! Giuro che lo risolveremo presto!", LocalDateTime.now());
	}
}

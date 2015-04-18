package de.detzler.ralf.sispmctl;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Produces(MediaType.TEXT_PLAIN)
public class AppExceptionMapper implements ExceptionMapper<SispmctlException> {

	@Override
	public Response toResponse(SispmctlException ex) {
		return Response.status(ex.getHttpStatus()).entity(ex.getMessage())
				.type(MediaType.APPLICATION_JSON).build();
	}

}

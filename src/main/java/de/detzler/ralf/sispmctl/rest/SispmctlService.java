package de.detzler.ralf.sispmctl.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.detzler.ralf.sispmctl.AppExceptionMapper;
import de.detzler.ralf.sispmctl.SispmctlException;
import de.detzler.ralf.sispmctl.model.SispmctlDevice;

@Path("/v1/outlet")
public class SispmctlService {

	public static final int MSG_OUTLET_OUT_OF_RANGE = 400;

	private static final String BLOG_POST_URL = "http://localhost:8080/device-rest-services/rest/v1/outlet/status/{outlet}";

	private SispmctlDevice device;

	public SispmctlService() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"beans.xml");
		device = (SispmctlDevice) appContext.getBean("device");
	}

	/**
	 * Returns the status of the given outlet.
	 * 
	 * Example call: http://localhost:8080/device-rest-services/rest/v1/outlet/
	 * status/2
	 * 
	 * <h3>Security</h3> Due to parameter <code>outlet</code> is of type int,
	 * script injections are not possible.
	 * 
	 * @param outlet
	 *            int the number of the outlet, e.g. 1 - 4
	 * @return String "off" if the outlet is switched off, "on" if the outlet is
	 *         switched on, "ERROR: 'error message' in the case of errors.
	 */
	@GET
	@Path("/status/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getOutletStatus(@PathParam("id") int outlet) {
		Response response;
		String ret = "";
		System.out.println("Outlet=" + outlet);

		try {
			checkOutletValid(outlet);
			ret = device.getOutletStatus(outlet);
			response = Response.status(200).entity(ret).build();
		} catch (SispmctlException ex) {
			response = new AppExceptionMapper().toResponse(ex);
		}

		return response;
	}

	@GET
	@Path("/on/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response switchOutletOn(@PathParam("id") int outlet) {
		Response response;
		String ret = "";
		System.out.println("Outlet=" + outlet);

		try {
			checkOutletValid(outlet);
			device.switchOutletOn(outlet);
			response = Response.status(200).entity(ret).build();
		} catch (SispmctlException ex) {
			response = new AppExceptionMapper().toResponse(ex);
		}

		return response;
	}

	@GET
	@Path("/off/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response switchOutletOff(@PathParam("id") int outlet) {
		Response response;
		String ret = "";
		System.out.println("Outlet=" + outlet);

		try {
			checkOutletValid(outlet);
			device.switchOutletOff(outlet);
			response = Response.status(200).entity(ret).build();
		} catch (SispmctlException ex) {
			response = new AppExceptionMapper().toResponse(ex);
		}

		return response;
	}

	public void checkOutletValid(int outlet) throws SispmctlException {

		if (outlet < 1 || outlet > 4) {

			throw new SispmctlException(
					Response.Status.BAD_REQUEST.getStatusCode(),
					"Outlet out of range. Please verify that the outlet number is between 1 and 4.");
		}
	}

	public SispmctlDevice getDevice() {
		return device;
	}

	public void setDevice(SispmctlDevice device) {
		System.out.println("SET DEVICE");
		this.device = device;
	}
}
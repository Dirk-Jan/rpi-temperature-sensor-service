package nl.djja.rpi.temperaturesensorservice.rest.resources;

import nl.djja.rpi.temperaturesensorservice.dtos.IOPinStateDTO;
import nl.djja.rpi.temperaturesensorservice.exceptions.IOPinManipulationException;
import nl.djja.rpi.temperaturesensorservice.factories.ServiceFactory;
import nl.djja.rpi.temperaturesensorservice.iopincontrol.IOPinState;
import nl.djja.rpi.temperaturesensorservice.rest.factory.RESTFactory;
import nl.djja.rpi.temperaturesensorservice.rest.util.Util;
import nl.djja.rpi.temperaturesensorservice.rest.util.exceptions.StringIsNotANumberException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

// TODO Define interface for other protocols

@Path("temperaturesensors")
public class TemperatureSensorsResource {

    @GET
    @Path("{pinNumber}/state")
    @Produces("application/json")
    public Response readTemperature(@PathParam("pinNumber") String pinNumberAsString) {
        int pinNumber;
        try {
            pinNumber = Util.stringToInteger(pinNumberAsString);
        } catch (StringIsNotANumberException e) {
            return RESTFactory.getErrorResponse(400, "Provided path parameter pinNumber is not a number.");
        }

        try {
            IOPinState pinState = ServiceFactory.getGPIOService().read(pinNumber);
            IOPinStateDTO dto = new IOPinStateDTO();
            dto.pinNumber = pinNumber;
            dto.pinState = pinState;

            return RESTFactory.getResponse(200, dto);
        } catch (IOPinManipulationException e) {
            return RESTFactory.getErrorResponse(500, e.getMessage());
        } catch (Exception e) {
            return RESTFactory.getGenericErrorResponse();
        }
    }

    @POST
    @Path("{pinNumber}/state")
    @Consumes("application/json")
    @Produces("application/json")
    public Response setTemperature(@PathParam("pinNumber") String pinNumberAsString, String ioPinStateDTOJSON) {
        int pinNumber;
        try {
            pinNumber = Util.stringToInteger(pinNumberAsString);
        } catch (StringIsNotANumberException e) {
            return RESTFactory.getErrorResponse(400, "Provided path parameter pinNumberAsString is not a number.");
        }

        IOPinStateDTO ioPinStateDTO;
        try {
            ioPinStateDTO = RESTFactory.getObjectMapper().readValue(ioPinStateDTOJSON, IOPinStateDTO.class);
        } catch (IOException e) {
            return RESTFactory.getErrorResponse(422, "Unprocessable Entity");
        }

        try {
            ServiceFactory.getGPIOService().write(pinNumber, ioPinStateDTO.pinState);
            return RESTFactory.get204Response();
        } catch (IOPinManipulationException e) {
            return RESTFactory.getErrorResponse(500, e.getMessage());
        } catch (Exception e) {
            return RESTFactory.getGenericErrorResponse();
        }
    }
}
